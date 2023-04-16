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
          <lay-input v-model="route.query.petId"></lay-input>
        </lay-form-item>
        <lay-form-item label="宠物昵称:">
          <lay-input v-model="route.query.petName"></lay-input>
        </lay-form-item>
        <lay-form-item label="性别:">
          <lay-input v-model="route.query.petSex"></lay-input>
        </lay-form-item>
        <lay-form-item label="宠物种类:">
          <lay-input v-model="route.query.variety"></lay-input>
        </lay-form-item>
        <lay-form-item label="发布时间:">
          <lay-input v-model="route.query.createTime"></lay-input>
        </lay-form-item>
        <lay-form-item label="宠物性格特征:">
          <lay-textarea v-model="route.query.description">
          </lay-textarea>
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
                <lay-input v-model="adoptionInfo.petname"></lay-input>
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
import userLogin from "../../../../stores/LoginStore"
const route=useRoute()

const userInfo=userLogin().userInfo

const adoptionInfo=reactive({
   username: "",
   variety: "",
   petname: "",
   petSex: "",
   describe: "",
   phone:""
})
adoptionInfo.variety=route.query.variety
adoptionInfo.petname=route.query.petName
adoptionInfo.petSex=route.query.petSex

const openSuccess = function() {
  insert(adoptionInfo,userInfo).then(res=>{
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
  height: 400px;
  width: 400px;
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
.layui-textarea{
  width: 200px;
}
.layui-body{
  height: 500px;
  display: flex;
}
</style>