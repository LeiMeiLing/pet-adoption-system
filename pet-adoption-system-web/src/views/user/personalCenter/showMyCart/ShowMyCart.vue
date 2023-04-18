<template>
  <div class="main">
    <lay-table style="margin: 15px"
               :columns="columns"
               :data-source="dataSource"
               :page="page"
               v-model:selectedKeys="selectedKeys"
               >
      <template #toolbar>
        <lay-tooltip content="下单">
          <div class="parent">
            <div class="content">
              下单请点击这里￥&ensp;&ensp;&ensp;&ensp;
            </div>
            <div class="order">
              <lay-button type="primary" @click="order">
                <lay-icon type="layui-icon-rmb"></lay-icon>
              </lay-button>

            </div>
            <div class="title">
              &ensp;&ensp;保存点右边的√选项
              &ensp;&ensp;&ensp;&ensp;
            </div>
            <div class="keep">
              <lay-button type="primary" @click="keepGoods">
                <lay-icon type="layui-icon-ok"></lay-icon>
              </lay-button>
            </div>
          </div>

        </lay-tooltip>
      </template>


      <template v-slot:operator="{ row }">
        <lay-button size="sm" class="btn" @click="deleteGoodsInfo(row)">
          <lay-icon type="layui-icon-unlink"></lay-icon></lay-button>
      </template>
      <template v-slot:active="{ row }" >
        <div class="goodsInfo">
          <img :src="row.goodsPicture" style="height: 50px;width:100px;margin-right: 20px">
          <div>{{row.goodsName}}</div>
        </div>
      </template>
      <template v-slot:number="{ row  }" >



<!--        <div><lay-input-number v-model="row.quantity" :step="1"></lay-input-number></div>-->

        <lay-button type="default" @click="reduce(row)">-</lay-button>
<!--                            数量：{{ goodsInfo.goodsPrice }}-->
        <lay-input v-model="row.quantity" type="number" :max="99" :min="1" class="input1"></lay-input>
        <lay-button type="default" @click="increase(row)">+</lay-button>

      </template>
      <template v-slot:totalMoney="{row}">
        <div class="total" >
          <div>
            {{row.price*row.quantity}}
          </div>
        </div>
      </template>
    </lay-table>
  </div>
</template>

<script setup>
import {dele, deleRow, findSome, showMyCart, update} from "./api.js";
import useLogin from "../../../../stores/LoginStore.js"
import {onMounted, reactive, ref} from "vue";
import {layer} from "@layui/layer-vue";
const userInfo = useLogin().userInfo;
let dataSource = reactive([])
const selectedKeys = ref([])
const count = ref(1)

function reduce(row){
    if (row.quantity>1){
      row.quantity--
  }
}
function increase(row){
  if (row.quantity<99){
    row.quantity++
  }
}
function reload(){

  showMyCart(userInfo.id).then(res=>{
    dataSource.push(...res.data.list);

  })
}
function keepGoods(){
  let updateParm=''
  for (let i = 0; i <dataSource.length ; i++) {
   updateParm += dataSource[i].id+','+dataSource[i].quantity+','

  }
  update(updateParm).then(res=>{
    layer.msg('保存成功')

  })

}
function order() {
  var length = selectedKeys.value.length;

  var findParameter=''
  for (let i = 0; i <length ; i++) {
    findParameter+='id='+selectedKeys.value[i]+'&'
  }
  var s = findParameter.substring(0,findParameter.length-1)

    findSome(s).then(res=>{
      layer.msg("您总共花了"+res.data.totalPrice)
      dele(selectedKeys.value).then(res=>{
      })
  })
}

function deleteGoodsInfo(row){
  layer.confirm(`是否删除${row.goodsname}?`, {
    btn: [
      {
        text: "否",
        callback(rowId) {
          layer.close(rowId)
        }
      },
      {
        text: "是",
        callback(rowId) {
          deleRow(row)
          layer.msg("删除成功")
          layer.close(rowId)
        }
      }
    ]
  })
}

const data = ref(0);
const page = ref({
  total: 100,
  limit: 10,
  current: 2,
  showRefresh: true,
})


const columns = [
  {
    fixed: "left",
    type: "checkbox",
  },

  {
    title:"商品信息",
    width:"400px",
    customSlot:"active",
    key:"goods",
    align:"center"
  },{
    title:"单价",
    width: "100px",
    key:"unitPrice",
    align:"center"
  },{
    title:"数量",
    width: "100px",
    customSlot:"number",
    key:"quantity",
    align:"center"
  },{
    title:"金额",
    width: "100px",
    customSlot:"totalMoney",
    key:"totalPrice",
    align:"center"
  },{
    title:"操作",
    width: "80px",
    key:"operator",
    customSlot:"operator",
    align:"center"
  }
]
onMounted(reload)

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
.parent{
  text-align :center ;
}
.content{
  display: inline-block;;
}
.order{
  display: inline-block;
  align-items: center;
  justify-content: center;
  align-items: center;
}
.keep{
  display: inline-block;
}
.title{
  display: inline-block;
}

</style>