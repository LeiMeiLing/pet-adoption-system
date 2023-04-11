<template>
  <div class="essay">
        <lay-breadcrumb separator=">">
          <lay-breadcrumb-item title="主页"></lay-breadcrumb-item>
          <lay-breadcrumb-item title="宠物知识"></lay-breadcrumb-item>
          <lay-breadcrumb-item title="异宠"></lay-breadcrumb-item>
        </lay-breadcrumb>
        <h1>
          推荐10种好养的宠物鸟及排名（宠物鸟种类有哪些）
        </h1>
    <div>
      &emsp;&emsp;当鸟类成为宠物的时候，很多人都是绝对不可思议的。实际上，现在越多越多的人们喜欢鸟宠，并想要自己养一只，不仅可陶冶情操，也可以解忧乏闷。那么宠物鸟该如何选择呢?有哪些宠物鸟呢?下面咱们就来看看宠物鸟的种类。
      一般家庭养的鸟类，建议是纯粹的观赏型鸟。一则羽毛颜色艳丽，鸣叫动听的，如：芙蓉鸟。二则人工繁殖的宠物鸟，比如，文鸟类，鹦鹉类。
    </div>
        <div class="picture" @click="signleImg">
        </div>
    <div>
      &emsp;&emsp;当鸟类成为宠物的时候，很多人都是绝对不可思议的。实际上，现在越多越多的人们喜欢鸟宠，并想要自己养一只，不仅可陶冶情操，也可以解忧乏闷。那么宠物鸟该如何选择呢?有哪些宠物鸟呢?下面咱们就来看看宠物鸟的种类。
      一般家庭养的鸟类，建议是纯粹的观赏型鸟。一则羽毛颜色艳丽，鸣叫动听的，如：芙蓉鸟。二则人工繁殖的宠物鸟，比如，文鸟类，鹦鹉类。
    </div>
    <br>
    <div class="icon">
      <lay-icon v-model="praise"  color="#FFB800" size="40px" @click="onLike"></lay-icon>
      <lay-icon v-model="tread" color="#1E9FFF" size="40px" @click="unLike"></lay-icon>
    </div>
    <br>
        <a href="">
          相关推荐
        </a>
    <lay-page v-model="currentPage" :limit="limit" :total="total" :show-page="true"></lay-page>

  </div>


</template>

<script setup>
import { ref } from 'vue'
import { layer } from  "@layui/layui-vue"
const limit = ref(10)
const total = ref(50)
const currentPage = ref();

let status=false;

const signleImg = function(){
  layer.photos("/public/注册.png")
}
function onLike(){
  if(status=false){
    layer.msg("赞");
  }
  status=true;
}
function unLike(){
  layer.msg("踩");
}
</script>

<style scoped>
.essay{
  margin: 40px;
  font-size: 32px;
}

.picture{
  width: 400px;
  height: 400px ;
  display: flex;
  justify-content: center;
  align-items: center;
  background: url("/public/注册.png");
  background-size: cover;
}
.icon{
  display: flex;
  justify-content: space-around;
}
</style>