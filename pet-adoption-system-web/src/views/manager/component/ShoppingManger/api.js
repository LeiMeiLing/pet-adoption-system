import http from "../../../../config/http.js";

/*
    更新用户信息
 */
// export function updateUser(row) {
//     return http.put("/user/update",{
//         "id":row.id,
//         "username":row.username,
//         "email":row.email,
//         "phone":row.phone
//
//     }).then(res=>{
//         return res;
//     })
// }


/*
    分页
    */
export function page(page,limit){
    return http.get("/petstore/findAll?page="+page+"&limit="+limit,{
    }).then(res=>{
        return res;
    })
}

/*
查询所有
 */
export function list(){
    return http.get("/petstore/findAll",{
    }).then(res=>{
        return res;
    })
}
/*
模糊查询
 */
export function findSome(username,email,phone){
    return http.get("/user/findSome?userName="+username+"&email="+email+"&phone="+phone,{
    }).then(res=>{
        return res;
    })
}
/*
删除用户方法
 */
export function deleteUserInfo(row){
    return http.delete("/user", {
        data:{
            "id":row.id
        }
    },).then(res=>{
        return res;
    })
}

export function add(petStoreAdd){
    return http.put("/petstore/add",{
        ...petStoreAdd
    }).then(res=>{
        return res
})
}
