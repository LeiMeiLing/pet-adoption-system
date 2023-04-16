package cn.jasonone.service;

import cn.jasonone.bean.GoodsInfo;
import cn.jasonone.bean.UserInfo;
import com.github.pagehelper.PageInfo;
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

    void setSqlSession(SqlSession session);

    /**
     * 用户登录
     * @param userInfo
     * @return
     */
    UserInfo login(UserInfo userInfo);
    UserInfo find(Long id);
    /**
     * 找到名字
     */
    String findName(Long id);
    boolean findName(String name);
    /**
     * 获取用户列表显示在页面上
     * @return
     */
    PageInfo<UserInfo> userFindAll(int pageNum, int pageSize);

    /*
    管理员删除用户信息
     */
    void delete(Long id);

    //管理员修改用户信息
    void update(UserInfo userInfo);
    //管理员模糊查询用户信息
    PageInfo<UserInfo> selectNameOrType(int pageNum, int pageSize, UserInfo userInfo);
}
