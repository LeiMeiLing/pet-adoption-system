<template>
  <div class="albums">
    <div class="album" v-for="count in 4">
      <img class="img">
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';


</script>

<style>
.albums{
  display: flex;
}
.img{
  margin: 40px;
  height: 220px;
  width: 220px;
  boder:5px solid #638863;

}

</style>