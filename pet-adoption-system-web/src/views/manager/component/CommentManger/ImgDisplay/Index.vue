<template>

  <div class="up">
    <lay-input :allow-clear="true" v-model="comment.username" placeholder="输入用户名">
      <template #prefix>用户名:</template>
    </lay-input>

    <lay-input :allow-clear="true" v-model="comment.content" placeholder="输入评论关键字">
      <template #prefix>评论:</template>
    </lay-input>


    <lay-button type="normal" @click="find">
      <lay-icon type="layui-icon-search" size="30" ></lay-icon></lay-button>
  </div>


  <div class="table">
    <lay-table :columns="columns"
               :data-source="data"
               :default-toolbar="true"
               size="1000px"
               even>
      <template #action="{row}">
        <lay-button type="danger" radius size="sm" style="width: 40px" @click="deleteCommentA(row)">
          <lay-icon type="layui-icon-delete" size="18"></lay-icon>
        </lay-button>
      </template>
    </lay-table>
  </div>

</template>

<script setup>
import {useRoute} from "vue-router";
import {reactive} from "vue";
import {findAll,deleteComment,findSome} from "./api.js";
import {layer} from "@layui/layui-vue";
import router from "../../../../../config/router.js";

const route = useRoute();

const data = reactive([])

const comment = reactive({
  username:"",
  content:"",
  issueId:route.query.id
})

function find(){
  findSome(comment).then(res=>{
    data.length = 0
    data.push(...res.data)
  })
}

function deleteCommentA(row){
  layer.confirm(`是否确认删除？`, {
    btn: [
      {
        text: "是",
        callback(id) {
          deleteComment(row.id)
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

function reload(){
  findAll(route.query.id).then(res=>{
    data.length = 0
    data.push(...res.data)
  })
}
reload()




const columns = reactive([

  {title: "用户名", key: "commentName", align: "center", width: "100px"},
  {title: "评论信息", key: "content", align: "center",width: "1000px"},
  {title: "创建时间", key: "createTime", align: "center", width: "100px"},
  {title: "操作", customSlot: "action", align: "center",width: "100px"}
])

</script>

<style scoped lang="scss">
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