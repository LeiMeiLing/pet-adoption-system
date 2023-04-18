<template>
  <div class="center">
    <img class="background">

      <lay-avatar :src="src" radius @click="signleImg"></lay-avatar>
      <h1>{{ userInfo.username}}</h1>
      <div class="introduction">
        <lay-icon type="layui-icon-list" size="32px"></lay-icon>
        <div>{{userInfo.sign}}</div>
      </div>

      <lay-tab type="brief" v-model="current2" class="tab">
        <lay-tab-item title="我的发布" id="1"><div style="padding:20px">
          <invitation/>
        </div></lay-tab-item>
      </lay-tab>
  </div>

</template>

<script setup>
import {useRouter} from "vue-router";
import { layer } from  "@layui/layui-vue"
import {ref} from "vue";
import Invitation from "../invitation/Invitation.vue";
import userLogin from "../../../../stores/LoginStore.js";
const current2 = ref("1")
const src = "/public/宠物1.png";
const userInfo = userLogin().userInfo;
const signleImg = function() {
  layer.photos("/public/宠物1.png")
}

</script>

<style scoped lang="scss">
.center{
  width: 100%;
  height: 100%;
}


.background{
  height: 350px;
  width: 100%;
  background: url("/public/注册.png");
  position: absolute;
  background-size: cover;
}
.layui-avatar {
  height: 200px;
  width: 200px;
  margin-top:245px;
  margin-left: 30px;
  border: 2px solid #2b562b;
}

h1{
  position:absolute;
  top:355px;
  left:25%;
}
.introduction{
  position: absolute;
  top:455px;
  left:15%;
  display: flex;
}
.tab{
  width: 100%;
  position: absolute;
  border: #1c1919;
  top:500px;
}

</style>