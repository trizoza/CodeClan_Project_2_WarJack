package example.codeclan.com.cardgame;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by user on 20/01/2017.
 */

public class Player {

    private String name;
    private CopyOnWriteArrayList<WarCard> wHand;
    private CopyOnWriteArrayList<BlackJackCard> bjHand;
    private int valueOfHand;

    public Player(String name) {
        this.name = name;
        this.wHand = new CopyOnWriteArrayList<>();
        this.bjHand = new CopyOnWriteArrayList<>();
        this.valueOfHand = 0;
    }

    public String getName() {
        return this.name;
    }


    ///////// WAR METHODS //////////////////////////////

    public void addCard(WarCard card) {
        wHand.add(card);
    }

    public WarCard giveCard() {
        return wHand.remove(0);
    }

    public int cardCount() {
        return wHand.size();
    }

    //////// BLACK JACK METHODS ////////////////////////

    public void addBJCard(BlackJackCard card) {
        bjHand.add(card);
    }

    public BlackJackCard giveBJCard() {
        return bjHand.remove(0);
    }

    public int bjCardCount() {
        return bjHand.size();
    }

    public CopyOnWriteArrayList<BlackJackCard> getBJHand() {
        return bjHand;
    }

    public int getValueOfHand() {
        valueOfHand = 0;
        for (BlackJackCard card : bjHand) {
            valueOfHand += card.getValue();
        }
        return valueOfHand;
    }

}

