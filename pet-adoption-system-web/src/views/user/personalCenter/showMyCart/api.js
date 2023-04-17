import http from "../../../../config/http.js";

export function showMyCart(userId){
    return http.get("/shoppingCart/MyCart?userId="+userId)
}