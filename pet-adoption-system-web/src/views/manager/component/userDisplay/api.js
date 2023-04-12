



import http from "../../../../config/http";

export function list(){
    return http.get("/user/findAll", {

    }).then(res=>{
        return res;
    })

}