<template>
  <div class="manager">
    <lay-form :model="managerInfo" class="manager-form">
      <h1 class="title">宠物领养管理系统</h1>
      <lay-form-item label="用户名" :label-position="labelPosition" prop="username">
        <lay-input placeholder="请输入用户名" v-model="managerInfo.username" @keyup.enter="onLogin"></lay-input>
      </lay-form-item>
      <lay-form-item label="密码" :label-position="labelPosition" prop="password">
        <lay-input placeholder="请输入密码" v-model="managerInfo.password" type="password" @keyup.enter="onLogin"></lay-input>
      </lay-form-item>
      <lay-form-item class="btn">
        <lay-button  @click="onLogin" >登录</lay-button>
      </lay-form-item>
    </lay-form>
  </div>
</template>

<script setup>
import { ref} from "vue";
import {login} from "./api.js";
import router from "../../../config/router.js";
import {layer} from "@layui/layui-vue";

const managerInfo = ref({
  username: "",
  password:"",
});

const labelPosition = ref('right');

function onLogin(){
  login(managerInfo.value).then(res=>{
    layer.msg('登录成功')
    router.push({name:'userDisplay'})
  }).catch(err=>{
    layer.msg('用户名或密码错误')
  })
}
</script>

<style scoped lang="scss">
.manager{
  width: 100%;
  height: 100%;
  background-image: url("../../../../public/注册1.png");
  background-size: cover;
  display: flex;
  justify-content: center;
  align-items: center;
.manager-form{
  width: 450px;
  height: 300px;
  border: #ccc solid 1px;
  border-radius: 10px;
  background-color: rgba(255,255,255,0.5,);
  backdrop-filter: blur(5px);
.title{
  text-align: center;
  margin-bottom: 30px;
}
.layui-input{
  width: 300px;
}
  .btn{
    border: #638863;
  }
.layui-btn{
  width: 200px;
  color: #231b1b;
  margin-left: 30px;

}
}
}
</style>