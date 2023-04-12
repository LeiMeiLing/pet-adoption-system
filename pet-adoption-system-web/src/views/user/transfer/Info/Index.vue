<template>
  <!--宠物详细信息界面--->
  <lay-header>
    <h1>待领养宠物信息</h1>
  </lay-header>
  <lay-body>
    <img src="public/宠物1.png">
    <div>
      <lay-form class="pet-info">
        <lay-form-item label="发布人:" prop="username">
          <lay-input placeholder=""></lay-input>
        </lay-form-item>
        <lay-form-item label="宠物名:" prop="username">
          <lay-input placeholder=""></lay-input>
        </lay-form-item>
        <lay-form-item label="性别:" prop="username">
          <lay-input placeholder=""></lay-input>
        </lay-form-item>
        <lay-form-item label="转让原因:" prop="username">
          <lay-input placeholder=""></lay-input>
        </lay-form-item>
        <lay-form-item label="转让领养要求:" prop="username">
          <lay-input placeholder=""></lay-input>
        </lay-form-item>
        <lay-form-item label="宠物性格特征:" prop="username">
          <lay-textarea placeholder="" v-model="data1">
          </lay-textarea>
        </lay-form-item>
        <lay-form-item label="发布时间:" prop="username">
          <lay-input placeholder=""></lay-input>
        </lay-form-item>
        <lay-form-item class="btn">
          <lay-space size="lg">
            <lay-button type="primary" @click="changeVisible">想要领养</lay-button>
            <lay-layer title="提交转让申请" v-model="visible" :area="['500px','500px']">
              <!--领养申请信息界面--->
              <lay-form class="pet-info">
                <lay-form-item label="姓名:" prop="username">
                  <lay-input placeholder="请输入用户名"></lay-input>
                </lay-form-item>
                <lay-form-item label="宠物名:" prop="username">
                  <lay-input placeholder=""></lay-input>
                </lay-form-item>
                <lay-form-item label="宠物性别:" prop="username">
                  <lay-input placeholder=""></lay-input>
                </lay-form-item>
                <lay-form-item label="联系方式:" prop="username">
                  <lay-input placeholder=""></lay-input>
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
import {ref} from "vue";
import router from "../../../../config/router.js";
import {layer} from "@layui/layui-vue";

const openSuccess = function() {
  layer.msg("提交成功", { icon : 1, time: 1000})
}
const visible = ref(false)
const changeVisible = function() {
  visible.value = !visible.value
}

function cancel(){
  visible.value = !visible.value
}
function back(){
  router.push('/transfer')
}
const petInfo=ref({

})
</script>

<style scoped lang="scss">
.pet-info{
  margin-top: 20px;
  height: 400px;
  width: 400px;
}

img{
  margin-left: 200px;
  margin-right: 50px;
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