import {defineStore} from "pinia"

export default defineStore("loginInfo", {
    state: () => ({
        token: "",
        userInfo: null,
        managerInfo: null
    }),
    persist: {
        enabled: true
    },
    actions: {
        setToken(token) {
            this.token = token;
        },
        setUserInfo(userInfo) {
            this.userInfo = userInfo;
        },
        logout() {
            this.token = "";
            this.userInfo = null;
        },
        setManagerInfo(managerInfo) {
            this.managerInfo = managerInfo;
        },
        managerOut(){
        }
    },
    getters: {
        isLogin() {
            return () => !!this.token;
        }
    }
})
