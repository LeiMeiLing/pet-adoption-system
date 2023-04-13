<template>
  <div class="personalCenter">
    <lay-layout>
      <lay-side >
        <lay-menu  class="menu" :selected-key="selectedKey" @change-selected-Key="changeSelectedKey" @change-open-keys="changeOpenKeys" v-model:openKeys="openKeys2" :tree="true">
        <lay-menu-item id="1" to="/center">个人中心</lay-menu-item>
        <lay-menu-item id="2"  @click="changeVisible1">个人信息
          <lay-layer title="基础使用" v-model="visible1" :area="['500px','500px']">
            内容
          </lay-layer>
        </lay-menu-item>
        <lay-menu-item id="3" to="/address">收货地址</lay-menu-item>
        <lay-menu-item id="4" to="/invitation">我的帖子</lay-menu-item>
        <lay-menu-item id="5" to="application">领养申请</lay-menu-item>
        <lay-menu-item id="6" to="transfer">转送宠物</lay-menu-item>
      </lay-menu></lay-side>
      <lay-body>
       <router-view></router-view>
      </lay-body>
    </lay-layout>


    </div>

</template>

<script setup>
import { ref, watch } from "vue";
import { useRoute, useRouter } from "vue-router";
const visible1 = ref(false)
const changeVisible1 = function() {
  visible1.value = !visible1.value
}
const openKeys2 = ref(["1"])
const selectedKey = ref("1")
const changeSelectedKey = (val) => {
  selectedKey.value = val;
}

const changeOpenKeys = (val) => {
  openKeys2.value = val;
}


</script>

<style scoped lang="scss">
.personalCenter{
  height: 100%;
  width: 100%;
  font-size: 25px;
}
.layui-side{
  min-height: 1000px;
  background-color: #638863;

}
.layui-body{
  min-height: 1000px;
}
.menu{
  background-color: #638863;
}
:deep(.layui-nav) *{
  font-size: 18px !important;
}

</style>