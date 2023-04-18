<template>
 <div class="release">
     <lay-form class="">
       <lay-form-item label="标题:">
         <lay-input placeholder="请输入文章标题" v-model="petKnowledge.title"></lay-input>
       </lay-form-item>
       <lay-form-item label="作者:">
         <lay-input placeholder="请输入作者名" v-model="petKnowledge.author"></lay-input>
       </lay-form-item>
       <lay-form-item label="描述:">
         <lay-input placeholder="请输入文章描述" v-model="petKnowledge.description"></lay-input>
       </lay-form-item>
       <lay-form-item label="内容:" >
         <lay-textarea placeholder="请输入文章内容" v-model="petKnowledge.content"></lay-textarea>
<!--         <lay-input placeholder="请输入文章内容" v-model="petKnowledge.content" style="width: 1000px;height:500px;"></lay-input>-->
       </lay-form-item>
       <lay-form-item label="文章图片:" class="img-input">
         <input type="file" id="file" @change="handleFileChange">
         <img :src="imgbase64" style="width: 150px;height: 100px;margin-right: 20px;margin-top: 10px"/>
       </lay-form-item>
       <lay-form-item label="文章类型:">
         <lay-select v-model="petKnowledge.essayType" placeholder="请选择">
           <lay-select-option :value="'养宠知识'" label="养宠知识"></lay-select-option>
           <lay-select-option :value="'宠物百科'" label="宠物百科"></lay-select-option>
           <lay-select-option :value="'宠物健康'" label="宠物健康"></lay-select-option>
         </lay-select>
       </lay-form-item>
       <lay-form-item >
         <lay-space size="lg">
           <lay-button class="btn" type="primary" @click="onCommit">发布</lay-button>
           <lay-button class="btn" type="primary" @click="visible=false">取消</lay-button>
         </lay-space>
       </lay-form-item>
     </lay-form>
 </div>
</template>

<script setup>
import {reactive, ref} from "vue";
import {insert} from "../petKnowledge/api.js";
import {layer} from "@layui/layui-vue";

const visible=ref(false)
const petKnowledge=reactive({
  title:"",
  author:"",
  description:"",
  content:"",
  picture:"",
  essayType:"",
})
function onCommit(){
  petKnowledge.picture = abc
  insert(petKnowledge).then(res=>{
    layer.msg(res.msg)
    visible.value=false
  }).catch(err=>{
    layer.msg(err.msg)
  })
}


</script>

<style scoped lang>
</style>
<script>

var abc;


export default {
  data(){
    return{
      imgbase64:""
    }
  },
  methods:{
    handleFileChange(e){
      let file = e.target.files[0];
      //缩略图
      let fr = new FileReader()
      fr.readAsDataURL(file)

      let that = this
      fr.onload = function () {
        that.imgbase64 = fr.result
        abc=that.imgbase64
      }
    }
  },
  submit() {
  }
}
</script>