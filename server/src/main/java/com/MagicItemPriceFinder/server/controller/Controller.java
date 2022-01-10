package com.MagicItemPriceFinder.server.controller;

import com.MagicItemPriceFinder.server.dao.MagicItemDAO;
import com.MagicItemPriceFinder.server.model.MagicItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@RestController
public class Controller {

    @Autowired
    MagicItemDAO magicItemDAO;

    @RequestMapping(path="/magic-items", method = RequestMethod.GET)
    public List<MagicItem> list() {
        return magicItemDAO.getAll();
    }

    @RequestMapping(path="/magic-items/search")
    public List<MagicItem> searchByName(@RequestParam(name = "name_contains") String nameContains) {
        return magicItemDAO.getItemsByName(nameContains);
    }

    @RequestMapping(path="/magic-items/filter", method = RequestMethod.GET)
    public List<MagicItem> filterItems(@RequestParam(name = "name_contains", required = false) String name,
                                       @RequestParam(required = false) String rarity,
                                       @RequestParam(required = false) String type,
                                       @RequestParam(name = "requires_attunement", required = false) boolean requiresAttunement) {

        List<MagicItem> unfilteredItems = new ArrayList<>();
        List<MagicItem> filteredItems = new ArrayList<>();
        if (name != null) {
            unfilteredItems = searchByName(name);
        } else unfilteredItems = list();

        for(MagicItem item : unfilteredItems) {
            boolean addToList = true;

            //check the item against all the search options
            if (rarity != null) {
                if (!item.getRarity().getName().equalsIgnoreCase(rarity)) addToList = false;
            }
            if (type != null) {
                if (!item.getItemType().equalsIgnoreCase(type)) addToList = false;
            }
            if (requiresAttunement) {
                if (item.getAttunement() == null) addToList = false;
            }

            if (addToList) filteredItems.add(item);
        }

        return filteredItems;
    }


}
