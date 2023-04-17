<template>
  <ul v-for="(item,index) in dataSource">
    <li class="item">
      <div class="img_box" @click="click(item)"><img v-bind:src="item.picture" alt=""></div>
      <div style="color: blue">宝贝名</div>   <div v-html="item.petName"></div><ErrorIcon @click="del(item.id)"></ErrorIcon>
    </li>
  </ul>
</template>

<script setup>
import {findMyPetShow} from "./api.js";
import useLogin from "../../../../stores/LoginStore.js"
import {reactive} from "vue";
import {findAll} from "../../petShow/api.js";
import router from "../../../../config/router.js";
import {layer} from "@layui/layui-vue";
import {delShow} from "../../../manager/component/CommentManger/api.js";
import {ErrorIcon} from '@layui/icons-vue';

const userInfo = useLogin().userInfo;

let dataSource = reactive([])


function find() {
  findMyPetShow(userInfo.id).then(res=>{
    dataSource.length=0
    dataSource.push(...res.data);
  })
}

findMyPetShow(userInfo.id).then(res=>{
  dataSource.length=0
  dataSource.push(...res.data);
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
.a>.a2{
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