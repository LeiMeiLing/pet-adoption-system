<template>
  <div class="table">
    <lay-table :columns="columns"
               :data-source="data"
               :default-toolbar="true"
               even>
      <template #action="{row}">
        <lay-button type="danger"  size="sm"  @click="updateStatus(row)">
         同意
        </lay-button>
        <lay-button type="normal" size="sm" @click="updateS(row)">
         不同意
        </lay-button>
      </template>

    </lay-table>

  </div>
</template>

<script setup>
import {onMounted, reactive} from "vue";
import userLogin from "../../../../stores/LoginStore"
import {list, update} from "./api.js";
const data = reactive([])
const userInfo=userLogin().userInfo
const columns = reactive([
  {title: "ID", key: "id", align: "center"},
  {title: "转让标题", key: "title", align: "center"},
  {title: "宠物姓名", key: "petName", align: "center"},
  {title: "转让人", key: "transferName", align: "center"},
  {title: "转让说明", key: "transferName", align: "center"},
  {title: "宠物图片", key: "picture", align: "center"},
  {title: "宠物品种", key: "variety", align: "center"},
  {title: "宠物性别", key: "petSex", align: "center"},
  {title: "城市", key: "city", align: "center"},
  {title: "领养状态", key: "petStatus", align: "center"},
  {title: "创建时间", key: "createTime", align: "center"},
  {title: "更新时间", key: "updateTime", align: "center"},
  {title: "操作",  customSlot: "action", align: "center"},
])
function updateS(row){
  const b="审核不通过"

  row.petStatus=b
  update(row)
}

function updateStatus(row){
  const a="审核通过"
  row.petStatus=a
    update(row)
}
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