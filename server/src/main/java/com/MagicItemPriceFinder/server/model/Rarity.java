package com.MagicItemPriceFinder.server.model;

public class Rarity {

    private String name;
    private String recommendedLevel;
    private Integer gpLowerRange;
    private Integer gpUpperRange;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecommendedLevel() {
        return recommendedLevel;
    }

    public void setRecommendedLevel(String recommendedLevel) {
        this.recommendedLevel = recommendedLevel;
    }

    public Integer getGpLowerRange() {
        return gpLowerRange;
    }

    public void setGpLowerRange(Integer gpLowerRange) {
        this.gpLowerRange = gpLowerRange;
    }

    public Integer getGpUpperRange() {
        return gpUpperRange;
    }

    public void setGpUpperRange(Integer gpUpperRange) {
        this.gpUpperRange = gpUpperRange;
    }
}
