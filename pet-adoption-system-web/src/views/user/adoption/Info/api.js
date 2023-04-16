import http from "../../../../config/http";
export function insert(adoptionInfo,userInfo){
    return http.put("/adoption/add", {
        variety: adoptionInfo.variety,
        petname: adoptionInfo.petname,
        petSex: adoptionInfo.petSex,
        username:userInfo.username,
        describe:adoptionInfo.describe,
        phone:adoptionInfo.phone
    }).then(res=>{
        return res;
    })
}