package cn.jasonone.service;

import cn.jasonone.bean.TransferApplication;
import cn.jasonone.bean.UserInfo;
import org.apache.ibatis.session.SqlSession;

import java.util.List;


public interface TransferApplicationService {
    /**
     * 添加转让领养申请
     * @param transferApplication
     */
    void add(TransferApplication transferApplication);

    void setSqlSession(SqlSession session);

    List<TransferApplication> findAll();

    TransferApplication selectById(Integer id);

    void updateStatus(TransferApplication application);
}
