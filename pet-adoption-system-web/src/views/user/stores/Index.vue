<template>

<div>
    <div class="selectShopping" >
      <lay-input placeholder="请输入商品名进行查询" v-model="goodsInfo.goodsName" prefix-icon="layui-icon-search">
      </lay-input>
      <lay-input placeholder="请输入商品类型进行查询" v-model="goodsInfo.goodsType" prefix-icon="layui-icon-search">
      </lay-input>
      <lay-button type="primary" @click="getSome">搜索</lay-button>
    </div>


    <div id="app">
        <ul v-for="(item,index) in dataSource" >
            <li class="item" v-if="item.goodsStatus===1">
                <div class="img_box" @click="click(item)"><img v-bind:src="item.goodsPicture" alt=""></div>
                <p v-html="item.goodsDesc"></p>
                <span :style="{color:'red'}">&yen;{{item.goodsPrice}}</span>
            </li>
        </ul>
    </div>

</div>
</template>


<script setup>
import {reactive, ref} from "vue";
import {findAll, findSome} from "./api.js";
import router from "../../../config/router.js";

let dataSource = reactive([])
findAll().then(res=>{
    dataSource.length=0
    dataSource.push(...res.data.list);
})

function click(item){
    router.push({
        path:'/storesDetail',
        query:item
    })
}
function getSome(){
  findSome(goodsInfo.goodsName,goodsInfo.goodsType).then(res=>{
  dataSource.length=0
  dataSource.push(...res.data.list);
})
}
const goodsInfo = reactive({
    id:0,
    goodsType:"",
    goodsName: "",
    goodsPicture:"",
    goodsPrice:"",
    goodsDesc:"",
    goodsStatus:""

});

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

</style>