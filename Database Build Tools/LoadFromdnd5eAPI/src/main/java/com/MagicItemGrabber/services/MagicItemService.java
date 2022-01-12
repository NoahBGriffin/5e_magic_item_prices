package com.MagicItemGrabber.services;

import com.MagicItemGrabber.model.MagicItem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class MagicItemService {

    private static final String API_BASE_URL = "https://www.dnd5eapi.co";
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<MagicItem> getAllItems() {
        List<String> urls = getAllItemURLS();
        List<MagicItem> magicItems = new ArrayList<>();
        JsonNode jsonNode;

        for (String url : urls) {
            ResponseEntity<String> response = createStringResponseEntity(API_BASE_URL + url);

            try {
                jsonNode = objectMapper.readTree(response.getBody());

                String name = jsonNode.path("name").asText();
                String itemType = jsonNode.path("equipment_category").path("name").asText();
                String[] itemInfo = objectMapper.convertValue(jsonNode.path("desc"), String[].class);
                String description = itemInfo[1];
                String attunement = null;
                //wondrous items is plural and i dislike it!! anyway this fixes that
                if (itemType.substring(itemType.length() - 1 ).equals("s")) {
                    itemType = itemType.substring(0, itemType.length() - 1);
                }

                //deal with attunement

                if (itemInfo[0].contains("attunement")) {
                    int indexOfParen = itemInfo[0].lastIndexOf('(');
                    attunement = itemInfo[0].substring(indexOfParen + 1, itemInfo[0].length() - 1);
                    itemInfo[0] = itemInfo[0].substring(0, indexOfParen);
                }

                //handle separating the combined +1, +2, or +3 items into individual items
                if (name.contains("+1")) {
                    String[] nameAndPluses = name.split("\\,");
                    name = nameAndPluses[0];
                    int subtractIndex = 1;

                    for (int i = nameAndPluses.length - 1; i > 0; i--) {
                        MagicItem item = new MagicItem();

                        String plus = nameAndPluses[i].replace("or", "").trim();
                        String fullName = name + " " + plus;

                        //FOR RARITY
                        String[] rarities = itemInfo[0].split("\\,");
                        int index = rarities.length - subtractIndex;
                        String rarity = rarities[index].replace("or", "");
                        rarity = rarity.replace("(" + plus + ")", "");
                        rarity = rarity.trim();

                        item.setName(fullName);
                        item.setItemType(itemType);
                        item.setRarity(rarity);
                        item.setDescription(description);
                        item.setAttunement(attunement);

                        magicItems.add(item);
                        subtractIndex++;

                    }
                } else {
                    MagicItem item = new MagicItem();

                    String[] getRarityFromMe = itemInfo[0].split("\\,");
                    int index = getRarityFromMe.length - 1;
                    String rarity = getRarityFromMe[index].trim();

                    item.setName(name);
                    item.setItemType(itemType);
                    item.setRarity(rarity);
                    item.setDescription(description);
                    item.setAttunement(attunement);

                    magicItems.add(item);
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return magicItems;
    }

    public List<String> getAllItemURLS() {

        List<String> urls = new ArrayList<>();
        JsonNode jsonNode;

        ResponseEntity<String> response = createStringResponseEntity(API_BASE_URL + "/api/magic-items");

        try {
            jsonNode = objectMapper.readTree(response.getBody());

            JsonNode root = jsonNode.path("results");

            for (int i = 0; i < root.size(); i++) {
                String itemUrl = root.path(i).path("url").asText();
                urls.add(itemUrl);
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return urls;
    }

    private ResponseEntity<String> createStringResponseEntity(String url) {
        HttpEntity<String> entity = new HttpEntity<>("");
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class
        );
        return response;
    }



}
