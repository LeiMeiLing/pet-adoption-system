package cn.jasonone.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.MD5;
import cn.jasonone.bean.ManagerInfo;
import cn.jasonone.bean.UserInfo;
import cn.jasonone.mapper.GoodsInfoMapper;
import cn.jasonone.mapper.ManagerInfoMapper;
import cn.jasonone.service.ManagerService;
import cn.jasonone.util.MyBatisUtil;
import lombok.Setter;
import org.apache.ibatis.session.SqlSession;

public class ManagerServiceImpl implements ManagerService {
    @Setter
    private SqlSession sqlSession;

    @Override
    public void register(ManagerInfo managerInfo) {
        ManagerInfoMapper managerInfoMapper =sqlSession.getMapper(ManagerInfoMapper.class);
        String salt = RandomUtil.randomString(6);
        managerInfo.setSalt(salt);
        System.out.println(managerInfo.getSalt());
        managerInfo.setPassword(salt+managerInfo.getPassword()+salt);
        System.out.println(managerInfo.getPassword());
        managerInfoMapper.insertSelective(managerInfo);
    }
    @Override
    public ManagerInfo login(ManagerInfo managerInfo) {
        ManagerInfoMapper managerInfoMapper = MyBatisUtil.getSession().getMapper(ManagerInfoMapper.class);
        ManagerInfo manager = managerInfoMapper.findByUsername(managerInfo.getUsername());
        if(manager != null){
            // 获得盐
            String salt = manager.getSalt();
            // 加盐加密
            String password = salt+managerInfo.getPassword()+salt;
            password = MD5.create().digestHex(password);
            // 验证密码
            if(password.equals(manager.getPassword())){
                manager.setPassword(null);
                manager.setSalt(null);
                return manager;
            }
        }
        return null;
    }

}
