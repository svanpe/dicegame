package be.svanpe.dicegame;

import java.util.List;

public class PlayMessage {
    private String token;
    private List<Integer> diceToKeep;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Integer> getDiceToKeep() {
        return diceToKeep;
    }

    public void setDiceToKeep(List<Integer> diceToKeep) {
        this.diceToKeep = diceToKeep;
    }
}
