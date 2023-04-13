import http from "../../../../config/http";
export function list(){
    return http.get("/pet/findAll", {

    }).then(res=>{
        return res;
    })
}

export function findSome(petName,petStatus){
    return http.get("/pet/findSome?petName="+petName+"&petStatus="+petStatus,{
    }).then(res=>{


    })
}
export function petDele(petInfo){
    return http.delete("/pet?petId=",{
        data:{
            "id":petInfo.petId
        }
    },).then(res=>{
        return res;
    })
}