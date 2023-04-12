<template>
  <!--宠物转让界面-->
  <lay-layout>
    <lay-body>
      <div class="transfer">
        <lay-panel shadow="hover">
          <div class="content">
            <img src="public/宠物2.png">
            <div class="content-right">
              <h1 @click="onInfo">{{title}}</h1>
              <div class="content-right-data">
                {{data}}
              </div>
              <lay-breadcrumb>
                <lay-breadcrumb-item >{{time}}</lay-breadcrumb-item>
              </lay-breadcrumb>
            </div>
          </div>
        </lay-panel>
        <lay-panel shadow="hover">
          <div class="content">
            <img src="public/宠物2.png">
            <div class="content-right">
              <h1>{{title}}</h1>
              <div class="content-right-data">
                {{data}}
              </div>
            </div>
          </div>
        </lay-panel>
        <lay-panel shadow="hover">
          <div class="content">
            <img src="public/宠物2.png">
            <div class="content-right">
              <h1>{{title}}</h1>
              <div class="content-right-data">
                {{data}}
              </div>
            </div>
          </div>
        </lay-panel>
      </div>
    </lay-body>
    <lay-side>
      <div class="side">
        <lay-quote>宠物资讯</lay-quote>
      </div>
    </lay-side>
  </lay-layout>

</template>

<script setup>
import {ref} from "vue";
import router from "../../../config/router.js";
const time=ref('2023年4月12日')

const title=ref('新西兰的宠物牛奶（牛奶先生零乳糖令人耳目一新）')
const data=ref('新西兰一直给人的印象就是广阔的草原，以及它孕育出的优质奶源，' +
    '尤其在人类婴幼儿奶粉方面尤其突出，新西兰的奶源可以说是奶粉竞争力以及品质的体现。' +
    '不仅仅是对人产业，对于宠物营养品行业来说，新西兰奶源也颇受高端宠物奶粉品牌青睐。')
function onInfo(){
  router.push('/detailed')
}
</script>

<style scoped lang="scss">

.transfer{
  height: 150px;
  width: 1000px;
  margin-top: 20px;
  margin-left: 10px;
  display: inline;
}
.side{
  margin-left: 20px;
}
.content-right-data{
 font-size: 18px;
  cursor: pointer;
  margin-bottom: 10px;
}
.content{
  height: 200px;
  width:1000px ;
  display: flex;
  align-items: center;
}
.content-right{
  margin-left: 10px;
}

h1{
  border: white;
  width:700px;
 margin-bottom: 20px;
  font-size: 20px;
  cursor: pointer;
}
.layui-panel{
  display: flex;
  align-items: center;
}
.side{
  margin-top: 20px;
}

img{
  height: 150px;
  width: 230px;
  border-radius: 5px;
  transition: all 0.3s;/* 上浮这个过程需要的时间 */
}
img:hover{
  box-shadow: 0 16px 32px 0 rgba(48, 55, 66, 0.15);/* 鼠标悬浮时盒子出现的阴影 */
  transform: translate(0, -2px);/* 鼠标悬浮时盒子上移10px */
  cursor: pointer;
}
</style>