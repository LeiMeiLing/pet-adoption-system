<template>
  <div class="petKnowledge">
<!--        <h1>欢&emsp;迎</h1>-->

    <lay-split-panel style="height: 450px">
      <lay-split-panel-item :space="1200" >
        <div class="picture"></div>
      </lay-split-panel-item>

      <lay-split-panel-item>
        <div class="text">
          几句话关于我们的宠物领养
        </div>
        <div class="text1">面对这样可爱的猫和狗谁能不喜爱呢？伤心时，它总是在我身边陪伴着我;开心时，我总会和它一起分享，它成了我形影不离的好朋友。</div>
        <div class="qrcode">
          <ul>
            <li>最新</li>
            <li>养宠知识</li>
            <li>宠物百科</li>
            <li>宠物健康</li>
          </ul>
          <lay-qrcode text="https://www.chongbaibai.com/" :width="250" color="#009688"></lay-qrcode>
        </div>
        <div class="tip">
          扫描二维码了解更多宠物小知识
        </div>


        <!--        <div class="btns">-->
<!--          <lay-button type="primary" @click="onDog">汪星人</lay-button>-->
<!--          <lay-button type="primary" @click="onCat">喵星人</lay-button>-->
<!--        </div>-->
      </lay-split-panel-item>
    </lay-split-panel>
    <cat-and-dog></cat-and-dog>

  </div>
</template>
<script setup>
import {useRouter} from "vue-router";
import CatAndDog from '../petKnowledge/CatAndDog.vue'
const router=useRouter();

</script>
<style scoped lang="scss">
//h1{
//  font-family: 宋体;
//  font-style:italic;
//  font-weight: bold;
//  font-size: 70px;
//  margin: 30px;
//  text-align: center;
//}
.picture{
  width: 90%;
  height: 90% ;
  background: url("/public/注册.png");
  background-size: cover;
}
.petKnowledge{
  margin: 35px;
  font-size: 28px;
}
.text{
  font-size: 50px;
}

.text1{
  font-size: 31px;
}
ul{
  color: #5b5454;
}
li::before{
  content: '💖 ';
  line-height: 60px;
  text-align: center;
}
.qrcode{
  display: flex;
  justify-content: space-between;
}

.tip{
  float: right;
  font-size: 18px;
}
//.btns{
//  display: flex;
//  justify-content: space-around;
//  margin: 20px;
//}
:deep(.lay-split-panel) .lay-split-panel-item{
  border: none!important;
}
</style>