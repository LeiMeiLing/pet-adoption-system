<template>
  <!--首页-->
  <lay-layout class="main">
    <lay-header>
      <Index/>
      <lay-field title="热门宠物种类展示">
        <lay-quote>狗狗</lay-quote>
        <div class="dog">
          <div class="dog-img">
            <img src="public/宠物1.png">
            <lay-line></lay-line>
            <div class="content">
              苏俄牧羊犬（附：苏俄牧羊犬简介）
            </div>
          </div>
          <div class="dog-img">
            <img src="public/宠物1.png">
            <lay-line></lay-line>
            <div class="content">
              苏俄牧羊犬（附：苏俄牧羊犬简介）
            </div>
          </div>
          <div class="dog-img">
            <img src="public/宠物1.png">
            <lay-line></lay-line>
            <div class="content">
              苏俄牧羊犬（附：苏俄牧羊犬简介）
            </div>
          </div>
          <div class="dog-img">
            <img src="public/宠物1.png">
            <lay-line></lay-line>
            <div class="content">
              苏俄牧羊犬（附：苏俄牧羊犬简介）
            </div>
          </div>
        </div>
        <lay-quote>猫咪</lay-quote>
        <div class="cat">
          <div class="pet-img">
            <img src="public/宠物1.png">
            <lay-line></lay-line>
            <div class="content">
              苏俄牧羊犬（附：苏俄牧羊犬简介）
            </div>
          </div>
          <div class="pet-img">
            <img src="public/宠物1.png">
            <lay-line></lay-line>
            <div class="content">
              苏俄牧羊犬（附：苏俄牧羊犬简介）
            </div>
          </div>
          <div class="pet-img">
            <img src="public/宠物1.png">
            <lay-line></lay-line>
            <div class="content">
              苏俄牧羊犬（附：苏俄牧羊犬简介）
            </div>
          </div>
          <div class="pet-img">
            <img src="public/宠物1.png">
            <lay-line></lay-line>
            <div class="content">
              苏俄牧羊犬（附：苏俄牧羊犬简介）
            </div>
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
</script>

<style scoped lang="scss">
.content{
  font-size: 18px;
  font-family: 微软雅黑体;
}
.dog,.cat{
  margin-bottom: 10px;
  display: flex;

  .dog-img,.pet-img{
    height: 300px;
    width: 280px;
    margin-right: 15px;
    border-radius: 5px;
    border:1px solid #ccc ;
  }
}
img{
  height: 200px;
  width: 280px;
  border-radius: 10%;
}
.dog-img,.pet-img:hover{
   transition-property: all;
  transition-duration: 0.25s;
  transition-timing-function: linear;
  box-shadow: 3px 3px 3px 3px #d2cbcb;
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