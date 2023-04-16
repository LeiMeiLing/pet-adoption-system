import http from "../../../../config/http.js";
export function list(transferInfo){
    return http.get("/transferInfo/findName?transferName="+transferInfo.username, {
    }).then(res=>{
        return res;
    })
}
export function update(transferInfo){
    return http.put("/transferInfo/update",{
        ...transferInfo
    })

}

