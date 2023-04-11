package cn.jasonone.service;

import cn.jasonone.bean.UserInfo;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * 用户信息服务接口
 * @author xujie
 */
public interface UserInfoService {
    /**
     * 用户注册
     * @param userInfo  用户信息
     */
    void register(UserInfo userInfo);


    /**
     * 用户登录
     * @param userInfo
     * @return
     */
    UserInfo login(UserInfo userInfo);

    /**
     * 获取用户列表显示在页面上
     * @return
     */
    List<UserInfo> userFindAll();
}
