package com.MagicItemPriceFinder.server.dao;

import com.MagicItemPriceFinder.server.model.Price;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcPriceDAO implements PriceDAO {

    private JdbcTemplate jdbcTemplate;

    public JdbcPriceDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Price> getItemPrices(long itemId) {
        String sql = "SELECT price_gp, source, source_notes, user_id " +
                "FROM price " +
                "WHERE item_id = ? " +
                "ORDER BY price_gp";
        List<Price> prices = new ArrayList<>();

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, itemId);

        while(results.next()) {
            int priceGP = results.getInt("price_gp");
            String source = results.getString("source");
            String notes = results.getString("source_notes");
            long userId = results.getLong("user_id");

            Price price = new Price();

            price.setPriceGP(priceGP);
            price.setSource(source);
            price.setNotes(notes);
            price.setUserId(userId);

            prices.add(price);
        }

        return prices;
    }

    @Override
    public List<Price> getAllPricesByUserId(long userId) {
        String sql = "SELECT price_gp, source, source_notes, user_id " +
                "FROM price " +
                "WHERE user_id = ? ";
        List<Price> prices = new ArrayList<>();

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);

        while(results.next()) {
            int priceGP = results.getInt("price_gp");
            String source = results.getString("source");
            String notes = results.getString("source_notes");
            long userId2 = results.getLong("user_id");

            Price price = new Price();

            price.setPriceGP(priceGP);
            price.setSource(source);
            price.setNotes(notes);
            price.setUserId(userId2);

            prices.add(price);
        }

        return prices;
    }
}
