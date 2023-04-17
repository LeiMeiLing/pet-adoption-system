import http from "../../../config/http.js";

export function add(row){
    return http.put("/petIssue", {
            ...row
                }).then(res=>{
        return res;
    })
}

export function findAll(page,limit){
    if(page != null||limit != null){
        return http.get("/petIssue/findAll?page="+page+"&limit="+limit,{
        }).then(res=>{
            return res;
        })
    }else {
        return http.get("/petIssue/findAll", {
        }).then(res=>{
            return res;
        })
    }
}