<template>
  <div>
    <lay-row space="10">

      <lay-col>
        <lay-col md="6"><lay-input placeholder="请输入宠物id">
          <template #prepend>宠物id</template>
        </lay-input></lay-col>
        <lay-col md="6"><lay-input placeholder="请输入宠物名" v-model="petInfo.petName">
          <template #prepend>宠物名</template>
        </lay-input></lay-col>
        <lay-col md="6"><lay-input placeholder="请输入宠物品种" >
          <template #prepend>品种</template>
        </lay-input></lay-col>
      </lay-col >


    </lay-row>
    <lay-row space="10">
      <lay-col md="10"><lay-input placeholder="请输入性别">
        <template #prepend>性别</template>
      </lay-input></lay-col>
      <lay-col md="10"><lay-input placeholder="请输入领养状态" v-model="petInfo.petStatus">
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
        <lay-table :columns="columns" :data-source="data" :page="page" @change="change">

          <template #toolbar>
            <lay-tooltip content="新增宠物">
              <lay-button type="primary"  @click="addVisible=true">
                <lay-icon type="layui-icon-add-one"></lay-icon>
              </lay-button>

              <!--新增弹窗-->
              <lay-layer title="新增宠物" v-model="addVisible" :area="['450px','550px']">
                <lay-form :model="petAddInfo" class="register-form">
                  <lay-form-item label="宠物昵称:">
                    <lay-input placeholder="请输入宠物昵称" v-model="petAddInfo.petName"></lay-input>
                  </lay-form-item>
                  <lay-form-item label="宠物性别:">
                    <lay-select v-model="petAddInfo.petSex" placeholder="请选择">
                      <lay-select-option :value="'雌性'" label="雌性"></lay-select-option>
                      <lay-select-option :value="'雄性'" label="雄性"></lay-select-option>
                      <lay-select-option :value="'无'" label="无"></lay-select-option>
                    </lay-select>
                  </lay-form-item>
                  <lay-form-item label="宠物类型:">
                    <lay-input placeholder="请输入宠物类型" v-model="petAddInfo.variety" ></lay-input>
                  </lay-form-item>
                  <lay-form-item label="宠物图片:">
                    <input type="file" id="file" @change="handleFileChange">
                  </lay-form-item>
                  <img :src="imgbase64" style="width: 100px"/>
                  <lay-form-item label="宠物简介:">
                    <lay-textarea placeholder="请输入描述" v-model="petAddInfo.description">
                    </lay-textarea>
                  </lay-form-item>
                  <lay-form-item class="btn">
                    <lay-button class="btn1" @click="onAdd" >新增</lay-button>
                    <lay-button class="btn2" @click="addVisible=false" >取消</lay-button>
                  </lay-form-item>
                </lay-form>
              </lay-layer>
            </lay-tooltip>
          </template>

        </lay-table>
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
import {findSome, list,insert} from "./api.js";
import router from "../../../../config/router.js";

//窗口可视化参数
const addVisible = ref(false)
//新增的宠物信息
const petAddInfo = reactive({
  petName: "",
  petSex:"",
  petPicture:"",
  variety: "",
  description:"",
});

function onAdd(){
  petAddInfo.petPicture=abc
   insert(petAddInfo).then(res=>{
    layer.msg(res.msg)
     addVisible.value=false
  }).catch(err=>{
    layer.msg(err.msg)
  })
}

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

<style lang="scss">
.layui-col{
  font-size: 16px;//字体大小
  font-family: 微软雅黑;
  margin-right: 10px;
}
.register-form{
  margin-top: 10px;
  font-size: 16px;
  font-family: 微软雅黑;
}
.layui-input{
  width: 300px;
}
img{
  margin-left: 100px;
  margin-bottom: 10px;
}
.btn{
  width: 100%;
  border: #638863;
  color: white;
  .btn1{
    background: #638863;
  }
}
</style>

<!-- 处理图片-->
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