<template>
  <lay-menu :selected-key="selectedKey" @change-selected-key="onChangeSelected" v-model:openKeys="openKeys" :tree="true">
    <lay-sub-menu id="system-manager">
      <template #title>
        系统管理
      </template>
      <lay-menu-item id="user-manager" to="/userDisplay">用户管理</lay-menu-item>
      <lay-menu-item id="role-manager" to="/petManger">宠物管理</lay-menu-item>
      <lay-menu-item id="permission-manager" to="/shopping">商城管理</lay-menu-item>
      <lay-menu-item id="comment=manager" to="/comment">评论管理</lay-menu-item>
      <lay-menu-item id="image-manager" to="/adoption">领养管理</lay-menu-item>
    </lay-sub-menu>
  </lay-menu>
</template>

<script setup>
import {ref} from "vue";

const selectedKey = ref('user-manager');
const openKeys = ref(['system-manager']);
function onChangeSelected(key){
  selectedKey.value = key;
}
</script>

<style scoped>

</style>