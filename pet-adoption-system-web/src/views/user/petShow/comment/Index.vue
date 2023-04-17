<template>
  <div class="container">



    <div class="content">
     <b style="color: #1aa2d4;font-size: 22px">{{ route.query.petName }}</b>
      <div style="margin-top: 20px">{{ route.query.content }}</div>
  <img :src="route.query.picture" style="Display: inline-block">
</div>

<!--发布评论区域-->
    <div class="comment-send">
      <form id="commentForm" method="post" action="api/comment" target="iframe">
         <span class="comment-avatar">
<!--                     <img src="avatar1.jpg" alt="头像" >-->
           <br><br>
                      <div style="font-size: 18px">{{ loginInfo.username }}:</div>
                 </span>
        <textarea class="comment-send-input" name="content" form="commentForm" cols="80" rows="5"
                  placeholder="请自觉遵守互联网相关的政策法规，严禁发布色情、暴力、反动的言论。" style="height: 40px;margin-top: 15px"></textarea>
        <input  name="issueId"  v-model="route.query.id" type="hidden"/>
        <input  name="commentId" v-model="loginInfo.id" type="hidden"/>
        <input  name="commentName" v-model="loginInfo.username" type="hidden"/>
        <input class="comment-send-button" type="submit" value="发表评论" style="height: 40px;margin-top: 16px" @click="sub">
      </form>
      <iframe id="iframe" name="iframe" style="display:none;"></iframe>
    </div>



    <!--展示评论区域-->
    <div class="comment-list" id="commentList">

      <div class="comment" v-for="(comment, index) in dataSource">
<!--        <span class="comment-avatar">
                     <img src="avatar1.jpg" alt="avatar">
                 </span>-->

        <div class="comment-content">
          <br>
          <p class="comment-content-name">
            <lay-icon type="layui-icon-username"></lay-icon>{{ comment.commentName }}</p>
          <p class="comment-content-article">{{ comment.content }}</p>
          <p class="comment-content-footer" >
<!--            <span class="comment-content-footer-id">#2</span>-->
            <span class="comment-content-footer-device">#{{ index+1 }}</span>
            <span class="comment-content-footer-timestamp">{{ comment.createTime }}</span>
          </p>

        </div>

        <div class="cls"></div>

      </div>

    </div>

  </div>
</template>


<script setup>
import useLogin from "../../../../stores/LoginStore.js"
import {useRoute} from "vue-router";
import {layer} from "@layui/layui-vue";
import {findAll, findName} from "./api.js";
import {onMounted, onUpdated, reactive} from "vue";



const route = useRoute();

const loginInfo = useLogin().userInfo

let dataSource = reactive([])

function sub(){
  layer.msg("发表成功",500)
  onUpdated()
}

function reload(){
  findAll(route.query.id).then(res=>{
    dataSource.length = 0
    dataSource.push(...res.data.list)
  })
}

function getName(id) {
   return findName(id).then(res=>{
     return res
  })
}


onMounted(reload)
/*onUpdated(reload)*/

</script>



<style scoped lang="scss">
.content{
 margin-top: 10px;
  font-size: 18px;
}
img{
  margin-top: 10px;
}
* {
  margin: 0;
  padding: 0;
  font-family: Microsoft YaHei, Arial, Helvetica, sans-serif;
}

.cls {
  clear: both;
}

.container {
  width: 780px;
  min-height: 10px;
  margin: 50px auto;
  /* border: 1px solid #dfdfdf; */
}

.comment {
  min-height: 60px;
  /* border: 1px solid red; */
}

.comment-avatar img {
  margin: 20px 0 0 20px;
  width: 48px;
  height: 48px;
  border-radius: 50%;
}

.comment-content {
  float: right;
  width: 685px;
  padding-top: 15px;
  border-top: 1px solid #dfdfdf;
}

.comment-bottom > .comment-content {
  border-bottom: 1px solid #dfdfdf;
}

.comment-content-name {
  color: #6d757a;
  font-size: 12px;
  margin-bottom: 6px;
}

.comment-content-article {
  font-size: 16px;
  margin-bottom: 10px;
}

.comment-content-footer {
  color: #6d757a;
  font-size: 12px;
  margin-bottom: 15px;
}

.comment-content-footer span {
  margin-right: 10px;
}


.comment-send {
  position: relative;
  margin-bottom: 30px;
}

.comment-send-input {
  outline: none;
  box-sizing: border-box;
  -moz-box-sizing: border-box;
  transition: all 0.3s;
  position: absolute;
  top: 15px;
  left: 95px;
  resize: none;
  width: 605px;
  height: 65px;
  padding: 10px;
  border-radius: 4px;
  background: #f4f5f7;
  border: 1px solid #e5e9ef;
}

.comment-send-input:hover, .comment-send-input:active {
  background: #fff;
  color: #555;
}

.comment-send-button {
  border: none;
  cursor: pointer;
  user-select: none;
  -moz-user-select: none;
  transition: all 0.3s;
  position: absolute;
  top: 15px;
  right: 0px;
  width: 65px;
  height: 65px;
  border-radius: 4px;
  background: #1aa2d4;
  color: #fff;
  font-size: 14px;
  text-align: center;
}

.comment-send-button:hover {
  background: #1eb6e3;
}
</style>