package be.svanpe.dicegame.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Testing for occurrence of  Same Figure")
class SameFigureCardRuleTest {

    @DisplayName("Testing for occurrence of  six figure 3 times")
    @Test
    void SameFigureTesting() {
        SameFigureCardRule cardRule = new SameFigureCardRule(Dice.Figures.six, 3);
        List<Dice> dices = List.of(new Dice(Dice.Colors.green, Dice.Figures.six),
                new Dice(Dice.Colors.blue, Dice.Figures.six),
                new Dice(Dice.Colors.green, Dice.Figures.three),
                new Dice(Dice.Colors.green, Dice.Figures.six),
                new Dice(Dice.Colors.blue, Dice.Figures.two),
                new Dice(Dice.Colors.blue, Dice.Figures.two)
        );
        assertTrue(cardRule.isValid(dices));
    }
}