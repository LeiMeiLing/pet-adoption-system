import http from "../../../config/http.js";

export function add(row){
    return http.put("/petIssue", {

            content:row.content,
            picture:row.picture,
            commentId:row.commentId,
            petName:row.petName

    }).then(res=>{
        return res;
    })
}

export function findAll(){
    return http.get("/petIssue/findAll").then(res=>{
        return res
    })
}