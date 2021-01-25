<template>
  <div class="about">
    <div>
      <div class="cards">

        <div class="row1">
              <div class="column">
                <img src="../assets/carotte card.png" width="280" height="350"/>
              </div>
              <div class="column">
                <img src="../assets/poireau card.png" width="280" height="350"/>
              </div>
              <div class="column">
                <img src="../assets/salade card.png" width="280" height="350"/>
              </div>
        </div>
        <div class="row2">
              <div class="column">
                 <img src="../assets/cornichon card.png" width="280" height="350"/>
              </div>
              <div class="column">
                 <img src="../assets/treffle card.png" width="280" height="350"/>
              </div>
              <div class="column">
                 <img src="../assets/artichaux card.png" width="280" height="350"/>
              </div>
        </div>
      </div>
    </div>
  </div>
</template>
<style scoped>
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
