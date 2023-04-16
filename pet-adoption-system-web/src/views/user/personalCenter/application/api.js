import http from "../../../../config/http.js";
export function list(userInfo){
    return http.get("/adoption/findName?username="+userInfo.username, {
    }).then(res=>{
        return res;
    })
}

