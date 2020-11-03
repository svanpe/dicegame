package be.svanpe.dicegame.game;

import java.util.Comparator;

public class DiceComparator implements Comparator<Dice> {

    public int compare(Dice o1, Dice o2) {
       return Integer.compare(o1.getFigure().numericValue, o2.getFigure().numericValue);
    }
}
