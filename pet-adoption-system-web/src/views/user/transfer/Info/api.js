import http from "../../../config/http";
export function add(applicationInfo){
    return http.put("/application/add", {
        ...applicationInfo
    }).then(res=>{
        return res;
    })
}