import http from "../../../../config/http.js";
export function setNewPassword(newPassword,userinfo){
    return http.post("/user/changePassword",{
        password:newPassword,
        id:userinfo.id
    })

}
export function confirmPassword(userInfo,oldPassword){
    return http.get("/user/find?id="+userInfo.id+"&password="+oldPassword)
}