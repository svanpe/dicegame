package be.svanpe.dicegame.game;

import java.util.List;

public interface CardRule {

    public boolean isValid(List<Dice> dices);

}
