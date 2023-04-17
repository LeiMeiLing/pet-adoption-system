import http from "../../../../config/http.js";

export function findAll(id){
    return http.get("/comment/findAll?issueId="+id).then(res=>{
        return res
    })
}

export function findName(id){
    return http.get("/user/findName?id="+id).then(res=>{
        return res
    })
}