import http from "../../../../../config/http.js";

export function findAll(issueId){
    return http.get("/comment/findAll?issueId="+issueId,{
    }).then(res=>{
        return res;
    })
}

export function deleteComment(id){
    return http.delete("/comment?id="+id,{
    }).then(res=>{
        return res;
    })
}

export function findSome(row){
    return http.get("/comment/findSome?issueId="+row.issueId+"&commentName="+row.username+"&content="+row.content,{
    }).then(res=>{
        return res;
    })
}
