<template>
  <div>
    <lay-row>

      <lay-col>
        <lay-col md="8"><lay-input placeholder="请输入宠物id">
          <template #prepend>宠物id</template>
        </lay-input></lay-col>
        <lay-col md="8"><lay-input placeholder="请输入宠物名" v-model="petInfo.petName">
          <template #prepend>宠物名</template>
        </lay-input></lay-col>
        <lay-col md="8"><lay-input placeholder="请输入宠物品种" >
          <template #prepend>品种</template>
        </lay-input></lay-col>
      </lay-col >


    </lay-row>
    <lay-row>
      <lay-col md="11"><lay-input placeholder="请输入性别">
        <template #prepend>性别</template>
      </lay-input></lay-col>
      <lay-col md="11"><lay-input placeholder="请输入领养状态" v-model="petInfo.petStatus">
        <template #prepend>领养状态</template>
      </lay-input></lay-col>
      <lay-col md="2">
        <lay-button type="normal" @click="find">
          <lay-icon type="layui-icon-search">
          </lay-icon>
        </lay-button>
      </lay-col>
    </lay-row>
    <lay-row space="10">
      <lay-col>
        <lay-table :columns="columns" :data-source="data" :page="page" @change="change"></lay-table>
      <template #action>
        <lay-button type="danger" size="xs">
          <lay-icon type="layui-icon-delete" size="18px">
          </lay-icon>
        </lay-button>
        <lay-button type="normal" size="xs">
          <lay-icon type="layui-icon-edit" size="18px">
          </lay-icon>
        </lay-button>
      </template>

      </lay-col>
    </lay-row>
  </div>

</template>

<script setup>
import {onMounted, onUpdated, reactive, ref} from 'vue';
import { layer } from "@layui/layer-vue";
import {findSome, list} from "./api.js";

var list1 = list();
console.log(list1)
const page = ref({
      total: 100,
      limit: 10,
      current: 2,
      showRefresh: true,
    })

    const change = ({ current, limit }) => {
      layer.msg ("current:" + current + " limit:" + limit);
    }
    const columns =reactive( [
      {
        title: "宠物ID",
        key:"petId",
        align:'center'
      },{
        title: "宠物名",
        key:"petName",
        align:'center'
      },{
        title: "品种",
        key:"variety",
        align:'center'
      },{
        title: "性别",
        key:"petSex",
        align:'center'
      },{
        title: "图片",
        key:"petPicture",
        align:'center'
      },{
        title: "领养状态",
        key:"petStatus",
        align:'center'
      },{
        title: "创建时间",
        key:"creatTime",
        align:'center'
      },{
        title: "修改时间",
        key:"updateTime",
        align:'center'
      },
      {
        title: "操作",
        customSlot:'action',
        align: "center"
      },
    ])
    const data=reactive([])
    function reload(){
    list().then(res=>{
      data.length=0
      data.push(...res.data.list)
    })
    }


    const petInfo=reactive({
      petName:'',
      petStatus:''

    })
    function find(){
  findSome(petInfo.petName,petInfo.petStatus).then(res=>{
    data.length=0;
    data.push(...res.data.list)
  })
    }
    onMounted(reload)
    onUpdated(reload)
</script>
<style>

</style>