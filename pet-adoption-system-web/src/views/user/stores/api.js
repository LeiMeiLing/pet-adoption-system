import http from "../../../config/http";
import axios from "axios";
import { layer } from "@layui/layer-vue"

export function findAll(){
    return http.get("/petstore/findAll", {
    }).then(res=>{
        return res;
    })
}

export function findSome(goodsName , goodsType){
    return http.get("/petstore/findSome?goodsname="+goodsName+"&goodsType="+goodsType, {

    }).then(res=>{
        return res;
    })
}
export function joinCart(cartInfo){
    return http.put("/shoppingCart/join", {
        ...cartInfo
    }).then(res=>{
        return res;
    })
}