package be.svanpe.dicegame.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Dice {



    public enum Figures {
        one(1), two(2), three(3), four(4), five(5), six(6);
        int numericValue;

        Figures(int numericValue){
            this.numericValue = numericValue;
                    }
    }

    public enum Colors { red, green, blue}

    private Figures figure;
    private Colors color;

    public Dice(Colors color, Figures figure){
        this.setColor(color);
        this.setFigure(figure);

    }

    public static List<Dice> launch(int quantity){
        List<Dice> list = new ArrayList<Dice>();

        for(int i=0; i<quantity; i++){
            list.add(launch());
        }

        return list;
    }

    public static Dice launch(){
        return new Dice(Colors.values()[new Random().nextInt(Colors.values().length)]
                , Figures.values()[new Random().nextInt(Figures.values().length)]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dice dice = (Dice) o;
        return getFigure() == dice.getFigure() &&
                getColor() == dice.getColor();
    }

    public Figures getFigure() {
        return figure;
    }

    public void setFigure(Figures figure) {
        this.figure = figure;
    }

    public Colors getColor() {
        return color;
    }

    public void setColor(Colors color) {
        this.color = color;
    }


    @Override
    public int hashCode() {
        return Objects.hash(getFigure(), getColor());
    }
}
