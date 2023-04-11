<template>
  <lay-layout class="example">
    <lay-header>
      <Header></Header>
    </lay-header>
    <lay-layout>
      <lay-side>
        <Side></Side>
      </lay-side>
      <lay-body>
        <router-view></router-view>
      </lay-body>
    </lay-layout>
  </lay-layout>
</template>

<script>

import {defineComponent} from "vue";
import Header from "./Header.vue";
import Side from "./Side.vue";

export default defineComponent({
  components: {Side, Header}
})
</script>

<style scoped lang="scss">
.layui-layout{
  height: 100vh;
  .layui-header{
    background: #393D49;
    color: #fff;
  }
  .layui-side{
    background: #2F4056;
    color: #fff;
  }
  .layui-body{
    padding: 15px;
  }
}
</style>


