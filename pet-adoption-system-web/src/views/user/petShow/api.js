import http from "../../../config/http.js";

export function add(row){
    return http.put("/petIssue", {
            ...row
                }).then(res=>{
        return res;
    })
}

export function findAll(){
    return http.get("/petIssue/findAll").then(res=>{
        return res
    })
}