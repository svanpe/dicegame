package be.svanpe.dicegame.game;

public class Action {
    private String message;

    public Action(){

    }

    public Action(String message){
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
