import http from "../../../config/http";
export function list(){
    return http.get("/pet/findAll", {
    }).then(res=>{
        return res;
    })
}