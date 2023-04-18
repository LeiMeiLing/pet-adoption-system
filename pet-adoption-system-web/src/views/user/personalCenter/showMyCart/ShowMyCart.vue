<template>
  <div class="main">
    <lay-table style="margin: 15px" :columns="columns" :data-source="dataSource" :page="page">
      <template v-slot:operator="{ row }">
        <lay-button size="sm" class="btn">
          <lay-icon type="layui-icon-unlink"></lay-icon></lay-button>
      </template>


      <template v-slot:active="{ row }" v-for="data in dataSource">
        <div class="goodsInfo">
          <img :src="data.goodsPicture" style="height: 50px;width:100px;margin-right: 20px">
          <div>{{data.goodsName}}</div>
        </div>
      </template>
      <template v-slot:number="{ row }" v-for="data in dataSource">
        <div><lay-input-number v-model="data.quantity" :step="1"></lay-input-number></div>
      </template>
    </lay-table>



    
<!--      <div v-for="data in dataSource">
          {{data}}
          <img v-bind:src="data.goodsPicture" alt="">
      </div>-->
  </div>
</template>

<script setup>
import {showMyCart} from "./api.js";
import useLogin from "../../../../stores/LoginStore.js"
import {reactive, ref} from "vue";
const userInfo = useLogin().userInfo;
let dataSource = reactive([])

showMyCart(userInfo.id).then(res=>{
    dataSource.push(...res.data.list);
    console.log(dataSource)

})
const data = ref(0);

const page = ref({
  total: 100,
  limit: 10,
  current: 2,
  showRefresh: true,
})

/*const change = ({ current, limit }) => {
  layer.msg("current:" + current + " limit:" + limit);
}*/

const columns = [
  {
    fixed: "left",
    type: "checkbox",
  },{
    title:"商品信息",
    width:"400px",
    customSlot:"active",
    key:"goods",
    align:"center"
  },{
    title:"单价",
    width: "100px",
    key:"price",
    align:"center"
  },{
    title:"数量",
    width: "250px",
    customSlot:"number",
    key:"quantity",
    align:"center"
  },{
    title:"金额",
    width: "100px",
    key:"price",
    align:"center"
  },{
    title:"操作",
    width: "80px",
    key:"operator",
    customSlot:"operator",
    align:"center"
  }
]

</script>

<style scoped lang="scss">
.main{
  height: 400px;
  margin: 10px;
  border: #638863 solid 1px;
  border-radius: 15px;
}
.btn{
  color: white;
  font-size: 10px;
  background-color: #638863;
  border-radius: 5px;
}
.goodsInfo{
  display: flex;
}

</style>