package com.petrpol.hearthstonecards.data.model;

/** Model class represents card back object */
public class CardBack {

    private String cardBackId;
    private String name;
    private String description;
    private String img;
    private String howToGet;

    public CardBack(String cardBackId, String name, String description, String img, String howToGet) {
        this.cardBackId = cardBackId;
        this.name = name;
        this.description = description;
        this.img = img;
        this.howToGet = howToGet;
    }

    public String getCardBackId() {
        return cardBackId;
    }

    public void setCardBackId(String cardBackId) {
        this.cardBackId = cardBackId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getHowToGet() {
        return howToGet;
    }

    public void setHowToGet(String howToGet) {
        this.howToGet = howToGet;
    }
}
