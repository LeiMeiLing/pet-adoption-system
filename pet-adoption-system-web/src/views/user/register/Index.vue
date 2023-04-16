<template>
<div class="register">
  <lay-form :model="userInfo" class="register-form">
    <h1 class="title">欢迎使用宠物领养系统</h1>
    <lay-form-item label="用户名" :label-position="labelPosition" prop="username">
      <lay-input placeholder="请输入用户名" v-model="userInfo.username"></lay-input>
    </lay-form-item>
    <lay-form-item label="密码" :label-position="labelPosition" prop="password">
      <lay-input placeholder="请输入密码" v-model="userInfo.password" type="password"></lay-input>
    </lay-form-item>
    <lay-form-item label="确认密码" :label-position="labelPosition" prop="checkPassword">
      <lay-input placeholder="请再次输入密码" v-model="userInfo.checkPassword" type="password"></lay-input>
    </lay-form-item>
    <lay-form-item label="邮箱" :label-position="labelPosition" prop="email">
      <lay-input placeholder="请输入邮箱" v-model="userInfo.email" type="email"></lay-input>
    </lay-form-item>
    <lay-form-item label="电话" :label-position="labelPosition" prop="phone">
      <lay-input placeholder="请输入电话" v-model="userInfo.phone"></lay-input>
    </lay-form-item>
    <lay-form-item class="btn">
      <lay-button class="btn1" @click="onRegister" >注册</lay-button>
      <lay-button class="btn2" @click="login" >登录</lay-button>
    </lay-form-item>
  </lay-form>
</div>
</template>

<script setup>
import { ref} from "vue";
import {register} from "./api.js";
import router from "../../../config/router.js";
import {layer} from "@layui/layui-vue";

const userInfo = ref({
  username: "",
  password:"",
  checkPassword:"",
  email: "",
  phone: "",
});

const regEmail = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
const regPhone = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
const labelPosition = ref('right');

function onRegister(){
  if ( userInfo.value.password!== userInfo.value.checkPassword){
    layer.confirm('两次输入的密码不一致哦，请重新输入')
  }else if (!regEmail.test(userInfo.value.email)) {
    layer.confirm('邮箱格式不正确，请检查')
  }  else if (!regPhone.test(userInfo.value.phone)){
    layer.confirm('电话格式不正确，请检查')
  }else{
    register(userInfo.value).then(res=>{
      layer.msg(res.msg)
      router.push('/login')
    }).catch(err=>{
      layer.msg(err.msg)
    })
  }
}
function login(){
   router.push('/login')
}
</script>

<style scoped lang="scss">
.register{
  width: 100%;
  height: 100%;
  background-image: url("../../../../public/注册2.png");
  background-size: cover;
  display: flex;
  justify-content: center;
  align-items: center;
  .register-form{
    width: 450px;
    height: 400px;
    border: #ccc solid 1px;
    border-radius: 10px;

    background-color: rgba(255,255,255,0.5,);
    backdrop-filter: blur(10px);
    .title{
      text-align: center;
      margin-bottom: 30px;
    }
    .layui-input{
      width: 300px;
    }
    .layui-btn{
      margin-left: 35px;
    }
    .btn{
      width: 100%;
      border: #638863;
      color: white;
      .btn1{
        background: #638863;
      }
    }
  }
}

</style>