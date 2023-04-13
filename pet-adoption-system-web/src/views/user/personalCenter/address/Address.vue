<template>
  <div class="address">
    <lay-icon type="layui-icon-close" ></lay-icon>
    <h1>收货地址</h1>
    <lay-form>
      <lay-form-item label="收货人："><lay-input></lay-input></lay-form-item>
      <lay-form-item label="手机号码："><lay-input></lay-input></lay-form-item>
      <lay-form-item label="详细地址："><lay-input></lay-input></lay-form-item>
      <lay-button type="primary" fluid >保存收货地址</lay-button>
    </lay-form>

  </div>
</template>

<script setup>

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