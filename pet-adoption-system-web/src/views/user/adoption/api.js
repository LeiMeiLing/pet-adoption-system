import http from "../../../config/http";
export function list(){
    return http.get("/pet/findAll", {
    }).then(res=>{
        return res;
    })
}
export function findAll(page,limit){
        return http.get("/pet/find?page="+page+"&limit="+limit,{
        }).then(res=>{
            return res;
        })
}