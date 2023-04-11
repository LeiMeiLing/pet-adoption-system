<template>
  <!--主页轮播图-->
  <lay-carousel v-model="active">
    <lay-carousel-item id="1">
      <img src="../../../../../public/1.png">
    </lay-carousel-item>
    <lay-carousel-item id="2">
      <img src="../../../../../public/2.png">
    </lay-carousel-item>
    <lay-carousel-item id="3">
      <img src="../../../../../public/3.png">
    </lay-carousel-item>
    <lay-carousel-item id="4">
      <img src="../../../../../public/4.png">
    </lay-carousel-item>
  </lay-carousel>
</template>

<script setup>
import {ref} from "vue";

const active = ref("1")
</script>

<style scoped lang="scss">
.layui-carousel{
  min-height: 450px;
  img{
    height: 450px;
    width: 100%;
  }
}
</style>