package be.svanpe.dicegame.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Testing the sequence of cards")
class SequenceCardRuleTest {

    @DisplayName("Testing 3 consecutive sequence")
    @Test
    void testing3Sequence() {
        SequenceCardRule cardRule = new SequenceCardRule(3);
        Dice dice1 = new Dice(Dice.Colors.red, Dice.Figures.one);
        Dice dice2 = new Dice(Dice.Colors.blue, Dice.Figures.one);
        Dice dice3 = new Dice(Dice.Colors.green, Dice.Figures.three);
        Dice dice4 = new Dice(Dice.Colors.blue, Dice.Figures.four);
        Dice dice5 = new Dice(Dice.Colors.blue, Dice.Figures.two);
        Dice dice6 = new Dice(Dice.Colors.blue, Dice.Figures.six);
        List<Dice> dices = new ArrayList<>();
        dices.add(dice1);
        dices.add(dice2);
        dices.add(dice3);
        dices.add(dice4);
        dices.add(dice5);
        dices.add(dice6);

        assertTrue(cardRule.isValid(dices));
    }

    @DisplayName("Testing 4 consecutive sequence")
    @Test
    void testing4Sequence() {
        SequenceCardRule cardRule = new SequenceCardRule(4);
        Dice dice1 = new Dice(Dice.Colors.red, Dice.Figures.one);
        Dice dice2 = new Dice(Dice.Colors.blue, Dice.Figures.one);
        Dice dice3 = new Dice(Dice.Colors.green, Dice.Figures.three);
        Dice dice4 = new Dice(Dice.Colors.blue, Dice.Figures.four);
        Dice dice5 = new Dice(Dice.Colors.blue, Dice.Figures.two);
        Dice dice6 = new Dice(Dice.Colors.blue, Dice.Figures.six);
        List<Dice> dices = new ArrayList<>();
        dices.add(dice1);
        dices.add(dice2);
        dices.add(dice3);
        dices.add(dice4);
        dices.add(dice5);
        dices.add(dice6);

        assertTrue(cardRule.isValid(dices));
    }

    @DisplayName("Testing 5 consecutive sequence")
    @Test
    void testing5Sequence() {
        SequenceCardRule cardRule = new SequenceCardRule(5);
        Dice dice1 = new Dice(Dice.Colors.red, Dice.Figures.one);
        Dice dice2 = new Dice(Dice.Colors.blue, Dice.Figures.five);
        Dice dice3 = new Dice(Dice.Colors.green, Dice.Figures.three);
        Dice dice4 = new Dice(Dice.Colors.blue, Dice.Figures.four);
        Dice dice5 = new Dice(Dice.Colors.blue, Dice.Figures.two);
        Dice dice6 = new Dice(Dice.Colors.blue, Dice.Figures.two);
        List<Dice> dices = new ArrayList<>();
        dices.add(dice1);
        dices.add(dice2);
        dices.add(dice3);
        dices.add(dice4);
        dices.add(dice5);
        dices.add(dice6);

        assertTrue(cardRule.isValid(dices));
    }
}