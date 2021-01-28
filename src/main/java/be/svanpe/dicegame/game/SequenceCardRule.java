package be.svanpe.dicegame.game;

import java.util.Collections;
import java.util.List;

public class SequenceCardRule implements CardRule{

    int count;

    public SequenceCardRule(int count){
        this.count = count;
    }
    public boolean isValid(List<Dice> dices) {
         Collections.sort(dices, new DiceComparator());

         int validationCount=0;

         Dice previousDice = null;
         for(Dice dice: dices){

             if(previousDice!=null){
                 // following commented code will fix the test case SequenceCardRuleTest.testing5Sequence
                 if(dice.getFigure().numericValue == previousDice.getFigure().numericValue)
                 {
                     continue;
                 } else

                 if(dice.getFigure().numericValue == previousDice.getFigure().numericValue+1){
                     validationCount ++;

                     if(validationCount==count-1){
                         return true;
                     }
                 } else {
                     validationCount=0;
                 }
             }

             previousDice = dice;

         }



         return false;
    }
}
