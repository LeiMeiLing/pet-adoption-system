<template>
  <!--转让宠物详细信息界面--->
  <lay-header>
    <h1>{{route.query.title}}</h1>
  </lay-header>
  <lay-body>
    <img :src="route.query.picture">
    <div>
      <lay-form class="transform-info">
        <lay-form-item label="发布人:">
        <div class="info">{{route.query.transferName}}</div>
        </lay-form-item>
        <lay-form-item label="宠物名:">
          <div class="info">{{route.query.petname}}</div>
        </lay-form-item>
        <lay-form-item label="性别:">
          <div class="info">{{route.query.petSex}}</div>
        </lay-form-item>
        <lay-form-item label="内容:">
          <div class="info">{{route.query.transferCause}}</div>
        </lay-form-item>
        <lay-form-item label="城市:">
          <div class="info">{{route.query.city}}</div>
        </lay-form-item>
        <lay-form-item label="发布时间:">
          <div class="info">{{route.query.createTime}}</div>
        </lay-form-item>
        <lay-form-item class="btn">
          <lay-space size="lg">
            <lay-button type="primary" @click="changeVisible">想要领养</lay-button>
            <lay-layer title="提交申请" v-model="visible" :area="['500px','500px']">
              <!--领养申请信息界面--->
              <lay-form class="application-info">
                <lay-form-item label="标题:">
                  <lay-input v-model="applicationInfo.title"></lay-input>
                </lay-form-item>
                <lay-form-item label="发布人:">
                  <lay-input v-model="applicationInfo.transferName"></lay-input>
                </lay-form-item>
                <lay-form-item label="申请人:">
                  <lay-input v-model="applicationInfo.username"></lay-input>
                </lay-form-item>
                <lay-form-item label="宠物名:" >
                  <lay-input v-model="applicationInfo.petname"></lay-input>
                </lay-form-item>
                <lay-form-item label="申请说明:" prop="username">
                  <lay-input v-model="applicationInfo.transferCause"></lay-input>
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
import userLongin from "../../../../stores/LoginStore.js";
const route=useRoute()
const userInfo = userLongin().userInfo

const applicationInfo=reactive({
  title:"",
  petname:"",
  username:"",
  transferName:"",
  transferCause:"",
})

applicationInfo.title=route.query.title
applicationInfo.petname=route.query.petname
applicationInfo.username=userInfo.username
applicationInfo.transferName=route.query.transferName
const openSuccess = function() {
  visible.value=false
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
.layui-form-item{
  font-size: 20px;
  margin-top: 10px;
}
.info{
  padding-top: 5px;
  font-size: 20px;
}
.transfer-info{
  margin-top: 30px;
  height: 400px;
  width: 400px;

}

img{
  height: 400px;
  width: 500px;
  margin-top: 30px;
  margin-left: 200px;
  margin-right: 50px;
}
h1{
  margin-top: 50px;
  text-align: center;
  margin-bottom: 30px;
}

.layui-body{
  height: 600px;
  display: flex;
}
</style>