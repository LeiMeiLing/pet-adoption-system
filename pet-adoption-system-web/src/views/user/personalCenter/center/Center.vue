<template>
  <div class="center">
    <img class="background">
    <div  class="return" @click="onReturn">
      <lay-icon color="#fff" size="45px" type="layui-icon-return"></lay-icon><p>返回</p>
    </div>
    <img src="/public/宠物1.png" @click="signleImg" class="headPicture">
    <h1>我的小喵</h1>
    <div class="introduction">
      <lay-icon type="layui-icon-list" size="28px"></lay-icon>
      <div>七彩文鸟是世界上著名的宠物鸟，但是由于品种少，是濒临物种，我们常见的家养七彩文鸟，基本上都是人工培育的。</div>
    </div>

      <lay-tab type="brief" v-model="current2" class="tab">
        <lay-tab-item title="我的发布" id="1"><div style="padding:20px">
          <invitation/>
        </div></lay-tab-item>
        <lay-tab-item title="相册" id="2"><div style="padding:20px">
          <album/>
        </div></lay-tab-item>
      </lay-tab>

  </div>

</template>

<script setup>
import {useRouter} from "vue-router";
import { layer } from  "@layui/layui-vue"
import {ref} from "vue";
import Invitation from "../invitation/Invitation.vue";
import Album from "./Album.vue";
const current2 = ref("1")

const router=useRouter();
const headPicture=ref("/public/宠物1.png")
function onReturn(){
  router.push('/');
}
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
.return{
  position: relative;
  display: flex;
  font-size: 38px;
  color: #ffffff;
  left:2%;
}
.headPicture{
  height: 150px;
  width: 150px;
  border-radius: 50%;
  position: relative;
  top:220px;
  left: 5%;
}
h1{
  position: absolute;
  top:355px;
  left:28%;

}
.introduction{
  position: absolute;
  top:420px;
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