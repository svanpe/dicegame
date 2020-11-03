package be.svanpe.dicegame.game;

import java.util.ArrayList;
import java.util.List;

public class GameControler {

    GameHandler handler;

    public static List<Player> createPlayers(){
        List<Player> players = new ArrayList<>();
        players.add(new Player("seb"));
        players.add(new Player("ralf"));
        players.add(new Player("much"));

        return players;
    }

    public void start(){
        handler =new  GameHandler(createPlayers());

    }

    public void play(Dice... diceWeKeep){
        System.out.println("Current player is " + handler.getGame().getCurrentPlayer().getName() + "("+handler.getGame().getCurrentPlayer().score()+ " points) - " + handler.getGame().getCountOfTrialForPlayer() + "/3 trials");
        Integer[] dices = new Integer[0];

        displayDices(handler.playerPlay(dices));

        displayPlayers(handler.getGame().getPlayers());


    }
    protected void displayDices(List<Dice> dices){

        StringBuilder output = new StringBuilder();

        int count = 0;

        for(Dice dice: dices){
            output.append(++count + "[").append(dice.getColor()).append(":").append(dice.getFigure()).append("] ");
        }

        System.out.println(output.toString());

    }
    protected void displayPlayers(List<Player> players){

        StringBuilder output = new StringBuilder();
        for(Player player: players){
            output.append("[").append(player.getName()).append(":").append(player.score()).append("] ");

        /*    for(Card card:player.cardsWon){
                System.out.println("\t" + card.name );
            }*/
        }

        System.out.println(output.toString());

    }

    public boolean gameContinue(){
        if(handler.getGame().getCards().isEmpty()){
            return false;
        }

        return true;
    }

}
