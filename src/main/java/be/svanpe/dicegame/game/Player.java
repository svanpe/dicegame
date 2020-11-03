package be.svanpe.dicegame.game;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private List<Card> cardsWon;

    public Player(String name){
        this.setName(name);
        setCardsWon(new ArrayList<Card>());

    }

    public int score(){
        int score = 0;
        for(Card card: getCardsWon()){

            score = score + card.getPoints();
        }

        return score;
    }

    public int getScore(){
        return score();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getCardsWon() {
        return cardsWon;
    }

    public void setCardsWon(List<Card> cardsWon) {
        this.cardsWon = cardsWon;
    }
}
