package stepdefs;

import be.svanpe.dicegame.game.Dice;
import be.svanpe.dicegame.game.SameFigureCardRule;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefinition {

    private final SameFigureCardRule cardRule = new SameFigureCardRule(Dice.Figures.six, 3);
    private final List<Dice> diceList = new ArrayList<>();


    @Given("^the player rolls six dices$")
    public void the_player_rolls_six_dices() {
        System.out.println("dice is thrown");
    }

    @When("^the (\\d+) dice shows number \"([^\"]*)\" with \"([^\"]*)\" figure$")
    public void the_dice_shows_number_with_figure(int arg1, String arg2, String arg3) {
        Dice dice = new Dice(Dice.Colors.valueOf(arg3), Dice.Figures.valueOf(arg2));
        diceList.add(dice);

    }

    @Then("^the same figure rule is \"([^\"]*)\"$")
    public void the_same_figure_rule_is(String arg1) {
        assertEquals(Boolean.parseBoolean(arg1), cardRule.isValid(diceList));
    }

}
