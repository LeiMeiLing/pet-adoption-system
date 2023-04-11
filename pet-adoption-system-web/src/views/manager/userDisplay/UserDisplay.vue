<template>
  <lay-input v-model="data1" type="number" placeholder="输入id">
    <template #prefix>输入id</template>
  </lay-input>
  <lay-input v-model="data1" type="number" :max="300" :min="100"></lay-input>

  <lay-table :columns="columns"
             :data-source="dataSource"
             :page="page">

  </lay-table>
</template>

<script setup>
import {reactive} from "vue";
// import {userFindAll} from "./api.js";
// import {list} from "./api.js";

const columns = [
  {title: 'ID', key: 'id'},
  {title: '用户名', key: 'username'},
  {title: '邮箱', key: 'email'},
  {title: '手机号', key: 'phone'},
  {title: '创建时间', key: 'createTime'},
  {title: '更新时间', key: 'updateTime'},
]
const dataSource = reactive([])
// function reload(){
//   list().then(res =>{
//     dataSource.length = 0
//     dataSource.push(...res.data)
//   })
// }

const page = reactive({
  total: 8,
  limit: 8,
  current: 1,
  showRefresh: true,
  limits: [3,4,5,6,7,8]
})
</script>

<style scoped>

</style>