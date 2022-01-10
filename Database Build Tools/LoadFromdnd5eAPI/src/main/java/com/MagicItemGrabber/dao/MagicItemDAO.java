package com.MagicItemGrabber.dao;

import com.MagicItemGrabber.model.MagicItem;

import java.util.List;

public interface MagicItemDAO {

    public List<MagicItem> addAllItems(List<MagicItem> items);

    public void addItem(MagicItem item);
}
