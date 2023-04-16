<template>
  <div class="table">
    <lay-table :columns="columns"
               :data-source="data"
               even>

    </lay-table>

  </div>
</template>

<script setup>
import {onMounted, reactive} from "vue";
import userLogin from "../../../../stores/LoginStore"
import {list} from "./api.js";
const data = reactive([])
const userInfo=userLogin().userInfo
const value=reactive({
  num:""
})
const columns = reactive([
  {title: "ID", key: "id", align: "center"},
  {title: "领养人", key: "username", align: "center"},
  {title: "宠物姓名", key: "petname", align: "center"},
  {title: "宠物性别", key: "petSex", align: "center"},
  {title: "描述", key: "describe", align: "center"},
  {title: "手机号", key: "phone", align: "center"},
  {title: "领养状态", key: "petStatus", align: "center"},
  {title: "创建时间", key: "createTime", align: "center"},
  {title: "更新时间", key: "updateTime", align: "center"},

])

function reload(){
  list(userLogin().userInfo).then(res=>{
    console.log(res);
    data.length=0
    data.push(...res.data)
  })
}

onMounted(reload)
</script>

<style scoped>

</style>