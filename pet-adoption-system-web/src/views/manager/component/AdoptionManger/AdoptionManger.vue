<template>

  <div class="up">
    <lay-row class="content">
      <lay-col md="10">
        <lay-input :allow-clear="true"  placeholder="输入用户名" v-model="search.userName">
          <template #prefix>用户名:</template>
        </lay-input>
      </lay-col>
        <lay-col md="4">

          <lay-tooltip >
            <lay-select v-model="search.Status"  placeholder="请选择" >
              <lay-select-option :value="1" label="审核通过" ></lay-select-option>
              <lay-select-option :value="2" label="审核不通过" ></lay-select-option>
              <lay-select-option :value="3" label="待审核" ></lay-select-option>
            </lay-select>
          </lay-tooltip>
        </lay-col>


      <lay-button type="normal" @click="find">
        <lay-icon type="layui-icon-search" size="30" ></lay-icon>
      </lay-button>
    </lay-row>

  </div>


  <div class="table">
    <lay-table :columns="columns"
               :data-source="data"
               :default-toolbar="true"
               even>
        <template #action="{row}">
          <lay-tooltip content="审核管理">
            <lay-select v-model="value.num" placeholder="请选择">
              <lay-select-option :value=3  label="默认" @click="updateStatus(row)"></lay-select-option>
              <lay-select-option :value=1  label="同意" @click="updateStatus(row)"></lay-select-option>
              <lay-select-option :value=2  label="不同意" @click="updateStatus(row)"></lay-select-option>
            </lay-select>
          </lay-tooltip>
        </template>

    </lay-table>

</div>
</template>

<script setup>
import {onMounted, reactive, ref} from "vue";
import {findSome, list, update} from "./api.js";
import {layer} from "@layui/layui-vue";
const data = reactive([])
const search=reactive({
  userName:'',
  Status:''
})


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
  {title: "领养管理", customSlot: "action", align: "center"}
])

function reload(){
  list().then(res=>{
    data.length=0
    data.push(...res.data)
  })
}

function find(){
  switch (search.Status){
    case 1:
      search.Status="审核通过"
      break;
    case 2:
      search.Status="审核不通过"
          break;
    default:
      search.Status="待审核"
      break;
  }
  findSome(search).then(res=>{
    data.length=0
    data.push(...res.data)
  })


}
function updateStatus(row){
    if(value.num==1){
      const status="审核通过"
      const describe="恭喜您，申请成功，快开启你的宠物之旅吧"
      update(row,status,describe).then(res=>{
        layer.msg("修改成功")
        value.num="3"
        reload()
      })
    }else if(value.num==2){
      const petStatus="审核不通过"
      const describe="您的领养资质不合格"
      update(row,petStatus,describe).then(res=>{
        layer.msg("修改成功")
        value.num='3'
        reload()
      })
    }


}
onMounted(reload)
</script>

<style scoped>
.content{
  display: flex;

}
</style>