<template>
  <div class="header">
    <h1 class="title">欢迎您！管理员</h1>
    <lay-dropdown>
      <lay-button border="green">
        <lay-avatar :src="src" radius></lay-avatar>
        <div class="name">a</div>
      </lay-button>
      <template #content>
        <lay-dropdown-menu>
          <lay-dropdown-menu-item>个人中心</lay-dropdown-menu-item>
          <lay-dropdown-menu-item @click="exit">退出登录</lay-dropdown-menu-item>
        </lay-dropdown-menu>
      </template>
    </lay-dropdown>
  </div>
</template>

<script setup>
import {layer} from "@layui/layui-vue";
import router from "../../../config/router.js";
import userLogin from "../../../stores/LoginStore.js";

const src = "https://foruda.gitee.com/avatar/1677022544584087390/4835367_jmysy_1578975358.png"

const exitManagerInfo = userLogin()
function exit() {
  layer.confirm(`是否退出登录？`, {
    btn: [
      {
        text: "是",
        callback(id) {
          exitManagerInfo.managerOut()
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
</script>

<style scoped lang="scss">
.header{
  height: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  .layui-btn{
    margin-right: 20px;
  }
}
</style>