<template>
  <div class="main">
      <div v-for="data in dataSource">
          {{data}}
          <img v-bind:src="data.goodsPicture" alt="">
      </div>
  </div>
</template>

<script setup>
import {showMyCart} from "./api.js";
import useLogin from "../../../../stores/LoginStore.js"
import {reactive} from "vue";

let dataSource = reactive([])
const userInfo = useLogin().userInfo;
showMyCart(userInfo.id).then(res=>{
    console.log(res)
    dataSource.push(...res.data.list);
    console.log(dataSource)

})
</script>

<style scoped>

</style>