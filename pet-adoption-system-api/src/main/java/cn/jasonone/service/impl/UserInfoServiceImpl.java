package cn.jasonone.service.impl;

import cn.hutool.crypto.digest.MD5;
import cn.jasonone.bean.UserInfo;
import cn.jasonone.mapper.UserInfoMapper;
import cn.jasonone.service.UserInfoService;
import cn.hutool.core.util.RandomUtil;
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
        sqlSession.getMapper(UserInfoMapper.class).insertSelective(userInfo);
    }

    @Override
    public UserInfo login(UserInfo userInfo) {
        UserInfoMapper mapper = sqlSession.getMapper(UserInfoMapper.class);
        UserInfo user = mapper.findByUsername(userInfo.getUsername());
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
    public List<UserInfo> userFindAll() {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserInfoMapper mapper = sqlSession.getMapper(UserInfoMapper.class);
        List<UserInfo> userInfos = mapper.userFindAll();
        return userInfos;
    }
}
