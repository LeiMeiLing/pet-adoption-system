import http from "../../../config/http.js";
export function findAll(){
    return http.get("/petstore/findAll").then(res=>{
        return res;
    })
}