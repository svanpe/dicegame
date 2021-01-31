<template>
  <div>
    <div id="main-content" class="container">
     <!--
      <div>
        <img class="dice" src="../assets/dices.png" aria-hidden="true" />
      </div>

      -->
      <div class="row">
        <div class="col-md-6">
          <form class="form-inline">
            <div class="form-group">
              <label for="connect">Connect to the game:</label>
              <button
                id="connect"
                class="btn btn-default"
                type="submit"
                :disabled="connected == true"
                @click.prevent="connect"
              >
                Connect
              </button>
              <button
                id="disconnect"
                class="btn btn-default"
                type="submit"
                :disabled="connected == false"
                @click.prevent="disconnect"
              >
                Disconnect
              </button>
            </div>
          </form>
        </div>
        <div class="col-md-6">
          <form class="form-inline">
            <div class="form-group">
              <label for="playerName">Your name : </label>
              <input
                type="text"
                id="playerName"
                class="form-control"
                v-model="register_message"
                placeholder="Your name here..."
              />
            </div>
            <button
              id="register"
              class="btn btn-default"
              type="submit"
              @click.prevent="register"
            >
              Register
            </button>
          </form>
        </div>
      </div>

      <div class="row">

        <div class="col-md-4">
          <div id="gamestatus"> {{status}}</div>
        </div>

        <div class="col-md-8">
          <div id="actions">
            <div
              class="alert alert-info"
              v-for="item in actions"
              :key="item"
            >
              {{ item.message }}
            </div>
          </div>
        </div>

        <div class="row">
          <div class="col-sm-3 sidenav">
            <ul class="list-group">
              <li v-for="(player, index) in players"
                    class="alert alert-info list-group-item"
                    :key="player">

                <div class="active" v-if="index==indexOfCurrentPlayer">
                  {{player.name}}  {{countOfTrial}}/3 <span class="badge">{{player.score}}</span>

                </div>

                <div v-else>
                  {{player.name}}  <span class="badge">{{player.score}}</span>
                </div>


                <div v-if="player.name==register_message"><span class="badge">You</span></div>

                <button
                        id="play"
                        class="btn btn-default"
                        type="submit"
                        v-if="player.name==register_message && index==indexOfCurrentPlayer"
                        @click.prevent="sendPlay"
                >
                  Play
                </button>
               </li>

            </ul>

          </div>


          <div class="col-sm-8 mx-2">
            <div id="figures" class="col" />
          </div>

        </div>
        <div class="col-sm-8 mx-2">
          <table id="cards">
            <div class="about">
              <div>
                <div class="column">
                  <img
                    src="../assets/carotte card.png"
                    v-show="card1"
                    width="240"
                    height="320"
                  />
                </div>
                <div class="column">
                  <img
                    src="../assets/poireau card.png"
                    v-show="card2"
                    width="240"
                    height="320"
                  />
                </div>
                <div class="column">
                  <img
                    src="../assets/salade card.png"
                    v-show="card3"
                    width="240"
                    height="320"
                  />
                </div>
                <div class="column">
                  <img
                    src="../assets/cornichon card.png"
                    v-show="card4"
                    width="240"
                    height="320"
                  />
                </div>
                <div class="column">
                  <img
                    src="../assets/treffle card.png"
                    v-show="card5"
                    width="240"
                    height="320"
                  />
                </div>
                <div class="column">
                  <img
                    src="../assets/artichaux card.png"
                    v-show="card6"
                    width="240"
                    height="320"
                  />
                </div>
              </div>
            </div>
          </table>
        </div>
      </div>

      <div class="row">
        <div class="col-md-12">
          <div class="panel-group">
            <div class="panel panel-default">
              <div class="panel-heading">
                <h4 class="panel-title">
                  <a data-toggle="collapse" href="#collapse1"
                    >Collapsible panel</a
                  >
                </h4>
              </div>
              <div id="collapse1" class="panel-collapse collapse">
                <div class="panel-body">
                  <table id="conversation" class="table-table-striped">
                    <thead>
                      <tr>
                        <th>messages received</th>
                      </tr>
                    </thead>
                    <tbody id="games" class="collapse"></tbody>
                  </table>
                </div>
                <div class="panel-footer">Panel Footer</div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";

export default {
  name: "websocketdemo",
  data() {
    return {

      dices: [],
      cards: [],
      players: [],
      actions: [],
      status: null,
      indexOfCurrentPlayer: null,
      countOfTrial: null,
      received_messages: [],
      register_message: null,
      connected: false,
      token: this.guid(),
      btn: true,
      card1: false,
      card2: false,
      card3: false,
      card4: false,
      card5: false,
      card6: false,
    };
  },
  mounted() {
    let diceScript = document.createElement("script");
    diceScript.setAttribute("src", "https://kit.fontawesome.com/31d122af8a.js");
    diceScript.setAttribute("crossorigin", "anonymous");
    document.head.appendChild(diceScript);
  },
  methods: {
    guid() {
      function _p8(s) {
        var p = (Math.random().toString(16) + "000000000").substr(2, 8);
        return s ? "-" + p.substr(0, 4) + "-" + p.substr(4, 4) : p;
      }
      return _p8() + _p8(true) + _p8(true) + _p8();
    },

    register() {
      console.log("register message:" + this.register_message);
      if (this.stompClient && this.stompClient.connected) {
        const msg = { playerName: this.register_message };
        msg.token = this.token;
        console.log(JSON.stringify(msg));
        this.stompClient.send("/app/register", JSON.stringify(msg));
      }
    },
    connect() {
      this.socket = new SockJS("http://localhost:5078/dicegame-websocket");
      this.stompClient = Stomp.over(this.socket);
      this.stompClient.connect(
        {},
        (frame) => {
          this.connected = true;
          console.log(frame);
          this.stompClient.subscribe("/topic/game", (greeting) => {
            this.showGreeting(greeting.body);
            var thegame = JSON.parse(greeting.body);

            this.status = thegame.status;
            this.actions = thegame.actions;
            this.players = thegame.players;
            this.countOfTrial= thegame.countOfTrialForPlayer;
            this.indexOfCurrentPlayer = thegame.indexOfCurrentPlayer;

            //this.showStatus(thegame.status, thegame.actions);
            this.showDices(thegame.dices);
            /*
            this.showPlayers(
              thegame.players,
              thegame.indexOfCurrentPlayer,
              thegame.countOfTrialForPlayer
            );*/
            this.showCards(thegame.cards);
          });
        },
        (error) => {
          console.log(error);
          this.connected = false;
        }
      );
    },
    disconnect() {
      if (this.stompClient) {
        this.stompClient.disconnect();
      }
      this.connected = false;
    },
    tickleConnection() {
      this.connected ? this.disconnect() : this.connect();
    },
    showGreeting(message) {
      $("#games").append("<tr><td>" + message + "</td></tr>");
    },
    showStatus(status, actions) {
      $("#gamestatus").empty();
      gamestatus.append(status);
      $("#actions").empty();
      Object.keys(actions).forEach((key) => {
        let action = actions[key];
        this.received_messages.push(action.message);
      });
    },
    showDices(dices) {
      $("#figures").empty();

      var btnIndex = 0;

      $("#figures").append(
        '<div id="btn-group-dices" class="btn-group-toggle" data-toggle="buttons">'
      );
      Object.keys(dices).forEach((key) => {
        let dice = dices[key];
        $("#btn-group-dices").append(
          '<input type="checkbox" data-toggle="switchbutton" id="diceBtn' +
            btnIndex +
            '" checked>' +
            '<i class="fas fa-dice-' +
            dice.figure +
            '" style="font-size:60px;color:' +
            dice.color +
            ';"></i>'
        );
        btnIndex++;
      });
    },
    showPlayers(players, activePlayer, countOfTrial) {
      $("#players").empty();

      $("#players").append('<ul class="list-group">');

      var index = 0;
      Object.keys(players).forEach((key) => {
        let player = players[key];
        console.log(player.name);

        var aclass = "";
        var count = "";
        var youBadge = "";
        if (index == activePlayer) {
          aclass = " active";
          count = countOfTrial + "/3";

          if (this.register_message == player.name && players.length > 1) {
            this.btn = true;
            youBadge = '<span class="badge">You</badge>';
          } else {
            this.btn = false;
          }
        }
        $("#players").append(
          '<li class="list-group-item' +
            aclass +
            '" >' +
            player.name +
            " " +
            count +
            ' <span class="badge">' +
            player.score +
            "</span>" +
            youBadge +
            "</li>"
        );
        index++;
      });

      $("#players").append("</ul>");
    },
    showCards(cards) {
      (this.card1 = false),
        (this.card2 = false),
        (this.card3 = false),
        (this.card4 = false),
        (this.card5 = false),
        (this.card6 = false),
        Object.keys(cards).forEach((key) => {
          let card = cards[key];
          console.log(card.name);

          if (card.name == "carotte") {
            this.card1 = true;
          } else if (card.name == "poireau") {
            this.card2 = true;
          } else if (card.name == "salade") {
            this.card3 = true;
          } else if (card.name == "cornichon") {
            this.card4 = true;
          } else if (card.name == "treffle") {
            this.card5 = true;
          } else if (card.name == "artichaux") {
            this.card6 = true;
          }
        });
    },
    sendPlay() {
      var playMessage = new Object();
      playMessage.token = this.token;
      playMessage.diceToKeep = new Array();

      if ($("#diceBtn0") != null && $("#diceBtn0").prop("checked")) {
        playMessage.diceToKeep.push(0);
      }
      if ($("#diceBtn1") != null && $("#diceBtn1").prop("checked")) {
        playMessage.diceToKeep.push(1);
      }
      if ($("#diceBtn2") != null && $("#diceBtn2").prop("checked")) {
        playMessage.diceToKeep.push(2);
      }
      if ($("#diceBtn3") != null && $("#diceBtn3").prop("checked")) {
        playMessage.diceToKeep.push(3);
      }
      if ($("#diceBtn4") != null && $("#diceBtn4").prop("checked")) {
        playMessage.diceToKeep.push(4);
      }
      if ($("#diceBtn5") != null && $("#diceBtn5").prop("checked")) {
        playMessage.diceToKeep.push(5);
      }

      this.stompClient.send("/app/play", JSON.stringify(playMessage));
    },
  },
};
</script>

<style scoped>
.dice {
  height: 400px;
}
.column {
  float: left;
  width: 33.33%;
  padding: 5px;
}

/* Clear floats after image containers */
.row1::after {
  content: "";
  clear: both;
  display: table;
}
.row2::after {
  content: "";
  clear: both;
  display: table;
}
</style>