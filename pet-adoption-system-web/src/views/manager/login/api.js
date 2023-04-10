import useLogin from '../../../stores/LoginStore'
import http from "../../../config/http.js";
export function login(managerInfo){
    return http.post("/manager/login",{
        ...managerInfo
    })
        .then(res=>{
            if(res.code === 200){
                const loginInfo = useLogin();
                // 保存token
                loginInfo.setToken(res.token);
                // 保存用户信息
                loginInfo.setUserInfo(res.managerInfo);
            }
            return res;
        })
}
