import http from "../../../../../config/http.js";

export function findAll(issueId,page,limit){
    if(page != null||limit != null){
        return http.get("/comment/findAll?issueId="+issueId+"&page="+page+"&limit="+limit,{
        }).then(res=>{
            return res;
        })
    }else {
        return http.get("/comment/findAll?issueId="+issueId,{
        }).then(res=>{
            return res;
        })
    }

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
