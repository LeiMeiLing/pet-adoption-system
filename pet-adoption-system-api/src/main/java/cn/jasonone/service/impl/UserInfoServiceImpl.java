package cn.jasonone.service.impl;

import cn.hutool.crypto.digest.MD5;
import cn.jasonone.bean.GoodsInfo;
import cn.jasonone.bean.UserInfo;
import cn.jasonone.mapper.GoodsInfoMapper;
import cn.jasonone.mapper.ManagerInfoMapper;
import cn.jasonone.mapper.UserInfoMapper;
import cn.jasonone.service.UserInfoService;
import cn.hutool.core.util.RandomUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.Setter;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserInfoServiceImpl implements UserInfoService {
    @Setter
    private SqlSession sqlSession;
    @Override
    public void register(UserInfo userInfo) {
        // 生成盐
        String salt = RandomUtil.randomString(6);
        userInfo.setSalt(salt);
        userInfo.setPassword(salt+userInfo.getPassword()+salt);
        UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
        userInfoMapper.insertSelective(userInfo);
    }

    @Override
    public UserInfo login(UserInfo userInfo) {
        UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
        UserInfo user = userInfoMapper.findByUsername(userInfo.getUsername());
        if(user != null){
            // 获得盐
            String salt = user.getSalt();
            // 加盐加密
            String password = salt+userInfo.getPassword()+salt;
            password = MD5.create().digestHex(password);
            // 验证密码
            if(password.equals(user.getPassword())){
                user.setPassword(null);
                user.setSalt(null);
                return user;
            }
        }
        return null;
    }



    @Override
    public UserInfo find(Long id) {
        UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
        return userInfoMapper.selectByPrimaryKey(id);
    }


    @Override
    public String findName(Long id) {
        UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
        return userInfoMapper.selectByIdUsername(id);
    }

    @Override
    public boolean findName(String name) {
        UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
        System.out.println(userInfoMapper.findByUsername(name));
        return userInfoMapper.findByUsername(name)==null;

    }

    /*
    查找所有用户信息
     */
    @Override
    public PageInfo<UserInfo> userFindAll(int pageNum, int pageSize) {
        UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
        PageHelper.startPage(pageNum, pageSize);
        List<UserInfo> userInfos = userInfoMapper.userFindAll();
        return new PageInfo<>(userInfos);
    }
    /*
    在管理员界面根据用户id删除用户信息
     */
    @Override
    public void delete(Long id) {
        UserInfoMapper userInfoMapper =sqlSession.getMapper(UserInfoMapper.class);
        userInfoMapper.deleteByPrimaryKey(id);
    }

    /*
    在管理员界面修改用户信息
     */
    @Override
    public void update(UserInfo userInfo) {
        UserInfoMapper userInfoMapper =sqlSession.getMapper(UserInfoMapper.class);
        userInfoMapper.updateByPrimaryKeySelective(userInfo);
    }

    @Override
    public PageInfo<UserInfo> selectNameOrType(int pageNum, int pageSize, UserInfo userInfo) {
        UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
        PageHelper.startPage(pageNum, pageSize);
        List<UserInfo> userInfos = userInfoMapper.fuzzyQueries(userInfo);
        return new PageInfo<>(userInfos);
    }
}
