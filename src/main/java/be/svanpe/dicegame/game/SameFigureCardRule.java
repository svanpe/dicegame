package be.svanpe.dicegame.game;

import java.util.List;

public class SameFigureCardRule implements CardRule{

    Dice.Figures figure;
    int number;

    public SameFigureCardRule(Dice.Figures figure, int number){

        this.figure = figure;
        this.number = number;
    }

    @Override
    public boolean isValid(List<Dice> dices) {
        int count = 0;

        for(Dice dice: dices){
            if(dice.getFigure() == this.figure){
                count++;
            }

        }

        if(count==number){
            return  true;
        }

        return false;
    }
}
