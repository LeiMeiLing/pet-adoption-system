<template>
  <div class="essay">
        <lay-breadcrumb separator=">">
          <lay-breadcrumb-item title="主页" to="/home"></lay-breadcrumb-item>
          <lay-breadcrumb-item title="宠物知识" to="/petKnowledge"></lay-breadcrumb-item>
          <lay-breadcrumb-item title="宠物健康" ></lay-breadcrumb-item>
        </lay-breadcrumb>
        <h1 v-model="petKnowledge.title">
          {{title}}
        </h1>
        <div v-model="petKnowledge.description">
          {{description}}
        </div>
        <img v-model="petKnowledge.picture" class="picture" @click="signleImg">
        <div v-model="petKnowledge.content">
          {{content}}
      &emsp;</div>
    <br>
    <div class="icon">
      <lay-icon v-show="praise" type="layui-icon-praise" color="#FFB800" size="40px" @click="praise = !praise"></lay-icon>
      <lay-icon v-show="!praise" type="layui-icon-tread" color="#FFB800" size="40px" @click="praise = !praise"></lay-icon>
      <lay-icon color="#1E9FFF" size="40px" @click="unLike"></lay-icon>
    </div>
    <br>
    <lay-page v-model="currentPage" :limit="limit" :total="total" :show-page="true"></lay-page>

  </div>


</template>

<script setup>
import {reactive, ref} from 'vue'
import { layer } from  "@layui/layui-vue"


const limit = ref(10)
const total = ref(50)
const praise = ref(true)
const currentPage = ref();
const petKnowledge =ref({
  title:'',
  author:'',
  description:'',
  content:'',
  createTime:'',
  essay:'',
  picture:''
})

let status=false;

const signleImg = function(){
  layer.photos("")
}
function onLike(){
  if(status=false){
    layer.msg("赞");
  }
  status=true;
}
function unLike(){
  layer.msg("踩");
}
</script>

<style scoped>
.essay{
  margin: 40px;
  font-size: 32px;
}

.picture{
  width: 400px;
  height: 400px ;
  display: flex;
  justify-content: center;
  align-items: center;
  background: url("/public/注册.png");
  background-size: cover;
}
.icon{
  display: flex;
  justify-content: space-around;
}
</style>