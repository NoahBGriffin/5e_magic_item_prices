package com.MagicItemPriceFinder.server.dao;

import com.MagicItemPriceFinder.server.model.Rarity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcRarityDAO implements RarityDAO {

    private JdbcTemplate jdbcTemplate;

    public JdbcRarityDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Rarity getRarity(String name) {
        String sql = "SELECT rarity, recommended_character_level, gp_lower_range, gp_upper_range " +
                "FROM rarities " +
                "WHERE rarity = ?";
        Rarity rarity = new Rarity();

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, name);

        if (results.next()) {
            rarity = getRarityFromResults(results);
        }

        return rarity;
    }

    @Override
    public List<Rarity> getAllRarities() {
        String sql = "SELECT rarity, recommended_character_level, gp_lower_range, gp_upper_range " +
                "FROM rarities";
        List<Rarity> rarities = new ArrayList<>();

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while(results.next()) {
            Rarity rarity = getRarityFromResults(results);

            rarities.add(rarity);
        }

        return rarities;
    }
    
    private Rarity getRarityFromResults(SqlRowSet results) {
        Rarity rarity = new Rarity();
        String name = results.getString("rarity");
        String charLevel = results.getString("recommended_character_level");
        Integer gpLowerRange = results.getInt("gp_lower_range");
        Integer gpUpperRange = results.getInt("gp_upper_range");

        rarity.setName(name);
        rarity.setRecommendedLevel(charLevel);
        if (gpLowerRange == 0) {
            rarity.setGpLowerRange(null);
        } else rarity.setGpLowerRange(gpLowerRange);
        if (gpUpperRange == 0) {
            rarity.setGpUpperRange(null);
        } else rarity.setGpUpperRange(gpUpperRange);
        
        return rarity;
    }
}
