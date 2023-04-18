<template>
  <div>
<div class="up">
  <lay-input :allow-clear="true"  v-model="name.a" placeholder="输入宠物名" >
    <template #prefix>宠物名:</template>
  </lay-input>
    <lay-button type="normal" @click="findA">
      <lay-icon type="layui-icon-search" size="30px" ></lay-icon></lay-button>
</div>

  <div>
    <div id="app">
      <ul v-for="(item,index) in dataSource">
        <li class="item">
          <div class="img_box" @click="click(item)"><img v-bind:src="item.picture" alt=""></div>
          <div style="color: blue">宠物名</div>   <div v-html="item.petName"></div> <ErrorIcon @click="del(item.id)"></ErrorIcon>
        </li>
      </ul>

    </div>

  </div>
  </div>

  <lay-page :limit="limit1.a" :total="total1.a" showCount showPage @change="change1"></lay-page>
</template>


<script setup>
import {onMounted, onUpdated, reactive, ref} from "vue";
import {findAll,delShow} from "./api.js";
import router from "../../../../config/router";
import {ErrorIcon} from '@layui/icons-vue';
import {layer} from "@layui/layui-vue";
import {deleteComment} from "./ImgDisplay/api.js";
import {page} from "../userDisplay/api.js";

function findA(){
  findAll(name.a).then(res=>{
    dataSource.length = 0
    dataSource.push(...res.data.list)
    limit1.a = res.data.pageSize
    total1.a= res.data.total
  })
}


const petIssueDisplay = reactive({
  picture:"",
  content:"",
  commentId:"",
  petName:""
})


let dataSource = reactive([])

function del(row){
  layer.confirm(`是否确认删除？`, {
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

const change1 = ({ current, limit }) => {
  findAll(name.a,current,limit).then(res=>{
    dataSource.length = 0
    dataSource.push(...res.data.list)

  })
}

let name = reactive({
  a:""
})

let limit1 = reactive({
  a:""
})
let total1 = reactive({
  a:""
})

function reload() {
  findAll("").then(res => {
    dataSource.length = 0
    dataSource.push(...res.data.list);
  })
}

findAll().then(res => {
  dataSource.length = 0
  dataSource.push(...res.data.list);
  limit1.a = res.data.pageSize
  total1.a= res.data.total
})

function click(item){
  router.push({
    path:'/imgDisplay',
    query:{
      ...item
    }
  })
}

onMounted(reload)
</script>


<style scoped lang="scss">
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

.up {
  display: flex;

  .layui-input-has-prefix {
    width: 25%;
    margin-left: 20px;
  }

  .layui-select {
    margin-left: 20px;
  }

}

</style>