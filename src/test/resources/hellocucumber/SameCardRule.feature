Feature: Dice Game


  The Dice game is a turn-based game for multiple players.
  Each player has to roll 6 dices.Based on the rules the
  player gets points for the figures and numbers generated.


  Rule: Same Figure Rule


    Scenario: 3 dices contains six numbers
      Given the player rolls six dices
      When the 1 dice shows number 3 with "blue" figure
      And the 2 dice shows number 6 with "blue" figure
      And the 3 dice shows number 6 with "green" figure
      And the 4 dice shows number 2 with "green" figure
      And the 5 dice shows number 4 with "green" figure
      And the 6 dice shows number 6 with "blue" figure
      Then the same figure rule is "true"

