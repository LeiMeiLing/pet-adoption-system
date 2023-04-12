import axios, {get} from "axios";

import http from "../../../../config/http.js";
import useLogin from "../../../../stores/LoginStore.js";


// export function userFindAll(username,email,phone,createTime,updateTime) {
//     return axios.get('localhost:8000/user').then(res => {
//             return res[0].username
//         }
//     )
// }
// export const userFindAll = (username,email,phone,createTime,updateTime)=>{
//     return axios.get("http://localhost:8000/user?username="+username+"&email="+email+"&phone="+phone+"&createTime="+createTime+"&updateTime="+updateTime+"")
// }
// export const list = () =>{
//     return http.get('/user')
// }