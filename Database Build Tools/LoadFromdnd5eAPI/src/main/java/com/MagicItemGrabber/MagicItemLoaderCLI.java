package com.MagicItemGrabber;

import com.MagicItemGrabber.dao.JdbcMagicItemDAO;
import com.MagicItemGrabber.dao.MagicItemDAO;
import com.MagicItemGrabber.model.MagicItem;
import com.MagicItemGrabber.services.MagicItemService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MagicItemLoaderCLI {

    private MagicItemService magicItemService;
    private MagicItemDAO magicItemDAO;


    public static void main(String[] args) {
        MagicItemLoaderCLI magicItemLoaderCLI = new MagicItemLoaderCLI();
        magicItemLoaderCLI.run();
    }

    public MagicItemLoaderCLI() {
        magicItemService = new MagicItemService();
        magicItemDAO = new JdbcMagicItemDAO();
    }

    private void run() {
        List<MagicItem> items = magicItemService.getAllItems();

        List<MagicItem> leftovers = magicItemDAO.addAllItems(items);
        System.out.println("Done\n\n");
        for (MagicItem item : leftovers) {
            System.out.println(item);
        }

    }

}
