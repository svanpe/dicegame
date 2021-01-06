package be.svanpe.dicegame.game;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(Parameterized.class)
public class SequenceCardRuleTest {

    private final int count;
    private final List<Dice> dices;
    private final Boolean expectedResult;

    public SequenceCardRuleTest(int count, List<Dice> dices, Boolean expectedResult) {
        this.count = count;
        this.dices = dices;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        //test 1: testing 3 sequence
        List<Dice> dices1 = new ArrayList<>();
        dices1.add(new Dice(Dice.Colors.red, Dice.Figures.one));
        dices1.add(new Dice(Dice.Colors.blue, Dice.Figures.one));
        dices1.add(new Dice(Dice.Colors.green, Dice.Figures.three));
        dices1.add(new Dice(Dice.Colors.blue, Dice.Figures.five));
        dices1.add(new Dice(Dice.Colors.blue, Dice.Figures.two));
        dices1.add(new Dice(Dice.Colors.blue, Dice.Figures.six));

        //test 2: testing 4 sequence
        List<Dice> dices2 = new ArrayList<>();
        dices2.add(new Dice(Dice.Colors.red, Dice.Figures.one));
        dices2.add(new Dice(Dice.Colors.blue, Dice.Figures.two));
        dices2.add(new Dice(Dice.Colors.green, Dice.Figures.three));
        dices2.add(new Dice(Dice.Colors.blue, Dice.Figures.one));
        dices2.add(new Dice(Dice.Colors.blue, Dice.Figures.four));
        dices2.add(new Dice(Dice.Colors.blue, Dice.Figures.six));

        //test 3: testing 5 sequence
        List<Dice> dices3 = new ArrayList<>();
        dices3.add(new Dice(Dice.Colors.red, Dice.Figures.five));
        dices3.add(new Dice(Dice.Colors.blue, Dice.Figures.five));
        dices3.add(new Dice(Dice.Colors.green, Dice.Figures.three));
        dices3.add(new Dice(Dice.Colors.blue, Dice.Figures.four));
        dices3.add(new Dice(Dice.Colors.blue, Dice.Figures.six));
        dices3.add(new Dice(Dice.Colors.blue, Dice.Figures.two));

        //test 4: testing false " sequence
        List<Dice> dices4 = new ArrayList<>();
        dices4.add(new Dice(Dice.Colors.red, Dice.Figures.five));
        dices4.add(new Dice(Dice.Colors.blue, Dice.Figures.five));
        dices4.add(new Dice(Dice.Colors.green, Dice.Figures.one));
        dices4.add(new Dice(Dice.Colors.blue, Dice.Figures.four));
        dices4.add(new Dice(Dice.Colors.blue, Dice.Figures.six));
        dices4.add(new Dice(Dice.Colors.blue, Dice.Figures.two));

        return Arrays.asList(
                new Object[][]{
                        {3, dices1, true},
                        {4, dices2, true},
                        {5, dices3, true},
                        {3, dices4, false},
                        {4, dices1, false},
                        {5, dices2, false}
                }
        );
    }

    @Test
    public void testingSequences() {
        SequenceCardRule cardRule = new SequenceCardRule(count);
        assertEquals(expectedResult, cardRule.isValid(dices));
    }
}