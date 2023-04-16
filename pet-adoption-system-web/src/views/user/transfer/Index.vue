<template>
  <!--宠物转让界面-->
  <lay-layout>
    <lay-body>
      <div class="transfer">
        <lay-panel shadow="hover" v-for="item in dataSource">
          <div class="content">
            <img :src="item.picture">
            <div class="content-right">
              <h1 @click="onInfo(item)">{{item.title}}</h1>
              <div class="content-right-data">
                {{item.transferCause}}
              </div>
              <lay-breadcrumb>
                <lay-breadcrumb-item >{{item.createTime}}</lay-breadcrumb-item>
                <lay-breadcrumb-item >{{item.transferName}}</lay-breadcrumb-item>
                <lay-breadcrumb-item >{{item.city}}</lay-breadcrumb-item>
              </lay-breadcrumb>
            </div>
          </div>
        </lay-panel>
      </div>
    </lay-body>
    <lay-side>
      <lay-button @click="visible=true" class="transfer-application">
        <lay-icon type="layui-icon-release"></lay-icon>发布转让</lay-button>

      <lay-layer title="转让信息" :move="false" v-model="visible" :area="['500px','750px']">
        <lay-form class="transfer-info">
          <lay-form-item label="用户名:">
            <lay-input placeholder="请输入用户名" v-model="transferInfo.transferName"></lay-input>
          </lay-form-item>
          <lay-form-item label="转让标题:">
            <lay-input placeholder="请输入标题" v-model="transferInfo.title"></lay-input>
          </lay-form-item>
          <lay-form-item label="宠物昵称:">
            <lay-input placeholder="请输入宠物昵称" v-model="transferInfo.petname"></lay-input>
          </lay-form-item>
          <lay-form-item label="宠物性别:">
            <lay-select v-model="transferInfo.petSex" placeholder="请选择">
              <lay-select-option :value="'雌性'" label="雌性"></lay-select-option>
              <lay-select-option :value="'雄性'" label="雄性"></lay-select-option>
              <lay-select-option :value="'无'" label="无"></lay-select-option>
            </lay-select>
          </lay-form-item>
          <lay-form-item label="宠物品种:">
            <lay-input placeholder="请输入宠物昵称" v-model="transferInfo.variety"></lay-input>
          </lay-form-item>
          <lay-form-item label="宠物图片:" class="img-input">
            <input type="file" id="file" @change="handleFileChange">
            <img :src="imgbase64" style="width: 150px;height: 100px;margin-right: 20px;margin-top: 10px"/>
          </lay-form-item>
          <lay-form-item label="所在城市:">
            <lay-input placeholder="请输入所在城市" v-model="transferInfo.city"></lay-input>
          </lay-form-item>
          <lay-form-item label="情况说明:">
            <lay-textarea placeholder="请输入描述" v-model="transferInfo.transferCause">
            </lay-textarea>
          </lay-form-item>
          <lay-form-item >
            <lay-space size="lg">
              <lay-button class="btn" type="primary" @click="onCommit">发布</lay-button>
              <lay-button class="btn" type="primary" @click="visible=false">取消</lay-button>
            </lay-space>
          </lay-form-item>
        </lay-form>
      </lay-layer>

      <div class="side">
        <lay-quote>宠物资讯</lay-quote>
      </div>
    </lay-side>
  </lay-layout>

</template>

<script setup>
import {onMounted, reactive, ref} from "vue";
import router from "../../../config/router.js";
import userLongin from "../../../stores/LoginStore"
import {insert, select} from "./api.js";
import {layer} from "@layui/layui-vue";

const userInfo = userLongin().userInfo
const time=ref('2023年4月12日')

const title=ref('新西兰的宠物牛奶（牛奶先生零乳糖令人耳目一新）')
const data=ref('新西兰一直给人的印象就是广阔的草原，以及它孕育出的优质奶源，' +
    '尤其在人类婴幼儿奶粉方面尤其突出，新西兰的奶源可以说是奶粉竞争力以及品质的体现。' +
    '不仅仅是对人产业，对于宠物营养品行业来说，新西兰奶源也颇受高端宠物奶粉品牌青睐。')
const visible=ref(false)
function onInfo(item){
  router.push({path:'/detailed',query:item})
}


const transferInfo=reactive({
  title:"",
  transferName:"",
  petname:"",
  transferCause:"",
  picture:"",
  variety:"",
  petSex:"",
  city:"",
})
transferInfo.transferName=userInfo.username
function onCommit(){
  transferInfo.picture = abc
  insert(transferInfo).then(res=>{
    layer.msg(res.msg)
    visible.value=false
  }).catch(err=>{
    layer.msg(err.msg)
  })
}

let dataSource = reactive([])
select().then(res=>{
  dataSource.length=0
  dataSource.push(...res.data);
})
onMounted(select)

</script>

<style scoped lang="scss">
.transfer-application{
  margin-top: 20px;
  margin-left: 20px;
  background-color: #638863;
  color: white;
  font-size: 18px;
  height: 80px;
  width: 150px;
}
.transfer{
  height: 150px;
  width: 1000px;
  margin-top: 20px;
  margin-left: 10px;
  display: inline;
}
.side{
  margin-left: 20px;
}
.content-right-data{
 font-size: 18px;
  cursor: pointer;
  margin-bottom: 10px;
}
.transfer-info{
  margin-top: 10px;
  margin-left: 60px;
  .layui-input{
    width: 220px;
  }
  .btn{
      background-color: #638863;
      border: #638863 solid 1px;
  }
}
.content{
  height: 200px;
  width:1000px ;
  display: flex;
  align-items: center;
}
.content-right{
  margin-left: 10px;
}

h1{
  border: white;
  width:700px;
 margin-bottom: 20px;
  font-size: 20px;
  cursor: pointer;
}
.layui-panel{
  display: flex;
  align-items: center;
}
.side{
  margin-top: 20px;
}

img{
  height: 150px;
  width: 230px;
  border-radius: 5px;
  transition: all 0.3s;/* 上浮这个过程需要的时间 */
}
img:hover{
  box-shadow: 0 16px 32px 0 rgba(48, 55, 66, 0.15);/* 鼠标悬浮时盒子出现的阴影 */
  transform: translate(0, -2px);/* 鼠标悬浮时盒子上移10px */
  cursor: pointer;
}
</style>


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

