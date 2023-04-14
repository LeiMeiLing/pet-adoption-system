<template>
  <div class="personalCenter">
    <lay-layout>
      <lay-side >
        <lay-menu  class="menu" :selected-key="selectedKey" @change-selected-Key="changeSelectedKey" @change-open-keys="changeOpenKeys" v-model:openKeys="openKeys2" :tree="true">
          <lay-menu-item id="1" to="center">个人中心</lay-menu-item>
          <lay-menu-item id="2"  @click="visible1=true">个人信息</lay-menu-item>
          <lay-menu-item id="3"  @click="visible2=true">收货地址</lay-menu-item>
          <lay-menu-item id="4"  @click="visible3=true">修改密码</lay-menu-item>
          <lay-menu-item id="5" >领养申请</lay-menu-item>
          <lay-menu-item id="6" >转送宠物</lay-menu-item>
          <lay-menu-item id="7"  @click="visible1=true">退出登录</lay-menu-item>
      </lay-menu></lay-side>
      <lay-body>
       <router-view></router-view>
      </lay-body>
      <lay-layer :title="'个人信息'" v-model="visible1">
        <information/>
      </lay-layer>
      <lay-layer :title="'收货地址'" v-model="visible2">
        <Address/>
      </lay-layer>
      <lay-layer :title="'修改密码'" v-model="visible3">
        <Password/>
      </lay-layer>
    </lay-layout>


    </div>

</template>

<script setup>
import { ref} from "vue";
import Information from "./information/Information.vue";
import Address from "./address/Address.vue";
import Password from "./password/Password.vue"
import {layer} from "@layui/layui-vue";

const visible1= ref(false)
const visible2= ref(false)
const visible3= ref(false)
const openKeys2 = ref(["1"])
const selectedKey = ref("1")
const changeSelectedKey = (val) => {
  selectedKey.value = val;
}

const changeOpenKeys = (val) => {
  openKeys2.value = val;
}


</script>

<style scoped lang="scss">
.personalCenter{
  height: 100%;
  width: 100%;
  font-size: 25px;
}
.layui-side{
  min-height: 1000px;
  background-color: #638863;

}
.layui-body{
  min-height: 1000px;
}
.menu{
  background-color: #638863;
}
:deep(.layui-nav) *{
  font-size: 18px !important;
}

</style>