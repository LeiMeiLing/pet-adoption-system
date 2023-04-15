<template>
    <div class="information">
      <lay-form v-model="visible">
        <lay-avatar :src="src" radius ></lay-avatar>
        <lay-form-item label="年龄：" ><lay-input ></lay-input ></lay-form-item>
        <lay-form-item label="性别："><lay-input></lay-input></lay-form-item>
        <lay-form-item label="手机号："><lay-input v-model="updateUserInfo.phone"></lay-input></lay-form-item>
        <lay-form-item label="邮箱地址："><lay-input v-model="updateUserInfo.email"></lay-input></lay-form-item>
        <lay-form-item label="个性签名："></lay-form-item>
        <lay-textarea placeholder="请输入描述"  ></lay-textarea>
        <br>
        <lay-button type="primary" fluid @click="onUpdate()">保存个人信息</lay-button>
      </lay-form>
    </div>

</template>

<script setup>
import {reactive, ref} from 'vue'
import {useRouter} from "vue-router";
import userLogin from "../../../../stores/LoginStore"
import {updateUser} from "./api.js";
import {layer} from "@layui/layui-vue";

const userInfo = userLogin().userInfo
const updateUserInfo=reactive({
  ...userInfo
})



const visible = ref(true)
const src = "/public/宠物1.png";


function onUpdate(){
  updateUser(updateUserInfo)
  layer.confirm(`保存成功!`)
  visible.value = false
}
</script>

<style scoped lang="scss">
.information{
  font-size: 18px;
  height: 620px;
  width: 400px;
  color: #ffffff;
  line-height: 38px;
  background-color: #638863;
  border:3px solid #3a5e3a;
  border-radius: 5px;
}
.layui-icon{
  float: right;
}
h1{
  width: 100%;
  display: flex;
  justify-content: center;
  border-bottom: 5px solid #3a5e3a;
}
.layui-avatar{
  height: 100px;
  width: 100px;
  left: 35%;
  margin-bottom: 5px;
}
.layui-form-item{
  border-bottom: 2px solid #426c42;
}
.layui-input{
  margin-bottom: 5px;
}
.layui-textarea{
  background-color: #638863;
}

</style>