import http from "../../../../config/http.js";

/*
    更新用户信息
 */
export function updateUser(row) {
    return http.put("/user/update",{
            "id":row.id,
            "username":row.username,
            "email":row.email,
            "phone":row.phone
    }).then(res=>{
        return res;
    })
}

    /*
    查询所有
     */
export function list(){
    return http.get("/user/findAll",{
    }).then(res=>{
        return res;
    })
}

export function page(page,limit){
    return http.get("/user/findAll?page="+page+"&limit="+limit,{
    }).then(res=>{
        return res;
    })
}
    /*
    模糊查询
     */
export function findSome(user){
    return http.get("/user/findSome?username="+user.username+"&email="+user.email+"&phone="+user.phone,{
    }).then(res=>{
        return res;
    })
}
/*
删除用户方法
 */
export function deleteUserInfo(id){
    return http.delete("/user", {
        data:{
            "id":id
        }
    },).then(res=>{
        return res;
    })
}
