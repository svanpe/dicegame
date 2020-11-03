package be.svanpe.dicegame.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameHandler {

    private Game game;

    public GameHandler(List<Player> players){
        setGame(new Game(players));

    }

    public List<Dice> playerPlay(Integer ...dicesPlayerKeep){

        Dice[] dicesToKeep = new Dice[dicesPlayerKeep.length];

        int index= 0;

        for(Integer i : dicesPlayerKeep){
            dicesToKeep[index] = game.getDices().get(i);
            index++;
        }

        return playerPlay(dicesToKeep);
    }

    public List<Dice> playerPlay(Dice ...dicesPlayerKeep){
        List<Dice> newDices = new ArrayList<>();

        getGame().setCountOfTrialForPlayer(getGame().getCountOfTrialForPlayer()+1);

        if(dicesPlayerKeep.length==0){
            for(int i =0;i<6;i++){
                newDices.add(Dice.launch());
            }
        } else {
            for(Dice dice : game.getDices()){
                //dont keep dice? remove from the list and launch dice
                if(diceWeKeep(dice, dicesPlayerKeep)){
                    newDices.add(dice);
                } else {
                    newDices.add(Dice.launch());
                }
            }

        }

        game.getDices().clear();
        game.setDices(newDices);

        if(getGame().getCountOfTrialForPlayer()==3){
          switchPlayer();

        }

        return newDices;
    }

    private boolean diceWeKeep(Dice aDice, Dice[] dicesToKeep){
        for(Dice dice:dicesToKeep){
            if(aDice.equals(dice)){
                return true;
            }
        }

        return false;
    }

    public Card evaluate(){
        List<Card> cards = new ArrayList<>();

        for(Card card: getGame().getCards()){
            if(card.rule.isValid(getGame().getDices())){
                cards.add(card);
            }
        }

        //get the best card
        if(!cards.isEmpty()) {
            return takeTheBestCard(cards);
        }

        return null;
    }

    public Player switchPlayer(){

        //first check if the player has won something
        Player player = getGame().getCurrentPlayer();

        List<Card> cards = new ArrayList<>();

        for(Card card: getGame().getCards()){
            if(card.rule.isValid(getGame().getDices())){
                cards.add(card);
            }
        }

        //get the best card
        if(!cards.isEmpty()){
            Card card = takeTheBestCard(cards);

            //add it to the player
            player.getCardsWon().add(card);
            getGame().getCards().remove(card);

            System.out.println(player.getName() + " won " + card.getExplaination());

        }

        //turn
        getGame().setCountOfTrialForPlayer(0);

        game.setIndexOfCurrentPlayer(game.getIndexOfCurrentPlayer()+1);

        if(game.getIndexOfCurrentPlayer() == getGame().getPlayers().size()){
            game.setIndexOfCurrentPlayer(0);
        }

        game.setCountOfTrialForPlayer(0);
        game.setDices(new ArrayList<>());

        return getGame().getCurrentPlayer();
    }

    private Card takeTheBestCard(List<Card> cards){
        Collections.sort(cards,new CardComparator());
        return cards.get(cards.size()-1);
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
