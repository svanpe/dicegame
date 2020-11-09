package be.svanpe.dicegame;

import be.svanpe.dicegame.game.Player;

public class RegisterMessage {

    private String token;
    private String playerName;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
