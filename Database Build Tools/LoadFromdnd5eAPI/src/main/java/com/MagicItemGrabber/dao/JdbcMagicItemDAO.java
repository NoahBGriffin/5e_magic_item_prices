package com.MagicItemGrabber.dao;

import com.MagicItemGrabber.model.MagicItem;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcMagicItemDAO implements MagicItemDAO {

    private JdbcTemplate jdbcTemplate;

    public JdbcMagicItemDAO() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(System.getenv("APP_DB_URL"));
        dataSource.setUsername(System.getenv("APP_DB_USER"));
        dataSource.setPassword(System.getenv("APP_DB_PASS"));
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<MagicItem> addAllItems(List<MagicItem> items) {
        //this is for items that have multiples and are condensed into a single API entry
        //ie belt of giant strength
        //these will have to be entered manually, as rarity for each sub-item must be researched
        List<MagicItem> leftovers = new ArrayList<>();
        for (MagicItem item : items) {
            if (item.getRarity().equals("common") || item.getRarity().equals("uncommon") ||
                    item.getRarity().equals("rare") || item.getRarity().equals("very rare") ||
                    item.getRarity().equals("legendary") || item.getRarity().equals("artifact")) {
                addItem(item);
            } else {
                leftovers.add(item);
            }
        }
        return leftovers;
    }

    @Override
    public void addItem(MagicItem item) {
        String sqlCheckExists = "SELECT * FROM magic_item WHERE item_name = ? AND rarity = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sqlCheckExists, item.getName(), item.getRarity());

        if (!result.next()) {
            try {
                String sqlAddItem = "INSERT INTO magic_item(item_name, item_type, rarity, description, attunement)" +
                        "VALUES(?, ?, ?, ?, ?)";
                jdbcTemplate.update(sqlAddItem, item.getName(), item.getItemType(), item.getRarity(), item.getDescription(),
                        item.getAttunement());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
