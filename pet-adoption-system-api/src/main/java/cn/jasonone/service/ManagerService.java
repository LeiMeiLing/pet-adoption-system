package cn.jasonone.service;

import cn.jasonone.bean.GoodsInfo;
import cn.jasonone.bean.ManagerInfo;
import cn.jasonone.bean.UserInfo;
import org.apache.ibatis.session.SqlSession;

public interface ManagerService {
    void setSqlSession(SqlSession session);
    void register(ManagerInfo managerInfo);

    ManagerInfo login(ManagerInfo managerInfo);
}
