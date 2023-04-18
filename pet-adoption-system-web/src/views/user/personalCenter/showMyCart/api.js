import http from "../../../../config/http.js";

export function showMyCart(userId){
    return http.get("/shoppingCart/MyCart?userId="+userId)
}
export function deleRow(row){
    return http.delete('/shoppingCart/deleteGoods',{
        data:{
            id:row.id
        }
    }).then(res=>{
        return res
    })
}
export function findSome(id){

    return http.get('/shoppingCart/findSome?'+id).then(res=>{
        return  res
    })
}
export function dele(ids){
    return http.delete('/shoppingCart/delete?ids='+ids,{
    }).then(res=>{
        return res
    })
}

export function update(shoppingCart){
    return http.get('/shoppingCart/update?shoppingCart='+shoppingCart,{
    }).then(res=>{
        return res
    })
}
