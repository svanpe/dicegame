package be.svanpe.dicegame.game;

public class Card {

    private String name;
    CardRule rule;
    private String explaination;
    int points;

    public Card(String name,String explaination,int points, CardRule rule){
        this.setName(name);
        this.setExplaination(explaination);
        this.rule = rule;
        this.points  = points;

    }

    public int getPoints() {

        return points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExplaination() {
        return explaination;
    }

    public void setExplaination(String explaination) {
        this.explaination = explaination;
    }
}
