package com.petrpol.hearthstonecards.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/** Model class represents card back object */
@Entity(tableName = "card_backs_table")
public class CardBack {

    @PrimaryKey
    @NonNull
    private String cardBackId = "";
    private String img;


    public @NonNull String getCardBackId() {
        return cardBackId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @SuppressWarnings({"unused"}) //Set by Retrofit - used by Room
    public void setCardBackId(@NonNull String cardBackId) {
        this.cardBackId = cardBackId;
    }
}
