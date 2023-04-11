package cn.jasonone.service;

import cn.jasonone.bean.ManagerInfo;
import org.apache.ibatis.session.SqlSession;

public interface ManagerService {
    void register(ManagerInfo managerInfo);

    ManagerInfo login(ManagerInfo managerInfo);
}
