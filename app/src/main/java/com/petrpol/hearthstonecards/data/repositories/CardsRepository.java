package com.petrpol.hearthstonecards.data.repositories;

import androidx.lifecycle.MutableLiveData;

import com.petrpol.hearthstonecards.data.model.Card;

import java.util.ArrayList;
import java.util.List;

/** Repository for cards list
 *  Singleton */
public class CardsRepository {

    public static CardsRepository instance;

    private ArrayList<Card> cardsDataSet = new ArrayList<>();

    public static synchronized CardsRepository getInstance(){
        if (instance == null)
            instance = new CardsRepository();

        return instance;
    }

    public MutableLiveData<List<Card>> getCards(){

        createFakeData();

        MutableLiveData<List<Card>> data = new MutableLiveData<>();
        data.setValue(cardsDataSet);
        return data;
    }

    private void createFakeData(){
        cardsDataSet.add(new Card("EX1_323h","Lord Jaraxxus","Classic","Hero","Legendary","Give a minion +4/+4. At the start of your next turn, destroy it.",null,"https://d15f34w2p8l1cc.cloudfront.net/hearthstone/19a8b4ca47064b3b3f4520f99748872d9c2b3761f99bf4537c3fdb48b590fde7.png"
                ,null,0,0,15));
        cardsDataSet.add(new Card("EX1_145","Preparation","Classic","Hero","Legendary","The next spell you cast this turn costs (2) less.",null,"https://d15f34w2p8l1cc.cloudfront.net/hearthstone/4387cee6d06e8ba368f9af46e165b52968c9dd2859a167dc1edfd94f297008a4.png"
                ,null,0,0,15));
    }
}
