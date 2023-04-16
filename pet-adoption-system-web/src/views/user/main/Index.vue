<template>
  <!--首页-->
  <lay-layout class="main">
    <lay-header>
      <Index/>
      <lay-field title="宠物种类展示">
        <lay-quote>狗狗</lay-quote>
        <div class="dog">
          <div class="dog-img">
            <img src="public/宠物2.png">
            <div class="content">
              苏俄牧羊犬(是一种绅士、安静、可爱的伴侣,别称：俄罗斯猎狼犬)
            </div>
            <lay-breadcrumb>
              <lay-breadcrumb-item >2023年4月12日</lay-breadcrumb-item>
            </lay-breadcrumb>
          </div>
          <div class="dog-img">
            <img src="public/宠物.png">
            <div class="content">
              博美犬（是一种紧凑、短背、活跃的玩具狗。俗称德国狐狸犬）
            </div>
            <lay-breadcrumb>
              <lay-breadcrumb-item >2023年4月12日</lay-breadcrumb-item>
            </lay-breadcrumb>
          </div>
          <div class="dog-img">
            <img src="public/宠物5.png">
            <div class="content">
              比熊犬（是一种娇小的，强健的白色粉扑型的狗）
            </div>
            <lay-breadcrumb>
              <lay-breadcrumb-item >2023年4月12日</lay-breadcrumb-item>
            </lay-breadcrumb>
          </div>
          <div class="dog-img">
            <img src="public/宠物6.png">
            <div class="content">
              美国斗牛犬（是一种非常优秀的护卫犬,性格充满了野性和粗鲁）
            </div>
            <lay-breadcrumb>
              <lay-breadcrumb-item >2023年4月12日</lay-breadcrumb-item>
            </lay-breadcrumb>
          </div>
        </div>
        <lay-quote>猫咪</lay-quote>
        <div class="cat">
          <div class="pet-img">
            <img src="public/猫咪.png">
            <div class="content">
              加菲猫（加菲猫性格好静，可爱，温纯及忠诚非常合适家养）
            </div>
            <lay-breadcrumb>
              <lay-breadcrumb-item >2023年4月12日</lay-breadcrumb-item>
            </lay-breadcrumb>
          </div>
          <div class="pet-img">
            <img src="public/猫咪5.png">
            <div class="content">
              高地折耳猫（高地折耳猫喜欢躺着睡觉陪伴在主人身边）
            </div>
            <lay-breadcrumb>
              <lay-breadcrumb-item >2023年4月12日</lay-breadcrumb-item>
            </lay-breadcrumb>
          </div>
          <div class="pet-img">
            <img src="public/猫咪6.png">
            <div class="content">
              安哥拉猫（是温顺的天使，爱干净）
            </div>
            <lay-breadcrumb>
              <lay-breadcrumb-item >2023年4月12日</lay-breadcrumb-item>
            </lay-breadcrumb>
          </div>
          <div class="pet-img">
            <img src="public/猫咪7.png">
            <div class="content">
              金吉拉猫（是一只猫贵族，起源于英国）
            </div>
            <lay-breadcrumb>
              <lay-breadcrumb-item >2023年4月12日</lay-breadcrumb-item>
            </lay-breadcrumb>
          </div>
        </div>
      </lay-field>
      <lay-field title="网站小提示">
        该网站是用于想领养宠物和想转让心爱宠物，那么你将在这里实现你的想法。<br>
        如果你是领养者，请认真选取想要领养的宠物。<br>
        领养后请认真负责对待可爱的猫狗！<br>
        如果你是转让者，请一定要写清楚宠物的信息!<br>
        本网站还提供了宠物秀，可以在那里晒<br>
        宠物日常以及宠物商铺可以选择你需要的好物！<br>
      </lay-field>
    </lay-header>
  </lay-layout>
</template>

<script setup>
import Index from "../main/carousel/Index.vue";
import {reactive, ref} from "vue";
const time=ref('2023年4月12日')

import {list} from "./api.js";

let dataSource = reactive([])
list().then(res=>{
  dataSource.length=0
  dataSource.push(...res.data);
})
</script>

<style scoped lang="scss">
.content{
  margin-top: 10px;
  font-size: 18px;
  font-family: 微软雅黑体;
  margin-bottom: 10px;
}
.dog,.cat{
  margin-bottom: 10px;
  display: flex;

  .dog-img,.pet-img{
    margin-left: 60px;
    height: 300px;
    width: 280px;
    margin-right: 15px;
    border-radius: 5px;
    border:1px solid #ccc ;
    transition: all 0.3s;/* 上浮这个过程需要的时间 */
  }
}
img{
  height: 200px;
  width: 280px;
  border-radius: 5px;

}
.dog-img:hover,.pet-img:hover{
  box-shadow: 0 16px 32px 0 rgba(48, 55, 66, 0.15);/* 鼠标悬浮时盒子出现的阴影 */
  transform: translate(0, -5px);/* 鼠标悬浮时盒子上移10px */
  cursor: pointer;
}
.layui-body{
  min-height:600px;
  .layui-field{
    margin-top: 10px;
    text-align: center;
    .pet{
      margin-left: 10px;
    }
  }
}

</style>