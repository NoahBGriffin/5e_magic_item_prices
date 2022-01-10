package com.MagicItemGrabber.model;

public class MagicItem {

    private String name;
    private String itemType;
    private String rarity;
    private String description;
    private String attunement;

    public String getAttunement() {
        return attunement;
    }

    public void setAttunement(String attunement) {
        this.attunement = attunement;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "MagicItem{" +
                "name='" + name + '\'' +
                ", itemType='" + itemType + '\'' +
                ", rarity='" + rarity + '\'' +
                ", description='" + description + '\'' +
                ", attunement='" + attunement + '\'' +
                '}';
    }
}
