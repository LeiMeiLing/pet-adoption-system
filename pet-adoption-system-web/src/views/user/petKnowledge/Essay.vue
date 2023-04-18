<template>
  <div class="essay">
    <div class="text1">
      <lay-breadcrumb separator=">">
        <lay-breadcrumb-item title="主页" @click="toHome"></lay-breadcrumb-item>
        <lay-breadcrumb-item title="宠物知识" @click="toPetKnowledge"></lay-breadcrumb-item>
        <lay-breadcrumb-item >{{route.query.essayType}}</lay-breadcrumb-item>
      </lay-breadcrumb>
      <h1>
        {{route.query.title}}
      </h1>
      <div class="authorAndCreateTime">
        <p>{{route.query.author}}</p><lay-icon type="layui-icon-chart"></lay-icon>{{route.query.createTime}}
        <lay-icon type="layui-icon-chart"></lay-icon>{{route.query.essayType}}
      </div>
      <div class="description">
        &emsp;&emsp;{{route.query.description}}
      </div>
      <img :src="route.query.picture" class="picture" @click="signleImg" >
      <div>&emsp;&emsp;{{route.query.content}}</div>
      <br>
      <div class="icon">
        <div class="like"><lay-icon v-show="like" type="layui-icon-heart" color="red" size="40px" @click="like= !like" ></lay-icon>
          <lay-icon  v-show="!like" type="layui-icon-heart-fill" color="red" size="40px" @click="like = !like"></lay-icon>喜欢</div>
        <div class="collect">
          <lay-icon v-show="collect" type="layui-icon-rate" color="#ecbe08" size="40px" @click="collect= !collect"></lay-icon>
          <lay-icon  v-show="!collect" type="layui-icon-rate-solid" color="#ecbe08" size="40px" @click="collect = !collect"></lay-icon>收藏
        </div>
      </div>
<!--      <br>-->
<!--      <lay-page v-model="currentPage" :limit="limit" :total="total" :show-page="true"></lay-page>-->
    </div>
      <div class="text2">
        <div class="label">
          <lay-icon  type="layui-icon-note" color="#e1ba59" size="32px" ></lay-icon><div>相关推荐</div>
        </div>
        <br>
        <lay-field :title="count.essayType"  v-for="count in dataSource"><p class="title" @click="onTitle(count)">{{ count.title }}</p></lay-field>
      </div>

  </div>


</template>

<script setup>
import {reactive, ref} from 'vue'
import { layer } from  "@layui/layui-vue"
import {useRoute} from "vue-router";
import router from "../../../config/router.js";
import {createMessages} from "./api.js";


const route=useRoute()
const limit = ref(10)
const total = ref(50)
const like = ref(true)
const collect = ref(true)
const currentPage = ref();
let dataSource=reactive([])

const signleImg = function(){
  layer.photos("")
}
function toHome(){
  router.push("/home")
}
function toPetKnowledge(){
  router.push("/petKnowledge")
}

createMessages().then(res=>{
  dataSource.length=0
  dataSource.push(...res.data)
})

function onTitle(count){
  router.push({
    path:'/essay',
    query:count
  })

}

</script>

<style scoped lang="scss">
.essay{
  margin-top: 30px;
  margin-right:100px;
  margin-left: 100px;
  font-size: 20px;
  display: flex;
  justify-content: space-between;
}
.text1{
  width: 80%;
}
h1{
  font-size: 30px;
}
.authorAndCreateTime{
  margin-top: 10px;
  display: flex;
  font-size: 18px;
  color: #5b5454;
  line-height: 17px;
}
p{
  color: #eec41d;
}
.description{
  margin-top: 10px;
}
img{
  width: 500px;
  height: 350px ;
  margin-left: 30%;
}
.icon{
  display: flex;
  justify-content: space-around;
}
.like,.collect{
  display: flex;
  line-height: 50px;
}
.text2{
  width: 18%;
  border: 2px solid #79C48C;
  border-radius: 5px;
  font-size: 22px;
  .label{
    height: 80px;
    width: 100%;
    background-color: #638863;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  .layui-field{
    color: #638863;
    border-color: #638863;
  }
  p{
    color: #1c1919;
    cursor: pointer;
  }

}


</style>