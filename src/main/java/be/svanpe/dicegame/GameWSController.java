package be.svanpe.dicegame;

import be.svanpe.dicegame.game.GameHandler;
import be.svanpe.dicegame.game.Player;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GameWSController {

    GameHandler handler;

    @PostConstruct
    public void init() {
        handler =new  GameHandler(createPlayers());
    }

    @MessageMapping("/play")
    @SendTo("/topic/game")
    public String play(String message){

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        try {
            PlayMessage playMessage = objectMapper.readValue(message,PlayMessage.class);
            handler.playerPlay(playMessage.getDiceToKeep().toArray(new Integer[playMessage.getDiceToKeep().size()]));

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return e.getMessage();
        }

        try {
            String game = objectMapper.writeValueAsString(handler.getGame());
            return game;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return e.getMessage();
        }

    }

    @SubscribeMapping("/topic/game")
    public String sendWelcomeMessageOnSubscription() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        try {
            String game = objectMapper.writeValueAsString(handler.getGame());
            return game;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public static List<Player> createPlayers(){
        List<Player> players = new ArrayList<>();
        players.add(new Player("seb"));
        players.add(new Player("ralf"));
        players.add(new Player("much"));

        return players;
    }

}
