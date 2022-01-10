package com.MagicItemPriceFinder.server.model;

public class Price {

    private int priceGP;
    private String source;
    private String notes;
    private long userId;

    public int getPriceGP() {
        return priceGP;
    }

    public void setPriceGP(int priceGP) {
        this.priceGP = priceGP;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
