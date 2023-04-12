<template>
  <lay-layout>
    <lay-header>
      <lay-menu class="menu" v-model:selected-key="selectedKey" v-model:open-keys="openKeys">
        <lay-space :size="customSize">
          <lay-menu-item><span class="menu-item">宠物领养系统</span></lay-menu-item>
      <lay-menu-item id="1" to="/home">主页</lay-menu-item>
      <lay-menu-item id="2" to="petKnowledge">宠物知识</lay-menu-item>
      <lay-menu-item id="3" to="/adoption">领养中心</lay-menu-item>
      <lay-menu-item id="4" to="stores">宠物商铺</lay-menu-item>
        <lay-menu-item id="5" to="/transfer">宠物转让</lay-menu-item>
        <lay-menu-item id="6" to="">宠物秀</lay-menu-item>
          <lay-button>个人中心</lay-button>
        </lay-space>
    </lay-menu>
    </lay-header>
    <lay-body>
     <router-view/>
    </lay-body>
  </lay-layout>
</template>

<script setup>
import {ref} from "vue";
const selectedKey = ref("1")
const openKeys = ref(["1"])
const customSize = ref(50);
</script>

<style scoped lang="scss">
.layui-header{

}
.menu{
  background-color:#638863 ;
}
:deep(.layui-nav) *{
  font-size: 18px !important;
  color: white;
}
.menu-item{
  font-size: 30px !important;
  font-family: 黑体;
}
.layui-body{
  min-height: 1000px;
}
</style>