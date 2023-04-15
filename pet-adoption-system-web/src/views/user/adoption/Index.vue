<template>
<!--领养中心-->
  <lay-header>
    <h1>领养中心</h1>
    <lay-body>
      <div class="content">
        <div v-for="(item,key,index) in dataSource" class="content-div">
          <div>
            <img @click="onAdoption(item)" :src="item.petPicture">
          </div>
          <lay-field>
            <h2>{{item.petName}}</h2>
            {{item.description}}
          </lay-field>
        </div>
      </div>

<!--      <div>
        <div>
        <img src="../../../../public/宠物1.png">
          <lay-field title="Kitty">描述
          </lay-field>
      </div>
      </div>-->
    </lay-body>
  </lay-header>
</template>

<script setup>
import {reactive} from "vue";
import router from "../../../config/router.js";
import {list} from "./api.js";

let dataSource = reactive([])
list().then(res=>{
  dataSource.length=0
  dataSource.push(...res.data);
})
function onAdoption(item){
  router.push({
    path:'/info',
    query: item
  })
}
</script>

<style scoped lang="scss">
.content-div{
 display: inline-block;
}
.layui-header{
  margin-top: 30px;
  height: 600px;
  width: 1500px;
.layui-body{
  height: 1000px;
  display: flex;
  justify-content: center;
  align-items: center;
}
.layui-page{
  text-align: center;
}
  img{
    border: 5px solid #638863;
    height: 300px;
    width: 300px;
    border-radius: 50%;
    margin-left: 20px;
    margin-right: 50px;
    cursor: pointer;
  }
  img:hover{
    transition: all 0.3s;/* 上浮这个过程需要的时间 */
    box-shadow: 0 16px 32px 0 rgba(48, 55, 66, 0.15);/* 鼠标悬浮时盒子出现的阴影 */
    transform: translate(0, -5px);/* 鼠标悬浮时盒子上移10px */
  }
  text-align: center;
h1{
  font-size: 60px;
  margin-bottom: 20px;
}
  h2{
    margin-bottom: 10px;
  }
.layui-field{
  margin-top: 30px;
   width: 350px;
   height: 100px;
   text-align: center;
}
}
.content{
  height: 1000px;
  width: 1200px;
  flex-wrap: wrap;
  margin-left: 50px;
  display: flex;
  overflow: hidden;
  word-wrap: break-word;
}
</style>