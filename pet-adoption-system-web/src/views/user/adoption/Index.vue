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
          <lay-field :title="item.petName">
            <div>{{ item.description }}</div>
          </lay-field>
        </div>
      </div>

    </lay-body>
    <lay-page :limit="limit1.a" :total="total1.a" showCount showPage @change="change1"></lay-page>
  </lay-header>
</template>

<script setup>
import {reactive} from "vue";
import router from "../../../config/router.js";
import {list,findAll} from "./api.js";

let dataSource = reactive([])

let limit1 = reactive({
  a:""
})
let total1 = reactive({
  a:""
})
findAll(1,3).then(res=>{
  dataSource.length=0
  dataSource.push(...res.data.list);
  limit1.a = res.data.pageSize
  total1.a = res.data.total
})
const change1 = ({ current, limit }) => {
  findAll(current,3).then(res=>{
    dataSource.length = 0
    dataSource.push(...res.data.list)
    console.log(dataSource);
    limit1.a = res.data.pageSize
    total1.a = res.data.total
  })
}
function onAdoption(item) {
  router.push({
    path: '/info',
    query: item
  })
}
</script>

<style scoped lang="scss">
/*.content-div {
  display: inline-block;
}*/

.layui-header {
  margin-top: 20px;
  height: 600px;
  width: 1500px;

  .layui-body {
    height: 600px;
    display: flex;
    justify-content: center;
    align-items: center;
    &::-webkit-scrollbar{
      display: none;
    }
  }

  .layui-page {
    text-align: center;
  }

  img {
    // border: 5px solid #638863;
    height: 300px;
    width: 300px;
    border-radius: 50%;
    margin-left: 25px;
    margin-right: 50px;
    cursor: pointer;
  }

  img:hover {
    transition: all 0.3s; /* 上浮这个过程需要的时间 */
    box-shadow: 0 16px 32px 0 rgba(48, 55, 66, 0.15); /* 鼠标悬浮时盒子出现的阴影 */
    transform: translate(0, -5px); /* 鼠标悬浮时盒子上移10px */
  }

  text-align: center;

  h1 {
    font-size: 60px;
  }

  .layui-field {
    margin-top: 20px;
    width: 350px;
    height: 210px;
    text-align: center;
  }
}

.content {
  height: 500px;
  width: 1200px;
  /*flex-wrap: wrap;*/
  margin-left: 50px;
  display: flex;
 /* word-wrap: break-word;*/
}
</style>