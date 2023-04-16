import http from "../../../config/http";
export function insert(transferInfo){
    return http.put("/transferInfo/add", {
        ...transferInfo
    /*   title:transferInfo.title,
        petname:transferInfo.petname,
        transfername:transferInfo.transfername,
        picture:transferInfo.picture,
        variety:transferInfo.variety,
        petSex:transferInfo.petSex,
        city:transferInfo.city,
        transferCause:transferInfo.transferCause*/
    }).then(res=>{
        return res;
    })
}

export function select(){
    return http.get("/transferInfo/selectStatus", {
    }).then(res=>{
        return res;
    })
}