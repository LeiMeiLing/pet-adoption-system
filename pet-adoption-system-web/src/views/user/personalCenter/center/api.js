import http from "../../../../config/http.js";

export function findMyPetShow(userId,page,limit){

    if (page !=null || limit!= null){
        return http.get("/petIssue/findMy?id="+userId+"&page="+page+"&limit="+limit).then(res=>{
            return res
        })
    }else {
        return http.get("/petIssue/findMy?id="+userId).then(res=>{
            return res
        })
    }

}