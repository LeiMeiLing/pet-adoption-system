<template>
  <div class="center">
    <img class="background">
    <lay-avatar :src="src" radius></lay-avatar>
    <h1>我的小喵</h1>
    <div class="introduction">
      <lay-icon type="layui-icon-list" size="28px"></lay-icon>
      <div>七彩文鸟是世界上著名的宠物鸟，但是由于品种少，是濒临物种，我们常见的家养七彩文鸟，基本上都是人工培育的。</div>
    </div>

      <lay-tab type="brief" v-model="current2" class="tab">
        <lay-tab-item title="我的发布" id="1" ><div style="padding:20px">

<div class="a2">
          <ul v-for="(item,index) in dataSource" >
            <li class="item" >
              <div class="img_box" @click="click(item)"><img v-bind:src="item.picture" alt=""></div>
              <div style="color: blue">宝贝名</div>   <div v-html="item.petName"></div><ErrorIcon @click="del(item.id)"></ErrorIcon>
            </li>
          </ul>
</div>

          <div >
            <lay-page :limit="limit1.a" :total="total1.a" showCount showPage @change="change1" :limits="limits1"></lay-page>
          </div>



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
import {findMyPetShow} from "../center/api.js";
import useLogin from "../../../../stores/LoginStore.js"
import {reactive} from "vue";
import {findAll} from "../../petShow/api.js";
import router from "../../../../config/router.js";
import {delShow} from "../../../manager/component/CommentManger/api.js";
import {ErrorIcon} from '@layui/icons-vue';

const current2 = ref("1")

const signleImg = function() {
  layer.photos("/public/宠物1.png")
}

let limit1 = reactive({
  a:""
})
let total1 = reactive({
  a:""
})

let limits1 = ref([6])



const userInfo = useLogin().userInfo;

let dataSource = reactive([])


const change1 = ({ current, limit }) => {
  findMyPetShow(userInfo.id,current,6).then(res=>{
    dataSource.length = 0
    dataSource.push(...res.data.list)
    console.log(dataSource);
    limit1.a = res.data.pageSize
    total1.a = res.data.total
  })
}


function find() {
  findMyPetShow(userInfo.id).then(res=>{
    dataSource.length=0
    dataSource.push(...res.data,list);
  })
}

findMyPetShow(userInfo.id,1,6).then(res=>{
  dataSource.length=0
  dataSource.push(...res.data.list);
  limit1.a = res.data.pageSize
  total1.a = res.data.total
})

function click(item){
  router.push({
    name:'commentA',
    query:{
      ...item
    }
  })
}

function del(row){
  layer.confirm(`是否确认删除？PS:删除会将别人的评论一并删除哦`, {
    btn: [
      {
        text: "是",
        callback(id) {
          delShow(row)
          layer.close(id)
          layer.msg("删除成功")
          reload()
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
  margin-bottom: 5px;
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

/*****************************/
.a2{
  height: 300px;
}
#bbc{
  float: right;
}
.div1{
  margin: 0 15%;
  display: flex;
  .div2{
    width: 30%;
    height: 250px;
    border: solid 5px #cccccc;
  }
}
.selectShopping{
  display: flex;
  margin: 10px auto;
  width: 50%;
  .layui-input{
    margin: 0 5px;
  }
}

* {
  margin: 0;
  padding: 0;
  list-style: none;
}

body {
  background: #e3e4e5;
  margin: auto;
}

#app {
  width: 1000px;
  margin-left: auto;
  margin-right: auto;
  overflow: hidden;
}


.item {
  float: left;
  width: 190px;
  height: 266px;
  margin: 0 5px 8px;
  text-align: center;
  background: #fff;
}

.item .img_box {
  width: 120px;
  height: 120px;
  margin: 30px auto;
}

.img_box img {
  width: 100%;
  height: 100%;
}

.item p {
  font-size: 12px;
  line-height: 20px;
  height: 40px;
  padding: 0 10px;
  /* white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis; */
  display: -webkit-box;
  overflow: hidden;
  text-overflow: ellipsis;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}

.more2_info_self {
  background-color: #e1251b;
  border-radius: 2px;
  color: #fff;
  padding: 0 5px;
  margin-right: 4px;
  line-height: 16px;
  height: 16px;
  font-size: 12px;
  font-style: normal;
}

</style>