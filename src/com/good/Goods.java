package com.good;

public class Goods {
    private int goodId;
    private String goodName;
    private String goodPic;
    private int goodPrice;
    private String goodDescription;
    private int goodStock;

    public Goods() {
    }

    public Goods(int goodId, String goodName, String goodPic, int goodPrice, String goodDescription, int goodStock) {
        this.goodId = goodId;
        this.goodName = goodName;
        this.goodPic = goodPic;
        this.goodPrice = goodPrice;
        this.goodDescription = goodDescription;
        this.goodStock = goodStock;
    }

    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getGoodPic() {
        return goodPic;
    }

    public void setGoodPic(String goodPic) {
        this.goodPic = goodPic;
    }

    public int getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(int goodPrice) {
        this.goodPrice = goodPrice;
    }

    public String getGoodDescription() {
        return goodDescription;
    }

    public void setGoodDescription(String goodDescription) {
        this.goodDescription = goodDescription;
    }

    public int getGoodStock() {
        return goodStock;
    }

    public void setGoodStock(int goodStock) {
        this.goodStock = goodStock;
    }
}
