<template>
    <lay-panel class="information">
      <lay-form v-model="visible">
        <h1>个人信息</h1>
        <lay-avatar :src="src" radius></lay-avatar>
        <lay-form-item label="年龄：">
          <lay-input v-model="userInfo.age"></lay-input>
        </lay-form-item>
        <lay-form-item label="性别：">
          <lay-select v-model="value" placeholder="请选择" @change="change1">
            <lay-select-option :value="1" label="男"></lay-select-option>
            <lay-select-option :value="2" label="女"></lay-select-option>
          </lay-select>
        </lay-form-item>
        <lay-form-item label="手机号：">
          <lay-input v-model="userInfo.phone"></lay-input>
        </lay-form-item>
        <lay-form-item label="邮箱地址：">
          <lay-input v-model="userInfo.email"></lay-input>
        </lay-form-item>
        <lay-form-item label="个性签名："></lay-form-item>
        <lay-textarea placeholder="请输入个性签名" v-model="userInfo.sign"></lay-textarea>
        <br>
        <lay-button type="primary" fluid @click="onUpdate()">保存个人信息</lay-button>
      </lay-form>
    </lay-panel>

</template>

<script setup>
import {reactive, ref} from 'vue'
import {useRouter} from "vue-router";
import userLogin from "../../../../stores/LoginStore"
import {updateUser} from "./api.js";
import {layer} from "@layui/layui-vue";

const userInfo = userLogin().userInfo;

const value = ref(null);

const change1 = ( value )=>{
  if (value === 1){
    userInfo.sex="男";
  }else if(value === 2){
    userInfo.sex="女";
  }
}


const visible = ref(false)
const src = "/public/宠物1.png";


function onUpdate() {
  updateUser(userInfo)
  layer.msg(`保存成功!`, {time: 1500})
  visible.value = false
}
</script>

<style scoped lang="scss">
.information {
  font-size: 18px;
  height: 100%;
  width: 100%;
  color: #ffffff;
  line-height: 38px;
  background-color: #87b087;
  border: 3px solid #3a5e3a;
  border-radius: 5px;
  h1{
    display: flex;
    justify-content: center;
  }
}
.layui-form{
  margin-left: 100px;
  margin-right: 100px;
}
.layui-icon {
  float: right;
}


.layui-avatar {
  height: 150px;
  width: 150px;
  margin-bottom: 5px;
}

.layui-form-item {
  border-bottom: 2px solid #426c42;
}

.layui-input {
  margin-bottom: 5px;
}

.layui-textarea {
  background-color: #638863;
}
.layui-select{

}
</style>