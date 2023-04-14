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
        return res;
    })
}

export function insert(petAddInfo){
    return http.put("/pet/add",{
        petName:petAddInfo.petName,
        petSex:petAddInfo.petSex,
        variety:petAddInfo.variety,
        petPicture:petAddInfo.petPicture,
        description:petAddInfo.description
    }).then(res=>{
        return res;
    })
}