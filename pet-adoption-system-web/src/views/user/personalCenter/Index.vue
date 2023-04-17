<template>
  <div class="personalCenter">
    <lay-layout>
      <lay-side>
        <lay-menu class="menu" :selected-key="selectedKey" @change-selected-Key="changeSelectedKey"
                  @change-open-keys="changeOpenKeys" v-model:openKeys="openKeys2" :tree="true">
          <lay-menu-item id="10" to="/home"><lay-icon type="layui-icon-home"></lay-icon>&emsp;首&emsp;&emsp;页</lay-menu-item>
          <lay-menu-item id="1" to="center"><lay-icon type="layui-icon-username"></lay-icon>&emsp;个人中心</lay-menu-item>
          <lay-menu-item id="2" to="/information"><lay-icon type="layui-icon-edit"></lay-icon>&emsp;个人信息</lay-menu-item>
          <lay-menu-item id="3" @click="visible2=true"><lay-icon type="layui-icon-location"></lay-icon>&emsp;收货地址</lay-menu-item>
          <lay-menu-item id="4" @click="visible3=true"><lay-icon type="layui-icon-key"></lay-icon>&emsp;修改密码</lay-menu-item>
          <lay-menu-item id="5" to="/application"><lay-icon type="layui-icon-log"></lay-icon>&emsp;领养申请</lay-menu-item>
          <lay-menu-item id="6"><lay-icon type="layui-icon-time"></lay-icon>&emsp;转送宠物</lay-menu-item>
          <lay-menu-item id="8" to="myCart"><lay-icon type="layui-icon-cart-simple"></lay-icon>&emsp;购物车</lay-menu-item>
          <lay-menu-item id="9" @click="exit"><lay-icon type="layui-icon-logout"></lay-icon>&emsp;退出登录</lay-menu-item>
        </lay-menu>
      </lay-side>
      <lay-body>
        <router-view></router-view>
      </lay-body>
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
import {ref} from "vue";
import Information from "./information/Information.vue";
import Address from "./address/Address.vue";
import Password from "./password/Password.vue"
import {layer} from "@layui/layui-vue";
import userLogin from "../../../stores/LoginStore"
import router from "../../../config/router.js";


const exitUserInfo = userLogin()

/*
退出登录，是的话userInfo设置为空，跳转路由，导航守卫自动跳转到
登录界面
 */
function exit() {
  layer.confirm(`是否退出登录？`, {
    btn: [
      {
        text: "是",
        callback(id) {
          exitUserInfo.logout()
          router.push({path: '/login'})
          layer.close(id)
        }
      },
      {
        text: "否",
        callback(id) {
          layer.close(id)
        }
      }

    ],icon:7,
    title: '退出'

  })
}

const visible1 = ref(false)
const visible2 = ref(false)
const visible3 = ref(false)
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
.personalCenter {
  height: 100%;
  width: 100%;
  font-size: 25px;
}

.layui-side {
  min-height: 1000px;
  background-color: #638863;

}

.layui-body {
  min-height: 1000px;
}

.menu {
  background-color: #638863;
}

:deep(.layui-nav) * {
  font-size: 18px !important;
}

</style>