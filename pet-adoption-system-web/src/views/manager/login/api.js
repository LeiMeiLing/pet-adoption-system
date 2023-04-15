import useLogin from '../../../stores/LoginStore'
import http from "../../../config/http.js";
import LoginStore from "../../../stores/LoginStore";
export function login(managerInfo){
    return http.post("/manager/login",{
        ...managerInfo
    })
        .then(res=>{
            if(res.code === 200){
                const loginInfo = useLogin();
                // 保存用户信息
                loginInfo.setManagerInfo(res.data);
            }
            return res;
        })
}
