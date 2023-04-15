
import http from "../../../../config/http.js";

export function updateUser(updateUserInfo) {
    return http.put("/user/update",{
        "id":updateUserInfo.id,
        "phone":updateUserInfo.phone,
        "email":updateUserInfo.email
    }).then(res=>{
        return res;
    })
}