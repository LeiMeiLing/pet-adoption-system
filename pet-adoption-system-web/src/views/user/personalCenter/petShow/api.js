import http from "../../../../config/http.js";

export function findMyPetShow(userId){
    return http.get("/petIssue/findMy?id="+userId).then(res=>{
        return res
    })
}