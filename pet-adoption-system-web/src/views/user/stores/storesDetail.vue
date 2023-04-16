<template>
    <div class="out">
        <img v-bind:src="goodsInfo.goodsPicture" alt="" class="img1">
        <div class="main-info">
            <div class="card-container">
                <lay-card>
                    商品名：{{ goodsInfo.goodsname }}
                </lay-card>
                <lay-card shadow="hover">
                    商品类型：{{ goodsInfo.goodsType }}
                </lay-card>
                <lay-card shadow="never">
                    商品简介：{{ goodsInfo.goodsDesc }}
                </lay-card>
                <lay-card shadow="never">
                    商品状态：{{ goodsInfo.goodsStatus==1?'上架':(goodsInfo.goodsStatus==0?'下架':"已删除") }}
                </lay-card>
                <lay-card shadow="never">
                    价格：{{ goodsInfo.goodsPrice }}
                </lay-card>
                <lay-card shadow="never">
                    数量:<lay-button type="default" radius @click="reduce">-</lay-button>
<!--                    数量：{{ goodsInfo.goodsPrice }}-->
                    <lay-input v-model="count" type="number" :max="99" :min="1" class="input1"></lay-input>
                    <lay-button type="default" radius @click="increase">+</lay-button>
                </lay-card>

            </div>
            <lay-button border="red" @click="join">加入购物车</lay-button>
            <lay-button type="danger">确认购买</lay-button>
        </div>

    </div>
</template>

<script setup>
import {useRoute} from "vue-router";
import {reactive, ref} from "vue";
import useLogin from "../../../stores/LoginStore.js"
import {joinCart} from "./api.js";

const route = useRoute();
let goodsInfo = route.query;
const count = ref(1)

const cartInfo = reactive({
    userId: '',
    productId: '',
    quantity: '',
    price: '',
});
const userInfo = useLogin().userInfo;

function join(){
    cartInfo.userId=userInfo.id;
    cartInfo.productId=goodsInfo.id;
    cartInfo.quantity=count.value;
    cartInfo.price=(goodsInfo.goodsPrice) * count.value;
    joinCart(cartInfo)
}
function reduce(){
    if (count.value>1){
        count.value--
    }
}
function increase(){
    if (count.value<99){
        count.value++
    }
}

</script>

<style scoped lang="scss">
.out{
    margin: 50px;
    .img1{
        width: 20%;
        height: 10%;
        float: left;
    }
    .main-info{
        margin: 0  0 0 25%;
        padding: 1% 0 0 0;
    }
}
.input1{
    width: 4%;
    text-align:center;
    vertical-align:middle;
}
</style>