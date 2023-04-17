import http from "../../../../config/http";
import axios from "axios";

export function findAll(){
    return http.get("/petIssue/findAll", {
    }).then(res=>{
        return res;
    })
}

export function findSome(goodsName , goodsType){
    return http.get("/petstore/findSome?goodsname="+goodsName+"&goodsType="+goodsType, {

    }).then(res=>{
        return res;
    })
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