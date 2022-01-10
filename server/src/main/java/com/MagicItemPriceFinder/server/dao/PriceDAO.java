package com.MagicItemPriceFinder.server.dao;

import com.MagicItemPriceFinder.server.model.Price;

import java.util.List;

public interface PriceDAO {

    List<Price> getItemPrices(long itemId);

    List<Price> getAllPricesByUserId(long userId);
}
