<template>
  <div class="header">
    <h1 class="title">宠物领养系统</h1>
    <lay-dropdown>
      <lay-button border="green">
        <div class="avatar"></div>
        <div class="name">aaaa</div>
      </lay-button>
      <template #content>
        <lay-dropdown-menu>
          <lay-dropdown-menu-item>选项一</lay-dropdown-menu-item>
          <lay-dropdown-menu-item>选项二</lay-dropdown-menu-item>
          <lay-dropdown-menu-item>选项三</lay-dropdown-menu-item>
        </lay-dropdown-menu>
      </template>
    </lay-dropdown>

  </div>
</template>

<script setup>

</script>

<style scoped lang="scss">
.header{
  height: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  .layui-btn{
    margin-right: 20px;
  }
}
</style>