package be.svanpe.dicegame.game;

import java.util.Comparator;

public class CardComparator  implements Comparator<Card> {

    @Override
    public int compare(Card o1, Card o2) {
        return Integer.compare(o1.points,o2.points);
    }
}
