<template>
  <div class="out">
    <img class="img" v-bind:src="route.query.picture" alt="" >
    <div class="query">
      {{ route.query }}
    </div>
  </div>

</template>

<script setup>
import {useRoute} from "vue-router";
import router from "../../../../config/router";

const route = useRoute();

</script>

<style scoped>
.out{
  display: flex;
  flex-direction: column;
}

.out .img{
  position: absolute;
  weight: 150px;
  height: 150px;
  top: 20%;
  left: 45%;
  transform:translate(-50%,-12%);
  /*transform: scale(1);*/
  max-width: 700px;
}

</style>