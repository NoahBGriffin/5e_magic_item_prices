package com.MagicItemGrabber;

import com.MagicItemGrabber.dao.JdbcMagicItemDAO;
import com.MagicItemGrabber.dao.MagicItemDAO;
import com.MagicItemGrabber.model.MagicItem;
import com.MagicItemGrabber.services.MagicItemService;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.util.List;

public class MagicItemLoaderCLI {

    private final MagicItemService magicItemService;
    private final MagicItemDAO magicItemDAO;

    public static void main(String[] args) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/MagicItems");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");

        MagicItemLoaderCLI magicItemLoaderCLI = new MagicItemLoaderCLI(dataSource);
        magicItemLoaderCLI.run();
    }

    public MagicItemLoaderCLI(DataSource dataSource) {
        magicItemService = new MagicItemService();
        magicItemDAO = new JdbcMagicItemDAO(dataSource);
    }

    private void run() {
        List<MagicItem> items = magicItemService.getAllItems();
//        for (MagicItem item : items) {
//            if (item.getRarity().length() > 15) {
//                System.out.println(item);
//            }
//        }
        List<MagicItem> leftovers = magicItemDAO.addAllItems(items);
        System.out.println("Done\n\n");
        for (MagicItem item : leftovers) {
            System.out.println(item);
        }

    }

}
