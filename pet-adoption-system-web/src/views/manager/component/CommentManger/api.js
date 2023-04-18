import http from "../../../../config/http";
import axios from "axios";


export function findAll(petName,page,limit){
    if(page != null||limit != null){
        return http.get("/petIssue/findByName?page="+page+"&limit="+limit+"&petName="+petName,{
        }).then(res=>{
            return res;
        })
    }else if (petName != null){
    return http.get("/petIssue/findByName?petName="+petName, {
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

export function delShow(id){
    return http.delete("/petIssue",{
        data:{
            id:id
        }
    })
}
