<template>
<!--宠物详细信息界面--->
  <lay-header>
    <h1>待领养宠物信息</h1>
  </lay-header>
  <lay-body>

    <img :src="route.query.petPicture">
    <div>
      <lay-form class="pet-info">
        <lay-form-item label="宠物编号:" >
          <div class="content">{{ route.query.petId }}</div>
        </lay-form-item>
        <lay-form-item label="宠物昵称:">
          <div class="content">{{ route.query.petName}}</div>
        </lay-form-item>
        <lay-form-item label="性别:">
          <div class="content">{{ route.query.petSex}}</div>
        </lay-form-item>
        <lay-form-item label="宠物种类:">
          <div class="content">{{ route.query.variety}}</div>
        </lay-form-item>
        <lay-form-item label="发布时间:">
          <div class="content">{{ route.query.createTime}}</div>
        </lay-form-item>
        <lay-form-item label="宠物简介:">
          <div class="content">{{ route.query.description}}</div>
        </lay-form-item>
      <lay-form-item class="btn">
        <lay-space size="lg">
          <lay-button type="primary" @click="changeVisible">想要领养</lay-button>
          <lay-layer title="提交领养申请" v-model="visible" :area="['500px','500px']">
            <!--领养申请信息界面--->
            <lay-form class="pet-info">
              <lay-form-item label="姓名:" >
                <lay-input v-model="adoptionInfo.username"></lay-input>
              </lay-form-item>
              <lay-form-item label="宠物昵称:">
                <lay-input  v-model="adoptionInfo.petname"></lay-input>
              </lay-form-item>
              <lay-form-item label="宠物性别:">
                <lay-input v-model="adoptionInfo.petSex"></lay-input>
              </lay-form-item>
              <lay-form-item label="联系方式:">
                <lay-input v-model="adoptionInfo.phone"></lay-input>
              </lay-form-item>
              <lay-form-item label="宠物种类:">
                <lay-input v-model="adoptionInfo.variety"></lay-input>
              </lay-form-item>
              <lay-form-item label="申请说明:">
                <lay-input v-model="adoptionInfo.describe"></lay-input>
              </lay-form-item>
              <lay-form-item class="btn">
                <lay-space size="lg">
                  <lay-button type="primary" @click="openSuccess">提交申请</lay-button>
                  <lay-button type="primary" @click="cancel">取消申请</lay-button>
                </lay-space>
              </lay-form-item>
            </lay-form>
          </lay-layer>
          <lay-button type="primary" @click="back">再看看其他的宠物</lay-button>
        </lay-space>
      </lay-form-item>
      </lay-form>
    </div>
  </lay-body>
</template>

<script setup>
import {reactive, ref} from "vue";
import router from "../../../../config/router.js";
import {layer} from "@layui/layui-vue";
import {useRoute} from "vue-router";
import {insert} from "./api.js";
import userLongin from "../../../../stores/LoginStore"

const userInfo = userLongin().userInfo

const route=useRoute()


const adoptionInfo=reactive({
   username: "",
   variety: "",
   petname: "",
   petSex: "",
   describe: "",
   phone:""
})
adoptionInfo.username=userInfo.username
adoptionInfo.phone=userInfo.phone
adoptionInfo.variety=route.query.variety
adoptionInfo.petname=route.query.petName
adoptionInfo.petSex=route.query.petSex
const openSuccess = function() {
  insert(adoptionInfo).then(res=>{
    layer.msg(res.msg, { icon : 1, time: 1000})
    visible.value=false
  })
}
const visible = ref(false)
const changeVisible = function() {
  visible.value = !visible.value
}

function cancel(){
  visible.value = !visible.value
}
function back(){
  router.push('/userAdoption')
}

</script>

<style scoped lang="scss">
.pet-info{
  margin-top: 20px;
  height: 800px;
  width: 400px;
  overflow: hidden;
}

img{
  margin-left: 300px;
  margin-right: 50px;
  height: 450px;
  width: 440px;
}
h1{
  margin-top: 50px;
  text-align: center;
  margin-bottom: 30px;
}
.layui-body{
  height: 800px;
  display: flex;
  &::-webkit-scrollbar{
    display: none;
  }
}
.layui-form-item{
  font-size: 20px;
}
.content{
  padding:5px;
  font-size: 20px;
  color: #568356;
}
</style>