<template>
  <div class="address">
    <lay-form :model="display">
      <lay-form-item label="收货人："><lay-input v-model="userInfo.consignee"></lay-input></lay-form-item>
      <lay-form-item label="手机号码："><lay-input v-model="userInfo.phone"></lay-input></lay-form-item>
      <lay-form-item label="所在地区：">
        <lay-cascader :options="options" decollator=" " :changeOnSelect="true" allow-clear v-model="userInfo.address"></lay-cascader>
      </lay-form-item>
      <lay-form-item label="详细地址："><lay-input v-model="userInfo.location"></lay-input></lay-form-item>
      <br>
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
const value=ref()
const options = [
  {
    value: "湖南省",
    label: "湖南省",
    children: [
      {
        value: "长沙市",
        label: "长沙市",
        children: [
          {
            value: "岳麓区",
            label: "岳麓区",
          },
          {
            value: "雨花区",
            label: "雨花区",
          },
          {
            value: "天心区",
            label: "天心区",
          },
          {
            value: "其他区",
            label: "其他区",
          },
        ],
      },
      {
        value: "湘潭市",
        label: "湘潭市",
        children: [
          {
            value: "邵山市",
            label: "邵山市",
          },
          {
            value: "湘潭县",
            label: "湘潭县",
          },
          {
            value: "雨湖区",
            label: "雨湖区",
          },
          {
            value: "其他区",
            label: "其他区",
          },
        ],
      },
      {
        value: "郴州市",
        label: "郴州市",
        children: [
          {
            value: "苏仙区",
            label: "苏仙区",
          },
          {
            value: "安仁县",
            label: "安仁县",
          },
          {
            value: "北湖区",
            label: "北湖区",
          },
          {
            value: "其他区",
            label: "其他区",
          },
        ],
      }
    ]
  },
  {
    value: "湖北省",
    label: "湖北省",
    children: [
      {
        value: "武汉市",
        label: "武汉市",
        children: [
          {
            value: "汉阳区",
            label: "汉阳区",
          },
          {
            value: "其他区",
            label: "其他区",
          }
        ],
      },
      {
        value: "襄阳市",
        label: "襄阳市",
        children: [
          {
            value: "襄城区",
            label: "襄城区",
          },
          {
            value: "其他区",
            label: "其他区",
          }
        ],
      }
    ]
  },
]


function onUpdate(){
  updateUser(userInfo)
  layer.msg("收货地址已保存",{time: 1500})
  display.value = false
}

</script>

<style scoped lang="scss">
.address{
  font-size: 18px;
  height: 370px;
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

.layui-form-item{
  border-bottom: 2px solid #426c42;
}
.layui-input{
  margin-bottom: 5px;
}
.layui-cascader{
  width: 100%;
  margin-bottom: 5px;
}
</style>