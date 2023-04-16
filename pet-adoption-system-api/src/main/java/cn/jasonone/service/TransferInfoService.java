package cn.jasonone.service;

import cn.jasonone.bean.PetInfo;
import cn.jasonone.bean.TransferInfo;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * 装让服务接口
 */
public interface TransferInfoService {
    TransferInfo selectById(Integer id);
    void setSqlSession(SqlSession session);
    void add(TransferInfo transferInfo);
    void update(TransferInfo transferInfo);
    List<TransferInfo> findAll();
    List<TransferInfo> selectStatus();
}
