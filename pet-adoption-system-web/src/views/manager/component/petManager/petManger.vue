<template>
  <div>
    <lay-row space="10">

      <lay-col>

        <lay-col md="11"><lay-input placeholder="请输入宠物名" v-model="petInfo.petName">
          <template #prepend>宠物名</template>
        </lay-input></lay-col>
        <lay-col md="11"><lay-input placeholder="请输入宠物品种" v-model="petInfo.variety">
          <template #prepend>品种</template>
        </lay-input></lay-col>
      </lay-col >

    </lay-row>
    <lay-row space="10">
      <lay-col md="11"><lay-input placeholder="请输入性别" v-model="petInfo.petSex">
        <template #prepend>性别</template>
      </lay-input></lay-col>
      <lay-col md="11"><lay-input placeholder="请输入领养状态" v-model="petInfo.petStatus">
        <template #prepend>领养状态</template>
      </lay-input></lay-col>
      <lay-col md="2">
        <lay-button type="normal" @click="find">
          <lay-icon type="layui-icon-search">
          </lay-icon>
        </lay-button>
      </lay-col>
    </lay-row>


    <lay-layer title="新增宠物" v-model="addPetVisibel" :area="['400px','600px']">

     <lay-form class="add-form">
       <lay-form-item label="宠物昵称:">
         <lay-input v-model="petAdd.petName" placeholder="请输入宠物昵称"></lay-input>
       </lay-form-item>

       <lay-form-item label="宠物类型:">
         <lay-input v-model="petAdd.variety" placeholder="请输入宠物类型"></lay-input>
       </lay-form-item>

       <lay-form-item label="宠物图片:">
         <input type="file" id="file" @change="handleFileChange">
         <img :src="imgbase64" style="width: 100px"/>
       </lay-form-item>

       <lay-form-item label="宠物性别:">
         <lay-select v-model="petAdd.petSex" placeholder="请选择">
           <lay-select-option :value="'雌性'" label="雌性"></lay-select-option>
           <lay-select-option :value="'雄性'" label="雄性"></lay-select-option>
           <lay-select-option :value="'无'" label="无"></lay-select-option>
         </lay-select>
       </lay-form-item>
       <lay-form-item label="领养状态:">
         <lay-input v-model="petAdd.petStatus" placeholder="请输入状态"></lay-input>
       </lay-form-item>
       <lay-form-item label="宠物简介:">
         <lay-textarea placeholder="请输入描述" v-model="petAdd.description">
         </lay-textarea>
       </lay-form-item>

       <lay-form-item>
         <lay-button type="primary" @click="onAdd" style="width: 80px">新增</lay-button>
       </lay-form-item>
     </lay-form>

    </lay-layer>


    <lay-row space="10">

      <lay-col>

        <lay-table :columns="columns"
                   :data-source="data"
                   :page="page"
                   @change="change"
                   :default-toolbar="true"
                    >

          <template #pic = "{ row }">
            <img :src="row.petPicture">
          </template>




          <template #action="{row}">
            <lay-button type="danger" size="sm" @click="petDelete(row)">
              <lay-icon type="layui-icon-delete" size="20px">
              </lay-icon>
            </lay-button>
            <lay-button type="normal" size="sm" @click="showPet(row)">
              <lay-icon type="layui-icon-edit" size="20px" >
              </lay-icon>
            </lay-button>
          </template>
          <template #toolbar>
            <lay-tooltip content="新增宠物">
              <lay-button type="primary" @click="addPetVisibel=true">
                <lay-icon type="layui-icon-add-one"></lay-icon>
              </lay-button>
            </lay-tooltip>
          </template>
        </lay-table>


      </lay-col>
    </lay-row>


    <lay-layer v-model="updatePetDisplay" :area="['400px','450px']">
      <lay-form-item label="宠物名称" >
        <lay-input v-model="petInfoUpdate.petName" placeholder="请输入宠物名称"></lay-input>
      </lay-form-item>
      <input type="file" id="file" @change="handleFileChange">
      <img :src="imgbase64" style="width: 150px"/>
      <lay-form-item label="领养状态">
        <lay-input v-model="petInfoUpdate.petStatus" placeholder="请输入领养状态"></lay-input>
      </lay-form-item>

      <lay-form-item>
        <lay-button type="primary" @click="updatePetInfo" style="width: 80px">修改</lay-button>
      </lay-form-item>

    </lay-layer>
  </div>

</template>

<script>

var _fileObj;
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




<script setup>
import {onMounted, reactive, ref} from 'vue';
import { layer } from "@layui/layer-vue";
import { findSome, list, petDele,add,updatePet} from "./api.js";





const addPetVisibel = ref(false)
const updatePetDisplay = ref(false)
const addPetVisibel = ref(false)
const petAdd = reactive({
  variety: "",
  petName: "",
  petSex: "",
  petStatus: "",
  petPicture:"",
  description:""
})
const petInfoUpdate = reactive({
  petId:"",
  petName: "",
  petStatus: "",
  petPicture:""

})
function updatePetInfo() {
  petInfoUpdate.petPicture = abc
  updatePet(petInfoUpdate)
  layer.msg("修改成功")
  updatePetDisplay.value = false
  reload()
}
function showPet(row) {
  updatePetDisplay.value = true
  petInfoUpdate.petId=row.petId
  petInfoUpdate.petStatus = row.petStatus
  petInfoUpdate.petName = row.petName
  petInfoUpdate.petPicture=row.petPicture

}

const addPetVisibel = ref(false)
const petAdd = reactive({
  variety: "",
  petName: "",
  petSex: "",
  petStatus: "",
  petPicture:"",
  description:""
})
function onAdd() {
  petAdd.petPicture = abc
  add(petAdd)
  layer.msg("新增成功")
  addPetVisibel.value = false;
  reload()
}

var list1 = list();
console.log(list1)
const page = ref({
      total: 100,
      limit: 10,
      current: 2,
      showRefresh: true,
    })
    const petInfo=reactive({
      petName:'',
      variety:'',
      petSex:'',
      petStatus:'',



    })

    const change = ({ current, limit }) => {
      layer.msg ("current:" + current + " limit:" + limit);
    }
    const data=reactive([])
    const columns =reactive( [
      {title: "宠物ID", key:"petId", align:'center'},
      {title: "宠物名", key:"petName", align:'center'},
      {title: "品种", key:"variety", align:'center'},
      {title: "宠物图片", customSlot:"pic", align:'center'},
      {title: "性别", key:"petSex", align:'center'},
      {title: "领养状态", key:"petStatus", align:'center'},
      {title: "创建时间", key:"creatTime", align:'center'},
      {title: "修改时间", key:"updateTime", align:'center'},
      {title: "操作", customSlot:'action', align: "center"},
    ])


    function reload(){
    list().then(res=>{
      data.length=0
      data.push(...res.data)
      console.log(data)
    })
    }
    function petDelete(petInfo){
      layer.confirm(`是否删除${petInfo.petName}?`, {
        btn: [
          {
            text: "否",
            callback(petId) {
              layer.close(petId)
            }
          },
          {
            text: "是",
            callback(petId) {
              console.log(petInfo.petId);
              petDele(petInfo.petId)
              layer.msg("删除成功")
              layer.close(petId)
              reload()
            }
          }
        ]
      })
    }

    function find(){
    findSome(petInfo).then(res=>{
      console.log(res);
      data.length=0;
      data.push(...res.data)
  })
    }
    onMounted(reload)

</script>
<style scoped lang="scss">
.add-form{
  margin-top: 10px;
  .layui-input{
    width: 220px;
  }
}


</style>

