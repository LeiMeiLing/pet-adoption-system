import http from "../../../../config/http.js";

/*
    更新用户信息
 */
export function updateGoods(row) {
    return http.put("/petstore/update",{
        "id":row.id,
        "goodsname":row.goodsname,
        "goodsType":row.goodsType,
        "goodsPrice":row.goodsPrice,
        "goodsStatus":row.goodsStatus,
        "goodsDesc":row.goodsDesc,
        "goodsPicture":row.goodsPicture

    }).then(res=>{
        return res;
    })
}


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
export function findSome(goodsInfo){
    return http.get("/petstore/findSome?goodsname="+goodsInfo.goodsname+"&goodsType="+goodsInfo.goodsType,{
    }).then(res=>{
        return res;
    })
}
/*
删除用户方法
 */
export function deleteGoodsInfo(row){
    return http.delete("/petstore", {
        data:{
            id:row.id
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
