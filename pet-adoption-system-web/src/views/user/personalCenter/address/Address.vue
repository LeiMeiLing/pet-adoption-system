<template>
  <div class="address">
    <lay-icon type="layui-icon-close" ></lay-icon>
    <h1>收货地址</h1>
    <lay-form :model="display">
      <lay-form-item label="收货人："><lay-input v-model="userInfo.consignee"></lay-input></lay-form-item>
      <lay-form-item label="手机号码："><lay-input ></lay-input></lay-form-item>
      <lay-form-item label="详细地址："><lay-input v-model="userInfo.address"></lay-input></lay-form-item>
      <lay-button type="primary" fluid @click="onUpdate()">保存收货地址</lay-button>
    </lay-form>

  </div>
</template>

<script setup>
import userLogin from "../../../../stores/LoginStore"
import {updateUser} from "./api.js";
import {ref} from "vue";
import {layer} from "@layui/layui-vue";


const userInfo = userLogin().userInfo

const display = ref(false)


function onUpdate(){
  updateUser(userInfo)
  layer.msg("收货地址已保存",{time: 1500})
  display.value = false
}

</script>

<style scoped lang="scss">
.address{
  height: 600px;
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

.layui-form-item{
  border-bottom: 2px solid #426c42;
}
</style>