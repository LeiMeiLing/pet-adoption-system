package cn.jasonone.service.impl;

import cn.jasonone.bean.TransferInfo;
import cn.jasonone.mapper.TransferInfoMapper;
import cn.jasonone.service.TransferInfoService;
import lombok.Setter;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class TransferInfoServiceImpl implements TransferInfoService {
    @Override
    public TransferInfo selectById(Integer id) {
        TransferInfoMapper mapper = sqlSession.getMapper(TransferInfoMapper.class);
        TransferInfo transferInfo = mapper.selectByPrimaryKey(id);
        return transferInfo;
    }

    @Setter
    private SqlSession sqlSession;

    @Override
    public void add(TransferInfo transferInfo) {
        transferInfo.setPetStatus("未领养");
        TransferInfoMapper mapper = sqlSession.getMapper(TransferInfoMapper.class);
        mapper.insertSelective(transferInfo);
    }

    @Override
    public void update(TransferInfo transferInfo) {
        TransferInfoMapper mapper = sqlSession.getMapper(TransferInfoMapper.class);
        mapper.updateByPrimaryKey(transferInfo);
    }

    @Override
    public List<TransferInfo> findAll() {
        TransferInfoMapper mapper = sqlSession.getMapper(TransferInfoMapper.class);
        List<TransferInfo> all = mapper.findAll();
        return all;
    }

    @Override
    public List<TransferInfo> selectStatus() {
        TransferInfoMapper mapper = sqlSession.getMapper(TransferInfoMapper.class);
        List<TransferInfo> transferInfos = mapper.selectStatus();
        return transferInfos;
    }

    @Override
    public List<TransferInfo> findAll(String transferName) {
        TransferInfoMapper mapper = sqlSession.getMapper(TransferInfoMapper.class);
        return mapper.findName(transferName);
    }
}
