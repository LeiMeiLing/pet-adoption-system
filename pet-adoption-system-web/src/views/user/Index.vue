<template>
  <lay-layout class="main">
    <lay-header>
      <lay-menu v-model:selected-key="selectedKey" v-model:open-keys="openKeys">
      <lay-menu-item id="1" to="main">主页</lay-menu-item>
      <lay-menu-item id="2" to="">宠物知识</lay-menu-item>
      <lay-menu-item id="3" to="">领养中心</lay-menu-item>
      <lay-menu-item id="4" to="stores">宠物用品商城</lay-menu-item>
        <lay-menu-item id="5" to="">个人中心</lay-menu-item>
    </lay-menu>
    </lay-header>
    <lay-body>
        <router-view></router-view>
<!--    以下注释代码为轮播图,应放在某一组件中,而非放在此处    -->
<!--
      <lay-carousel v-model="active">
      <lay-carousel-item id="1">
        <div style="color: white;text-align: center;width:100%;height:300px;line-height:300px;background-color:#009688;">图片</div>
      </lay-carousel-item>
      <lay-carousel-item id="2">
        <div style="color: white;text-align: center;width:100%;height:300px;line-height:300px;background-color:#5FB878;">图片</div>
      </lay-carousel-item>
      <lay-carousel-item id="3">
        <div style="color: white;text-align: center;width:100%;height:300px;line-height:300px;background-color:#FFB800;">图片</div>
      </lay-carousel-item>
      <lay-carousel-item id="4">
        <div style="color: white;text-align: center;width:100%;height:300px;line-height:300px;background-color:#FF5722;">图片</div>
      </lay-carousel-item>
    </lay-carousel>-->
    </lay-body>
  </lay-layout>
</template>

<script setup>
import {ref} from "vue";
const selectedKey = ref("5")
const openKeys = ref(["7"])
const active = ref("1")
</script>

<style scoped>
.layui-body{
    min-height: 615px;
}
</style>