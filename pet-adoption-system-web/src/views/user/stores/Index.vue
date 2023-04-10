<template>

        <div class="selectShopping">
            <lay-input placeholder="请输入商品名进行查询" v-model="shoppingInfo.shoppingName" prefix-icon="layui-icon-search">
            </lay-input>
            <lay-button type="primary">搜索</lay-button>
        </div>

</template>


<script setup>
import {ref} from "vue";
import router from "../../../config/router.js";

const shoppingInfo = ref({
    shoppingName: "",
    password:"",
    checkPassword:"",
    email: "",
    phone: "",
});

</script>


<style scoped lang="scss">
.selectShopping{
    display: flex;
    margin: 10px auto;
    width: 50%;
    .layui-input{
        margin: 0 5px;
    }
}

</style>