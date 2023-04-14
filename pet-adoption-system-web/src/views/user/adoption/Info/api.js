import http from "../../../config/http";
export function select(){
    return http.get("/pet/findId", {
    }).then(res=>{
        return res;
    })
}