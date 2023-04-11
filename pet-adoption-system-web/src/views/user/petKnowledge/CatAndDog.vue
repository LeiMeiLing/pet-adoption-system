<template>
  <div class="cat&dog">
    <lay-tab type="brief" v-model="current1">
      <lay-tab-item title="最新" id="1"></lay-tab-item>
      <lay-tab-item title="养宠知识" id="2"></lay-tab-item>
      <lay-tab-item title="宠物百科" id="3"></lay-tab-item>
      <lay-tab-item title="宠物健康" id="4"></lay-tab-item>
    </lay-tab>
    <div class="panel-container" shadow="hover" v-for="count in 4">
      <lay-panel>
        <div class="picture"></div>
        <div class="text">
             <h1 class="title" @click="onEssay" >
               喵星人的日常
             </h1>
              <div class="content">
                猫咪们会获得“至尊成年猫”，“至尊猫崽”，甚至“至尊阉猫”的称号。
              </div>
              <div class="author">
                Ayla Angelos ·11/21 08:57
              </div>
        </div>
      </lay-panel>
    </div>
  </div>

</template>

<script setup>
import { ref } from 'vue'
const current1 = ref("1")
import {useRouter} from "vue-router";
const router=useRouter();
function onEssay(){
  router.push("/essay");
}
</script>

<style scoped lang="scss">

.panel-container > * {
  width: 100%;
  height: 150px;
}
.picture{
  height: 100%;
  width: 150px;
  background: url("/public/注册.png");
  background-size: cover;
}
.layui-panel{
  display: flex;
}
.text{
  margin-left: 50px;
  &>div{
    margin-top: 30px;
  }
}
.title{
  font-size: 28px;

}
.content{
  color: #5b5454;
  font-size: 20px;
}
.author{
  font-size: 20px;
  color: #5b5454;
}
</style>