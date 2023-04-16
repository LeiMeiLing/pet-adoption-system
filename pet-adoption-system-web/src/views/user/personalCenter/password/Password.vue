<template>
  <div class="password" >
    <lay-icon type="layui-icon-close" ></lay-icon>
    <h1>修改密码</h1>
    <lay-form >
      <lay-form-item label="原密码："><lay-input v-model="userInfo.oldPassword" ></lay-input></lay-form-item>
      <lay-form-item label="新密码："><lay-input v-model="userInfo.newPassword"></lay-input></lay-form-item>
      <lay-form-item label="确认密码："><lay-input v-model="userInfo.confirmPassword"></lay-input></lay-form-item>
      <lay-button type="primary" fluid="" @click="save">保存</lay-button>
    </lay-form>
    </div>
</template>

<script setup>
import {reactive, ref} from "vue";
import {confirmPassword, setNewPassword} from "./api.js";
import userLogin from "../../../../stores/LoginStore"
import {layer} from "@layui/layui-vue";
const userInfo=reactive({
  oldPassword:'',
  newPassword:'',
  confirmPassword:''
})


const visible=ref(true)
const userInfo1 = userLogin().userInfo;
function save(){
  console.log(userInfo1);
  confirmPassword(userInfo1,userInfo.oldPassword).then(res=>{
    if(res.code===200){
          if(userInfo.newPassword!=userInfo.confirmPassword){
            layer.msg("两次密码不一致")
          }else{
                  setNewPassword(userInfo.confirmPassword,userInfo1).then(res=>{
                    visible.value=false
                    layer.msg("修改成功")
                  })
          }
    }else{
      layer.msg("原密码错误")
    }
  })

}


</script>

<style scoped lang="scss">
.password{
  height: 500px;
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