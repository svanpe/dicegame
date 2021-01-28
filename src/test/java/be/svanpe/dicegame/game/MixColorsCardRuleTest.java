package be.svanpe.dicegame.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Testing for occurrence of  mixed colors")
class MixColorsCardRuleTest {

    @DisplayName("Testing for occurrence of 3 green and 2 blue")
    @Test
    void MixedFigureTesting() {
        MixColorsCardRule cardRule = new MixColorsCardRule(0, 3, 2);
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