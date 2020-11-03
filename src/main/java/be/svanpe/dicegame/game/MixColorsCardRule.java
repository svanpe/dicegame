package be.svanpe.dicegame.game;

import java.util.List;

public class MixColorsCardRule implements  CardRule{

    int red;
    int green;
    int blue;

    public MixColorsCardRule(int red, int green, int blue){
    this.red = red;
    this.blue = blue;
    this.green = green;
    }

    @Override
    public boolean isValid(List<Dice> dices) {
        int countRed=0;
        int countGreen=0;
        int countBlue=0;

        for(Dice dice : dices) {
            switch (dice.getColor()) {
                case red:
                    countRed++;
                    break;
                case blue:
                    countBlue++;
                    break;
                case green:
                    countGreen++;
            }

        }

        if(countRed>=red && countGreen>=green && countBlue >=blue){
            return true;
        }

        return false;
    }
}
