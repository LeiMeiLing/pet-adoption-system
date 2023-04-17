<template>
  <div class="Goods">
   <div class="up">

      <lay-input :allow-clear="true" v-model="goodsStore.goodsname" placeholder="输入商品名">
        <template #prefix>商品名:</template>
      </lay-input>

      <lay-input :allow-clear="true" v-model="goodsStore.goodsType" placeholder="输入类型">
        <template #prefix>商品类型:</template>
      </lay-input>
      <lay-button type="normal" @click="find">
        <lay-icon type="layui-icon-search" size="30" @click="findSome"></lay-icon></lay-button>
    </div>


    <div class="table">
      <lay-table :columns="columns"
                 :data-source="data"
                 :page="page"
                 @change="onPageChange"
                 even>


        <template #action="{row}">
          <lay-button type="danger" radius size="sm" style="width: 40px" @click="deleteGoods(row)">
            <lay-icon type="layui-icon-delete" size="18"></lay-icon>
          </lay-button>

          <lay-button type="normal" radius size="sm" style="width: 40px">
            <lay-icon type="layui-icon-edit" size="18" @click="showGoods(row)"></lay-icon>
          </lay-button>
        </template>




        <template #toolbar>
          <lay-tooltip content="添加商品">
            <lay-button type="primary" radius @click="addStoreVisibel=true">
              <lay-icon type="layui-icon-add-one"></lay-icon>
            </lay-button>
          </lay-tooltip>
        </template>
      </lay-table>


      <lay-layer v-model="addStoreVisibel" :area="['400px','450px']">
        <lay-form-item label="类型">
          <lay-input v-model="goodsStoreAdd.goodsType" placeholder="请输入类型"></lay-input>
        </lay-form-item>

        <lay-form-item label="名字">
          <lay-input v-model="goodsStoreAdd.goodsname" placeholder="请输入名字"></lay-input>
        </lay-form-item>

        <input type="file" id="file" @change="handleFileChange" >
        <img :src="imgbase64" style="width: 100px"/>

        <lay-form-item label="价格">
          <lay-input v-model="goodsStoreAdd.goodsPrice" placeholder="请输入价格"></lay-input>
        </lay-form-item>
        <lay-form-item label="描述">
          <lay-input v-model="goodsStoreAdd.goodsDesc" placeholder="请输入描述"></lay-input>
        </lay-form-item>
        <lay-form-item label="状态">
          <lay-input v-model="goodsStoreAdd.goodsStatus" placeholder="请输入状态"></lay-input>
        </lay-form-item>


        <lay-form-item>
          <lay-button type="primary" @click="onAdd" style="width: 80px">新增</lay-button>
        </lay-form-item>

      </lay-layer>

      <lay-layer v-model="updateGoodsDisplay" :area="['400px','450px']">
        <lay-form-item label="商品编号" >
          <lay-input v-model="goodsInfoUpdate.id" placeholder="请输入商品名" disabled="disabled"></lay-input>
        </lay-form-item>
        <lay-form-item label="商品名称">
          <lay-input v-model="goodsInfoUpdate.goodsname" placeholder="请输入商品名"></lay-input>
        </lay-form-item>

        <lay-form-item label="商品类型">
          <lay-input v-model="goodsInfoUpdate.goodsType" placeholder="请输入类型"></lay-input>
        </lay-form-item>

        <lay-form-item label="商品价格">
          <lay-input v-model="goodsInfoUpdate.goodsPrice" placeholder="请输入价格"></lay-input>
        </lay-form-item>

        <input type="file" id="file" @change="handleFileChange" >
        <img :src="imgbase64" style="width: 100px"/>

        <lay-form-item label="商品描述">
          <lay-input v-model="goodsInfoUpdate.goodsDesc" placeholder="请输入描述"></lay-input>
        </lay-form-item>

        <lay-form-item label="商品状态">
          <lay-input v-model="goodsInfoUpdate.goodsStatus" placeholder="请输入状态"></lay-input>
        </lay-form-item>

        <lay-form-item>
          <lay-button type="primary" @click="updateGoodsInfo" style="width: 80px">修改</lay-button>
        </lay-form-item>

      </lay-layer>
    </div>


  </div>


</template>

<script>
var _fileObj;
var abc;


export default {
  data(){
    return{
      imgbase64:""
    }
  },
  methods:{
    handleFileChange(e){
      let file = e.target.files[0];


      //缩略图
      let fr = new FileReader()
      fr.readAsDataURL(file)
      /*fr.readAsBinaryString(file)*/
      let that = this
      fr.onload = function () {
        that.imgbase64 = fr.result
        abc=that.imgbase64
      }
    }
  },
  submit() {
  }
}
</script>


<script setup>
import {onMounted, reactive, ref} from "vue";
import {deleteGoodsInfo, list, add, findSome,updateGoods} from "./api"
import {layer} from "@layui/layui-vue"
const columns = reactive([
  {title: "ID", key: "id", align: "center"},
  {title: "商品名称", key: "goodsname", align: "center"},
  {title: "商品类型", key: "goodsType", align: "center"},
  {title: "商品价格", key: "goodsPrice", align: "center"},
  /*{title: "商品图片", key:"goodsPicture",align: "center"},*/
  {title: "商品描述", key: "goodsDesc", align: "center"},
  {title: "商品状态", key: "goodsStatus", align: "center"},
  {title: "创建时间", key: "createTime", align: "center"},
  {title: "更新时间", key: "updateTime", align: "center"},
  {title: "操作", customSlot: "action", align: "center"}
])

const addStoreVisibel = ref(false)
const data = reactive([])
const updateGoodsDisplay = ref(false)
//查询
const goodsStore = reactive({
  goodsType: "",
  goodsname: "",

})

const goodsStoreAdd = reactive({
  goodsType: "",
  goodsname: "",
  goodsDesc: "",
  goodsStatus: "",
  goodsPicture:""
})

//修改用户
const goodsInfoUpdate = reactive({
  id:'',
  goodsType: "",
  goodsname: "",
  goodsPicture:'',
  goodsPrice: "",
  goodsDesc: "",
  goodsStatus: "",

})


const page = reactive({
  total: 10,
  limit: 8,
  current: 1,
  showRefresh: true,
  limits: [3, 4, 5, 6, 7, 8]
})


// 绑定更新界面，更新用户信息
function updateGoodsInfo() {
  updateGoods(goodsInfoUpdate)
  layer.msg("修改成功")
  reload()
  updateGoodsDisplay.value = false

}

function reload() {
  list().then(res => {
    data.length = 0
    data.push(...res.data.list)
    page.current = res.data.current
    page.total = res.data.total
  })
}

function showGoods(row) {
  updateGoodsDisplay.value = true
  goodsInfoUpdate.id=row.id
  goodsInfoUpdate.goodsname = row.goodsname
  goodsInfoUpdate.goodsType= row.goodsType
  goodsInfoUpdate.goodsDesc = row.goodsPrice
  goodsInfoUpdate.goodsStatus=row.goodsStatus
  goodsInfoUpdate.goodsPrice=row.goodsPrice
  goodsInfoUpdate.goodsPicture=row.goodsPicture

}


//新增用户
function onAdd() {
  goodsStoreAdd.goodsPicture = abc
  add(goodsStoreAdd)
  reload()
  layer.msg("新增成功")
  addStoreVisibel.value = false;
  reload()
}
//删除用户信息
function deleteGoods(row) {

  layer.confirm(`是否删除${row.goodsname}?`, {
    btn: [
      {
        text: "否",
        callback(id) {
          layer.close(id)
          find()
        }
      },

      {
        text: "是",
        callback(id) {
          deleteGoodsInfo(row)
          layer.msg("删除成功")
          layer.close(id)
          find()
        }
      }
    ]
  })
}



//查找用户信息
function find() {
  findSome(goodsStore).then(res => {
    console.log(res)
    data.length = 0
    data.push(...res.data)
  })
}

function onPageChange({current, limit}) {
  page.current = current
  page.limit = limit

}

onMounted(reload)
</script>

<style scoped lang="scss">
.Goods {
  width: 100%;
  height: 100%;

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

  .layui-space-align-center {
    margin-left: 20px;
    margin-top: 20px;
  }

  button {
    width: 8%;
    margin-left: 20px;
  }

  .table {
    margin-top: 20px;
  }

}
</style>