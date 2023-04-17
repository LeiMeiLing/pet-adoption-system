
import http from "../../../../config/http.js";

export function updateUser(updateUserInfo) {
    return http.put("/user/update",{
        ...updateUserInfo
    }).then(res=>{
        return res;
    })
}