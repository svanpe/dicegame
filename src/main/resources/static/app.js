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
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/game', function (greeting) {
            showGreeting(greeting.body);
            var thegame = JSON.parse(greeting.body);
            showStatus(thegame.status, thegame.actions);
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


function sendPlay() {

    var playMessage = new Object();
    playMessage.token = token;
    playMessage.diceToKeep = new Array();

    if($("#diceBtn0")!=null && $("#diceBtn0").prop('checked')){
        playMessage.diceToKeep.push(0);
    }
    if($("#diceBtn1")!=null && $("#diceBtn1").prop('checked')){
        playMessage.diceToKeep.push(1);
    }
    if($("#diceBtn2")!=null && $("#diceBtn2").prop('checked')){
        playMessage.diceToKeep.push(2);
    }
    if($("#diceBtn3")!=null && $("#diceBtn3").prop('checked')){
        playMessage.diceToKeep.push(3);
    }
    if($("#diceBtn4")!=null && $("#diceBtn4").prop('checked')){
        playMessage.diceToKeep.push(4);
    }
    if($("#diceBtn5")!=null && $("#diceBtn5").prop('checked')){
        playMessage.diceToKeep.push(5);
    }

    stompClient.send("/app/play", {}, JSON.stringify(playMessage));
}

function showGreeting(message) {
    $("#games").append("<tr><td>" + message + "</td></tr>");
}

function showStatus(status, actions){
    $("#gamestatus").empty();
    $("#gamestatus").append("<h2>"+status+"</h2>")

    $("#actions").empty();

    actions.forEach(function (action) {

        $("#actions")
            .append("<div class=\"alert alert-info\">" + action.message + "</div>");


    });
}

function showDices(dices) {

    if (dices == null) return;

    $("#figures").empty();

    var btnIndex = 0;

    $("#figures").append("<div id=\"btn-group-dices\" class=\"btn-group-toggle\" data-toggle=\"buttons\">");

    dices.forEach(function (dice) {

        $("#btn-group-dices")
            .append("<input type=\"checkbox\" data-toggle=\"switchbutton\" id=\"diceBtn"+ btnIndex +"\" checked>"
                + "<i class=\"fas fa-dice-" + dice.figure + "\" style=\"font-size:60px;color:" + dice.color + ";\"></i>"
           );

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

                    if($("#playerName").val()==player.name){
                        $("#play").show();
                    } else {
                        $("#play").hide();
                    }
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
            +"<h2><strong>"+card.name+"</strong></h2>"
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
    $( "#register" ).click(function() { register(); });
    $( "#play" ).click(function() { sendPlay(); });
});