import http from "../../../../config/http.js";
export function list(){
    return http.get("/adoption/findAll", {

    }).then(res=>{
        return res;
    })
}

export function update(adoptionInfo,status,describe){
    return http.post("/adoption/update", {
        id:adoptionInfo.id,
        variety: adoptionInfo.variety,
        petname: adoptionInfo.petname,
        petSex: adoptionInfo.petSex,
        petStatus:status,
        describe:describe,
        username:adoptionInfo.username,
        phone:adoptionInfo.phone
    }).then(res=>{
        return res;
    })
}