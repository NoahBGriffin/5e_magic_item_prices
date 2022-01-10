package com.MagicItemPriceFinder.server.dao;

import com.MagicItemPriceFinder.server.model.MagicItem;

import java.util.List;

public interface MagicItemDAO {

    List<MagicItem> getAll();

    List<MagicItem> getItemsByName(String nameContains);

}
