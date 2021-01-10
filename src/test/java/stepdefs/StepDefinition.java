package stepdefs;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition {

    @Given("^the player rolls six dices$")
    public void the_player_rolls_six_dices() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("dice is thrown");
    }

    @When("^the (\\d+) dice shows number (\\d+) with \"([^\"]*)\" figure$")
    public void the_dice_shows_number_with_figure(int arg1, int arg2, String arg3) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("dice combinations");
    }

    @Then("^the same figure rule is \"([^\"]*)\"$")
    public void the_same_figure_rule_is(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("result");
    }

}
