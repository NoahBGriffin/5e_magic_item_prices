package com.MagicItemPriceFinder.server.dao;

import com.MagicItemPriceFinder.server.model.Rarity;

import java.util.List;

public interface RarityDAO {

    Rarity getRarity(String rarity);

    List<Rarity> getAllRarities();
}
