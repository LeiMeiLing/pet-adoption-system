<template>
<!--领养中心-->
  <lay-header>
    <h1>领养中心</h1>
    <lay-body>
      <div>
        <div>
          <img @click="onAdoption" src="../../../../public/宠物1.png">
        </div>
        <lay-field title="Kitty">描述
        </lay-field>
      </div>
      <div>
        <div>
        <img src="../../../../public/宠物1.png">
          <lay-field title="Kitty">描述
          </lay-field>
      </div>
      </div>
      <div>
        <div>
        <img src="../../../../public/宠物1.png">
          <lay-field title="Kitty">描述
          </lay-field>
      </div>
      </div>
    </lay-body>
  </lay-header>
  <lay-page v-model="currentPage" :limit="limit" :total="total" :show-page="true"></lay-page>
</template>

<script setup>
import {ref} from "vue";
import router from "../../../config/router.js";

const limit = ref(3)
const total = ref(10)
const currentPage = ref(1);

function onAdoption(){
  router.push('/Info')
}
</script>

<style scoped lang="scss">

.layui-header{
  margin-top: 80px;
  height: 600px;
  width: 1500px;
.layui-body{
  height: 500px;
  display: flex;
  justify-content: center;
  align-items: center;
}

  img{
    border: 5px solid #638863;
    height: 300px;
    width: 300px;
    border-radius: 50%;
    margin-right: 50px;
  }
  text-align: center;
h1{
  font-size: 60px;
}
.layui-field{
  margin-top: 30px;
   width: 350px;
   height: 100px;
   text-align: center;
}
}
</style>