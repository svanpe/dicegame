package be.svanpe.dicegame.game;

import java.util.ArrayList;
import java.util.List;

public class Game {

   private int indexOfCurrentPlayer;
   private int countOfTrialForPlayer;

   private List<Dice> dices;
   private List<Card> cards;
   private List<Player> players;
   private List<Action> actions;

   public List<Card> getCards(){
       return cards;
   }
   public List<Player> getPlayers(){
       return  players;
   }


   public Game(List<Player> players){
       this.setPlayers(players);
       this.setCards(initCards());
       this.setDices(new ArrayList<>());
       this.setActions(new ArrayList<>());
   }

   static List<Card> initCards(){
       List<Card> cards = new ArrayList<>();
       cards.add(new Card("troll", "make a sequence of 3 dices", 1,  new SequenceCardRule(3)));
       cards.add(new Card("troll2", "make a sequence of 4 dices", 3,  new SequenceCardRule(4)));
       cards.add(new Card("troll3", "make a sequence of 5 dices", 5,  new SequenceCardRule(5)));
       cards.add(new Card("elf", "3 times a SIX", 5,  new SameFigureCardRule(Dice.Figures.SIX,3)));
       cards.add(new Card("elf2", "2 blues and 3 green", 5,  new MixColorsCardRule(0,3,2)));
       cards.add(new Card("elf3", "5 red", 6,  new MixColorsCardRule(5,0,0)));

       return cards;
   }


    public Player getCurrentPlayer(){
        return getPlayers().get(getIndexOfCurrentPlayer());
    }

    public int getIndexOfCurrentPlayer() {
        return indexOfCurrentPlayer;
    }

    public void setIndexOfCurrentPlayer(int indexOfCurrentPlayer) {
        this.indexOfCurrentPlayer = indexOfCurrentPlayer;
    }

    public int getCountOfTrialForPlayer() {
        return countOfTrialForPlayer;
    }

    public void setCountOfTrialForPlayer(int countOfTrialForPlayer) {
        this.countOfTrialForPlayer = countOfTrialForPlayer;
    }

    public List<Dice> getDices() {
        return dices;
    }

    public void setDices(List<Dice> dices) {
        this.dices = dices;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public void addAction(Action action){
       this.actions.add(action);
    }
}
