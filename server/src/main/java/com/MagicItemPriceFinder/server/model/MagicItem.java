package com.MagicItemPriceFinder.server.model;

import java.util.List;

public class MagicItem {

    private String name;
    private Rarity rarity;
    private String itemType;
    private String description;
    private String attunement;
    private List<Price> prices;

    public String getAttunement() {
        return attunement;
    }


    public void setAttunement(String attunement) {
        this.attunement = attunement;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }
}
