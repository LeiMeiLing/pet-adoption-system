import {defineStore} from "pinia"

export default  defineStore("loginInfo",{
    state:()=>({
        token: "",
        userInfo: null
    }),
    persist:{
      enabled:true
    },
    actions:{
        setToken(token){
            this.token = token;
        },
        setUserInfo(userInfo){
            this.userInfo = userInfo;
        },
        logout(){
            this.token = "";
            this.userInfo = null;
        }
    },
    getters:{
        isLogin(){
            return ()=> !!this.token;
        }
    }

})
