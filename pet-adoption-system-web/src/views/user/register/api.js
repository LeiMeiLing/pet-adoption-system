import http from "../../../config/http";
export function register(userInfo){
    return http.put("/user/register", {
        ...userInfo
    }).then(res=>{
        return res;
    })
}