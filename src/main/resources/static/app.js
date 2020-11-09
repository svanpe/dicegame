var stompClient = null;
var token = guid();

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/dicegame-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        $("#token").val(token);
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/game', function (greeting) {
            showGreeting(greeting.body);
            var thegame = JSON.parse(greeting.body);
            $("#gamestatus").empty();
            $("#gamestatus").append("<h2>"+thegame.status+"</h2>")
            showDices(thegame.dices);
            showPlayers(thegame.players, thegame.indexOfCurrentPlayer, thegame.countOfTrialForPlayer);
            showCards(thegame.cards);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function register() {
    var registerMsg = new Object();
    registerMsg.token = token;
    registerMsg.playerName = $("#playerName").val();

    stompClient.send("/app/register", {}, JSON.stringify(registerMsg));
}
function sendName() {
    stompClient.send("/app/play", {}, $("#name").val());
}

function sendPlay() {

    var playMessage = new Object();
    playMessage.token = token;
    playMessage.diceToKeep = new Array();

    stompClient.send("/app/play", {}, JSON.stringify(playMessage));
}

function showGreeting(message) {
    $("#games").append("<tr><td>" + message + "</td></tr>");
}

function showDices(dices) {

    if (dices == null) return;

    $("#figures").empty();

    var btnIndex = 0;

    dices.forEach(function (dice) {
        console.log(dice.figure);


        $("#figures")
            .append(" <button id=\"diceBtn" + btnIndex + "\" class=\"btn btn-primary\" data-toggle=\"button\" aria-pressed=\"false\" >"
            + "<i class=\"fas fa-dice-" + dice.figure + "\" style=\"font-size:60px;color:" + dice.color + ";\"></i>"
            + "</button>");

        btnIndex++;

    });

}

    function showPlayers(players,activePlayer,countOfTrial){

        if(players==null) return;

        $("#players").empty();

        $("#players").append("<ul class=\"list-group\">");

        var index=0;

        players.forEach(function(player){
                console.log(player.name);

                var aclass="";
                var count="";

                if(index==activePlayer){
                    aclass=" active";
                    count = countOfTrial + "/3"
                }

                $("#players").append("<li class=\"list-group-item" + aclass + "\" >" +player.name
                    + " " + count + " <span class=\"badge\">"+player.score+"</span>" +
                    "</li>");

                index++;
        });

        $("#players").append("</ul>");

    }

function showCards(cards){

    if(cards==null) return;

    $("#cards").empty();

    cards.forEach(function(card){
        console.log(card.name);


        $("#cards").append("<td><div class=\"well\" style=\"width: 18rem;\">"
            +"<h2>"+card.name+"</h2>"
            +"<p>" + card.explaination + "</p>"
            +"<span class=\"badge\">"+card.points+"</span>"
            +"</div></td>");
    });


}

/**
 * Generates a GUID string.
 * @returns {string} The generated GUID.
 * @example af8a8416-6e18-a307-bd9c-f2c947bbb3aa
 * @author Slavik Meltser.
 * @link http://slavik.meltser.info/?p=142
 */
function guid() {
    function _p8(s) {
        var p = (Math.random().toString(16)+"000000000").substr(2,8);
        return s ? "-" + p.substr(0,4) + "-" + p.substr(4,4) : p ;
    }
    return _p8() + _p8(true) + _p8(true) + _p8();
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
    $( "#register" ).click(function() { register(); });
    $( "#play" ).click(function() { sendPlay(); });
});