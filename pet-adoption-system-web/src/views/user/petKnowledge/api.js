import http from "../../../config/http.js"
export function insert(){
    return http.put("/petKnowledge/insert",{
    }).then(res=>{
        return res;
    })
}
export function selectByType(essayType){
    return http.get("/petKnowledge/selectByType",{
    }).then(res=>{
        return res;
    })
}

export function selectByTime(){
    return http.get("/petKnowledge/selectByTime",{
    }).then(res=>{
        return res;
    })
}
export function findAll(){
    return http.get("/petKnowledge/findAll",{
    }).then(res=>{
        return res;
    })
}


