package com.petrpol.hearthstonecards.data.model;

/** Model class represents one card  */
public class Card {

    private String cardId;
    private String name;
    private String cardSet;
    private String type;
    private String flavor;
    private String text;
    private String artist;
    private String img;
    private String imgGold;
    private int cost;
    private int attack;
    private int health;

    public Card(String cardId, String name, String cardSet, String type, String flavor, String text, String artist, String img, String imgGold, int cost, int attack, int health) {
        this.cardId = cardId;
        this.name = name;
        this.cardSet = cardSet;
        this.type = type;
        this.flavor = flavor;
        this.text = text;
        this.artist = artist;
        this.img = img;
        this.imgGold = imgGold;
        this.cost = cost;
        this.attack = attack;
        this.health = health;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardSet() {
        return cardSet;
    }

    public void setCardSet(String cardSet) {
        this.cardSet = cardSet;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImgGold() {
        return imgGold;
    }

    public void setImgGold(String imgGold) {
        this.imgGold = imgGold;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
