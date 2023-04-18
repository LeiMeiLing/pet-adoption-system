<template>
  <div class="petKnowledge">
    <lay-split-panel style="height: 450px">
      <lay-split-panel-item :space="1200" >
        <div class="picture"></div>
      </lay-split-panel-item>

      <lay-split-panel-item>
        <div class="text">
          几句话关于我们的宠物领养
        </div>
        <div class="text1">面对这样可爱的猫和狗谁能不喜爱呢？伤心时，它总是在我身边陪伴着我;开心时，我总会和它一起分享，它成了我形影不离的好朋友。</div>
        <div class="content">
          <ul>
            <li>最新</li>
            <li>养宠知识</li>
            <li>宠物百科</li>
            <li>宠物健康</li>
          </ul>
          <lay-qrcode text="https://www.chongbaibai.com/" :width="250"  color="#009688" class="qrcode"></lay-qrcode>
        </div>
        <div class="tip">
          扫描二维码了解更多宠物小知识
        </div>
<!--        <lay-button @click="onRelease">-->
<!--            发布-->
<!--        </lay-button>-->

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
import Release from "./Release.vue";
const router=useRouter();
// function onRelease(){
//   router.push('/release')
// }
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
  background: url("../../../../../public/petKnowledge.jpeg");
  background-size: cover;
}
.petKnowledge{
  margin:50px 100px;
  font-size: 25px;
}
.text{
  font-weight: bolder;
}
ul{
  color: #5b5454;
  font-size: 20px;
  margin-top: 20px;
}
li::before{
  content: '💖 ';
  line-height: 50px;
  text-align: center;
}
.content{
  display: flex;
  justify-content: space-between;
}

.tip{
  float: right;
  font-size: 15px;
  margin-right: 17px;
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