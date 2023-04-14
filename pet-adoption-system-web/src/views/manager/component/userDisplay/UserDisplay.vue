<template>
  <div class="user">
    <div class="up">
      <lay-input :allow-clear="true" v-model="user.username" placeholder="输入用户名">
        <template #prefix>用户名:</template>
      </lay-input>

      <lay-input :allow-clear="true" v-model="user.email" placeholder="输入邮箱">
        <template #prefix>邮箱:</template>
      </lay-input>

      <lay-input :allow-clear="true" v-model="user.phone" placeholder="输入手机号">
        <template #prefix>手机号:</template>
      </lay-input>

      <lay-button type="normal" @click="find">
        <lay-icon type="layui-icon-search" size="30" @click="showUser(row)"></lay-icon></lay-button>
    </div>


    <div class="table">
      <lay-table :columns="columns"
                 :data-source="data"
                 :page="page"
                 @change="onPageChange"
                 :default-toolbar="true"
                 even>
        <template #action="{row}">

          <lay-button type="danger" radius size="sm" style="width: 40px" @click="deleteUser(row)">
            <lay-icon type="layui-icon-delete" size="18"></lay-icon>
          </lay-button>

          <lay-button type="normal" radius size="sm" style="width: 40px">
            <lay-icon type="layui-icon-edit" size="18" @click="showUser(row)"></lay-icon>
          </lay-button>
        </template>
      </lay-table>


      <lay-layer v-model="updateUserDisplay" :area="['400px','450px']">
        <lay-form-item label="用户名称">
          <lay-input v-model="userInfoUpdate.username" placeholder="请输入用户名"></lay-input>
        </lay-form-item>

        <lay-form-item label="邮箱">
          <lay-input v-model="userInfoUpdate.email" placeholder="请输入邮箱"></lay-input>
        </lay-form-item>

        <lay-form-item label="手机号">
          <lay-input v-model="userInfoUpdate.phone" placeholder="请输入手机号"></lay-input>
        </lay-form-item>

        <lay-form-item>
          <lay-button type="primary" @click="updateUserInfo" style="width: 80px">修改</lay-button>
        </lay-form-item>

      </lay-layer>
    </div>


  </div>


</template>

<script setup>
import {onMounted, onUpdated, reactive, ref} from "vue";
import {list, updateUser, findSome, deleteUserInfo} from "./api.js";
import {layer} from "@layui/layui-vue"


const username = ref("")
const email = ref("")
const phone = ref("")

const columns = reactive([
  {title: "ID", key: "id", align: "center"},
  {title: "用户名", key: "username", align: "center"},
  {title: "邮箱", key: "email", align: "center"},
  {title: "手机号", key: "phone", align: "center"},
  {title: "创建时间", key: "createTime", align: "center"},
  {title: "更新时间", key: "updateTime", align: "center"},
  {title: "操作", customSlot: "action", align: "center"}
])


const data = reactive([])
const updateUserDisplay = ref(false)
//查询
const user = reactive({
  username: "",
  email: "",
  phone: ""
})

//修改用户
const userInfoUpdate = reactive({
  id: "",
  username: "",
  email: "",
  phone: "",
})


const page = reactive({
  total: 10,
  limit: 8,
  current: 1,
  showRefresh: true,
  limits: [3, 4, 5, 6, 7, 8]
})


//绑定更新界面，更新用户信息
function updateUserInfo() {
  console.log(userInfoUpdate)
  updateUser(userInfoUpdate)

  layer.msg("修改成功")
  updateUserDisplay.value = false
  reload()
}

function reload() {
  list(user.username, user.email, user.phone, page.limit, page.current).then(res => {
    data.length = 0
    console.log(res.data);
    data.push(...res.data.list)
    page.current = res.data.current
    page.total = res.data.total
  })
}

function showUser(row) {
  updateUserDisplay.value = true
  userInfoUpdate.id = row.id
  userInfoUpdate.username = row.username
  userInfoUpdate.email = row.email
  userInfoUpdate.phone = row.phone
}

//删除用户信息
function deleteUser(row) {
  // confirm(""+row.username+"是否要删除吗？")
  // deleteUserInfo(row.id)
  // find()
  layer.confirm(`是否删除${row.username}?`, {
    btn: [
      {
        text: "否",
        callback(id) {
          layer.close(id)
          find()
        }
      },

      {
        text: "是",
        callback(id) {
          deleteUserInfo(row)
          layer.msg("删除成功")
          layer.close(id)
          reload()
        }
      }
    ]
  })
}

//查找用户信息
function find() {
  findSome(user.username, user.email, user.phone).then(res => {
    data.length = 0
    data.push(...res.data.list)

  })
}

function onPageChange({current, limit}) {
  page.current = current
  console.log(page.current);
  page.limit = limit
  console.log(page.limit);
  reload()
}


onMounted(reload)
/*onUpdated(reload)*/
</script>

<style scoped lang="scss">
.user {
  width: 100%;
  height: 100%;

  .up {
    display: flex;

    .layui-input-has-prefix {
      width: 25%;
      margin-left: 20px;
    }

    .layui-select {
      margin-left: 20px;
    }

  }

  .layui-space-align-center {
    margin-left: 20px;
    margin-top: 20px;
  }

  button {
    width: 8%;
    margin-left: 20px;
  }

  .table {
    margin-top: 20px;
  }

}
</style>