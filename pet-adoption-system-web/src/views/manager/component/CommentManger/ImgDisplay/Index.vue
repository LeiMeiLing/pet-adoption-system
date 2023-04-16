<template>
  <div class="out">
    <img class="img" v-bind:src="route.query.picture" alt="" >
    <div class="query">
      {{ route.query }}
    </div>
  </div>


  <div class="table">
    <lay-table :columns="columns"
               :data-source="data"
               :default-toolbar="true"
               size="1000px"
               even>
      <template #action="{row}">

        <lay-button type="danger" radius size="sm" style="width: 40px" @click="deleteUser(row)">
          <lay-icon type="layui-icon-delete" size="18"></lay-icon>
        </lay-button>

        <lay-button type="normal" radius size="sm" style="width: 40px">
          <lay-icon type="layui-icon-edit" size="18" @click="showUser(row)"></lay-icon>
        </lay-button>
      </template>
    </lay-table>
  </div>

</template>

<script setup>
import {useRoute} from "vue-router";
import router from "../../../../../config/router.js";
import {reactive} from "vue";

const route = useRoute();

const columns = reactive([

  {title: "用户名", key: "username", align: "center", width: "100px"},
  {title: "评论信息", key: "email", align: "center",width: "1000px"},
  {title: "创建时间", key: "createTime", align: "center", width: "100px"},
  {title: "操作", customSlot: "action", align: "center"}
])

</script>

<style scoped>
.out{
  display: flex;
  flex-direction: column;
}

.out .img{
  position: absolute;
  weight: 150px;
  height: 150px;
  top: 20%;
  left: 45%;
  transform:translate(-50%,-12%);
  /*transform: scale(1);*/
  max-width: 700px;
}

</style>