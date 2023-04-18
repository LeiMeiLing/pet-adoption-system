import http from "../../../../config/http";
export function list(page,limit){
    if (page != null || limit != null){
        return http.get("/pet/findAll?page="+page+"&limit="+limit, {
        }).then(res=>{
            return res;
        })
    }else {
        return http.get("/pet/findAll", {
        }).then(res=>{
            return res;
        })
    }

}
export function findSome(petInfo){
    return http.get("/pet/findSome?petName="+petInfo.petName+"&variety="+petInfo.variety+"&petSex="+petInfo.petSex+"&petStatus="+petInfo.petStatus)
       .then(res=>{
        return res;
    })
    }
export function petDele(id){
    return http.delete("/pet",{
        data:{
            petId:id

    }},).then(res=>{
        return res;
    })
}
export function add(petAdd) {
    return http.put("/pet/add", {
        ...petAdd
    }).then(res => {
        return res
    })
}
export function updatePet(row) {
    return http.put("/pet/update",{
        petId:row.petId,
        petName:row.petName,
        petStatus:row.petStatus,
        petPicture:row.petPicture

    }).then(res=>{
        return res;
    })
}