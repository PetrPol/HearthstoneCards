package com.petrpol.hearthstonecards.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/** Model class represents one card  */
@Entity(tableName = "cards_table")
public class Card {

    @PrimaryKey
    @NonNull
    private String cardId;

    private String name;
    private String cardSet;
    private String type;
    private String flavor;
    private String text;
    private String artist;
    private String img;
    private String playerClass;
    private String imgGold;
    private String cost;
    private String attack;
    private String health;

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

    public String getPlayerClass() {
        return playerClass;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCost() {
        return cost;
    }

    public void setCardSet(String cardSet) {
        this.cardSet = cardSet;
    }

    public void setPlayerClass(String playerClass) {
        this.playerClass = playerClass;
    }

    public void setImgGold(String imgGold) {
        this.imgGold = imgGold;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public void setAttack(String attack) {
        this.attack = attack;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getAttack() {
        return attack;
    }

    public String getHealth() {
        return health;
    }
}
