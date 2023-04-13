<template>
  <div class="password">
    <lay-icon type="layui-icon-close" ></lay-icon>
    <h1>修改密码</h1>
    <lay-form>
      <lay-form-item label="原密码："><lay-input></lay-input></lay-form-item>
      <lay-form-item label="新密码："><lay-input></lay-input></lay-form-item>
      <lay-form-item label="确认密码："><lay-input></lay-input></lay-form-item>
      <lay-button type="primary" fluid="" >保存</lay-button>
    </lay-form>

  </div>
</template>

<script setup>

</script>

<style scoped lang="scss">
.password{

  height: 400px;
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