package cn.jasonone.service;

import cn.jasonone.bean.AdoptInfo;
import cn.jasonone.bean.TransferApplication;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * 领养信息服务接口
 */
public interface AdoptInfoService {
    /**
     * 添加领养申请
     * @param
     */
    void add(AdoptInfo adoptInfo);

    void setSqlSession(SqlSession session);

    List<AdoptInfo> findAll();

   AdoptInfo selectById(Integer id);

    void updateStatus(AdoptInfo adoptInfo);

    List<AdoptInfo> findName(String username);
}
