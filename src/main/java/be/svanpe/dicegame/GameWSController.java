package be.svanpe.dicegame;

import be.svanpe.dicegame.game.GameHandler;
import be.svanpe.dicegame.game.Player;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.apache.commons.lang3.StringUtils;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;



import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class GameWSController {

    GameHandler handler;
    Map<String, Player> players;

    @PostConstruct
    public void init() {
        handler =new  GameHandler(createPlayers());
        players = new HashMap<>();
    }

    @MessageMapping("/register")
    @SendTo("/topic/game")
    public String register(String message){

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);



        try {

            RegisterMessage registerMessage = null;
            registerMessage = objectMapper.readValue(message, RegisterMessage.class);
            Player player = new Player(registerMessage.getPlayerName());
            players.put(registerMessage.getToken(),player);
            handler.getGame().getPlayers().add(player);
            String game = objectMapper.writeValueAsString(handler.getGame());
            return game;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return e.getMessage();
        }


    }


    @MessageMapping("/play")
    @SendTo("/topic/game")
    public String play(String message){


        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        if(StringUtils.isBlank(message)){
            try {
                String game = objectMapper.writeValueAsString(handler.getGame());
                return game;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return e.getMessage();
            }
        }

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


    public static List<Player> createPlayers(){
        List<Player> players = new ArrayList<>();
        players.add(new Player("seb"));
        players.add(new Player("ralf"));
        players.add(new Player("much"));

        return players;
    }

}
