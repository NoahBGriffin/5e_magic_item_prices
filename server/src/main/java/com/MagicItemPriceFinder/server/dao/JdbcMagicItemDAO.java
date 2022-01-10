package com.MagicItemPriceFinder.server.dao;

import com.MagicItemPriceFinder.server.model.MagicItem;
import com.MagicItemPriceFinder.server.model.Price;
import com.MagicItemPriceFinder.server.model.Rarity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcMagicItemDAO implements MagicItemDAO {

    private JdbcTemplate jdbcTemplate;
    private PriceDAO priceDAO;
    private RarityDAO rarityDAO;

    public JdbcMagicItemDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        priceDAO = new JdbcPriceDAO(jdbcTemplate);
        rarityDAO = new JdbcRarityDAO(jdbcTemplate);
    }

    @Override
    public List<MagicItem> getAll() {
        String sql = "SELECT * FROM magic_item";
        List<MagicItem> items = new ArrayList<>();

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while(results.next()) {
            MagicItem item = getItemFromResults(results);

            items.add(item);
        }

        return items;
    }

    @Override
    public List<MagicItem> getItemsByName(String nameContains) {
        String sql = "SELECT * FROM magic_item WHERE item_name ILIKE ?";
        List<MagicItem> items = new ArrayList<>();

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, '%' + nameContains + '%');

        while(results.next()) {
            MagicItem item = getItemFromResults(results);

            items.add(item);
        }

        return items;
    }

    private MagicItem getItemFromResults(SqlRowSet results) {
        int itemId = results.getInt("item_id");
        String name = results.getString("item_name");
        String type = results.getString("item_type");
        String rarityStr = results.getString("rarity");
        String description = results.getString("description");
        String attunement = results.getString("attunement");

        Rarity rarity = rarityDAO.getRarity(rarityStr);
        List<Price> prices = priceDAO.getItemPrices(itemId);

        MagicItem item = new MagicItem();

        item.setName(name);
        item.setRarity(rarity);
        item.setItemType(type);
        item.setDescription(description);
        item.setAttunement(attunement);
        item.setPrices(prices);

        return item;
    }
}
