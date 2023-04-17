<template>
  <div class="cat&dog">
    <lay-tab type="brief" v-model="current1" @change="before1">
      <lay-tab-item v-for="a in arr2" :key="a"  :id="a.id" :title="a.title" :closable="a.closable">
      </lay-tab-item>
    </lay-tab>
    <div class="panel-container" shadow="hover" v-for="count in dataSource">
      <lay-panel>
        <img v-bind:src="count.picture">
        <div class="text">
             <h1 class="title" @click="onEssay">
               {{count.title}}
             </h1>
              <div class="description">
                {{count.description}}
              </div>
              <div class="authorAndCreateTime">
                {{count.author}}&emsp;{{count.createTime}}
              </div>
        </div>
      </lay-panel>
    </div>
  </div>

</template>

<script setup>
import {reactive, ref} from 'vue'
const current1 = ref("1")
import {findAll, selectByTime, selectByType} from "./api.js";
import {useRouter} from "vue-router";
const router=useRouter();
let dataSource=reactive([])

selectByTime().then(res=>{
  dataSource.length=0
  dataSource.push(...res.data)
})
const title1 = ref(["最新","养宠知识","宠物百科","宠物健康"])
let type = ref("")
const before1=function a1(id){
    let title = arr2.value[id-1].title;
    if (title==="最新"){
      selectByTime().then(res=>{
        dataSource.length=0
        dataSource.push(...res.data)
      })
    }else{
      selectByType(title).then(res=>{
        console.log(res);
        dataSource.length=0
        dataSource.push(...res.data)
      })
    }




}

const arr2 = ref([
  {id:'1', title:'最新', closable: false},
  {id:'2', title:'养宠知识', closable: false},
  {id:'3', title:'宠物百科', closable: false},
  {id:'4',title: '宠物健康', closable: false}
])

function selectTitle(id){
  return
}

function onEssay(){
  router.push("/essay");
}
</script>

<style scoped lang="scss">

.panel-container > * {
  width: 100%;
  height: 150px;
}
img{
  height: 100%;
  width: 150px;
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
  cursor:pointer;
  font-size: 28px;
}
.description{
  color: #5b5454;
  font-size: 20px;
}
.authorAndCreateTime{
  font-size: 20px;
  color: #5b5454;
}
</style>