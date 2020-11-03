package be.svanpe.dicegame.game;

public class GameApp {
    public static void main(String... args){
        GameControler controler = new GameControler();

        controler.start();

        int index=0;
        int tour=0;
        while(controler.gameContinue() && (index<200)){

            index++;
            tour++;
            controler.play(new Dice[]{});

            if(tour<3){
                Card card = controler.handler.evaluate();
                if(card!=null && card.points>2){
                    tour=0;
                    System.out.println(controler.handler.getGame().getCurrentPlayer().getName() + " decided to win " + card.getExplaination());
                    controler.handler.switchPlayer();
                }
            }

        }

        System.out.println("loop : " + index);
    }
}
