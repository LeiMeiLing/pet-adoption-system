<template>

  <AdditionIcon size="150px" id="bbc" @click="go">
  </AdditionIcon>


  <div>


    <ul v-for="(item,index) in dataSource">
      <li class="item">
        <div class="img_box" @click="click(item)"><img v-bind:src="item.picture" alt=""></div>
        <div style="color: blue">宝贝名</div>   <div v-html="item.petName"></div>
        <lay-rate  :icons="['layui-icon-heart', 'layui-icon-heart-fill']" theme="#FE0000"></lay-rate>
        <div style="color: #e1251b">内容</div><span>{{item.content}}</span><br>
        <lay-button type="normal" radius @click="send">发表评论</lay-button>
      </li>
    </ul>



  <lay-layer v-model="addStoreVisibel" :area="['400px','400px']" title="发布">
    <lay-form-item label="宠物名">
      <lay-input v-model="petIssueAdd.petName" placeholder="请输入宠物名"></lay-input>
    </lay-form-item>
    <input type="file" id="file" @change="handleFileChange">
    <img :src="imgbase64" style="width: 100px"/>
    <lay-form-item label="想说的话">
      <div style="float: top">
      <lay-input v-model="petIssueAdd.content" placeholder="请留下您可爱宠物的描述" style="height: 200px"></lay-input>
      </div>
    </lay-form-item>
    <lay-form-item>
      <lay-button type="primary" @click="onAdd" style="width: 80px">发布</lay-button>
    </lay-form-item>
  </lay-layer>







  </div>
</template>

<script>
import {layer} from "@layui/layui-vue";

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
        if (fr.result.length > 64*1024){
          layer.msg("图片太大,请重新选择")
          that.imgbase64=""
        }else {
          that.imgbase64 = fr.result
          abc=that.imgbase64}
      }
    }
  },
  submit() {
  }
}
</script>

<script setup>
import {HeartIcon,AdditionIcon} from '@layui/icons-vue';
import router from "../../../config/router.js";
import {onMounted, onUpdated, reactive, ref} from "vue";
import {add, findAll} from "./api.js";
import {layer} from "@layui/layui-vue";
import useLogin from "../../../stores/LoginStore.js"

const loginInfo = useLogin();



const addStoreVisibel = ref(false)

const comment = ref(false)

function click(item){
  router.push({
    name:'commentA',
    query:{
      id:item.id
    }
  })
}

function send(){
  comment.value = true

}

function go(){
  petIssueAdd.petName=""
  petIssueAdd.content=""
  petIssueAdd.picture=""
  addStoreVisibel.value=true
}

function onAdd(){
  let a=0;
  petIssueAdd.picture = abc
    for(var key in petIssueAdd) {
      if (!petIssueAdd[key]) {
        layer.msg('有空值',100)
        return
      } else {
        a=1
      }
    }
if (a===1){
  add(petIssueAdd)
  layer.msg("发布成功" ,100)
  find()
  addStoreVisibel.value=false
}



}


const petIssueAdd = reactive({
  picture:"",
  content:"",
  commentId:loginInfo.userInfo.id,
  petName:""
})


const goodsInfo = reactive({
  id:0,
  goodsType:"",
  goodsName: "",
  goodsPicture:"",
  goodsPrice:"",
  goodsDesc:"",
  goodsStatus:""

});

function find(){
  findAll().then(res=>{
    dataSource.length=0
    dataSource.push(...res.data);
  })
}


let dataSource = reactive([]

)

onMounted(find)
/*onUpdated(find)*/


</script>

<style scoped lang="scss">
#bbc{
  float: right;
}
.div1{
  margin: 0 15%;
  display: flex;
  .div2{
    width: 30%;
    height: 250px;
    border: solid 5px #cccccc;
  }
}
.selectShopping{
  display: flex;
  margin: 10px auto;
  width: 50%;
  .layui-input{
    margin: 0 5px;
  }
}

* {
  margin: 0;
  padding: 0;
  list-style: none;
}

body {
  background: #e3e4e5;
  margin: auto;
}

#app {
  width: 1000px;
  margin-left: auto;
  margin-right: auto;
  overflow: hidden;
}


.item {
  float: left;
  width: 190px;
  height: 266px;
  margin: 0 5px 8px;
  text-align: center;
  background: #fff;
}

.item .img_box {
  width: 120px;
  height: 120px;
  margin: 30px auto;
}

.img_box img {
  width: 100%;
  height: 100%;
}

.item p {
  font-size: 12px;
  line-height: 20px;
  height: 40px;
  padding: 0 10px;
  /* white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis; */
  display: -webkit-box;
  overflow: hidden;
  text-overflow: ellipsis;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}

.more2_info_self {
  background-color: #e1251b;
  border-radius: 2px;
  color: #fff;
  padding: 0 5px;
  margin-right: 4px;
  line-height: 16px;
  height: 16px;
  font-size: 12px;
  font-style: normal;
}
</style>