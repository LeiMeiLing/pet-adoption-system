package cn.jasonone.service.impl;

import cn.jasonone.bean.AdoptInfo;
import cn.jasonone.mapper.AdoptInfoMapper;
import cn.jasonone.service.AdoptInfoService;
import lombok.Setter;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class AdoptInfoServiceImpl implements AdoptInfoService {
    @Override
    public void add(AdoptInfo adoptInfo) {
        adoptInfo.setPetStatus("待审核");
        AdoptInfoMapper adoptInfoMapper=sqlSession.getMapper(AdoptInfoMapper.class);
        adoptInfoMapper.insertSelective(adoptInfo);
    }


    @Setter
    private SqlSession sqlSession;
    @Override
    public List<AdoptInfo> findAll() {
        List<AdoptInfo> list;
        AdoptInfoMapper adoptInfoMapper=sqlSession.getMapper(AdoptInfoMapper.class);
        list=adoptInfoMapper.findAll();
        return list;
    }

    @Override
    public AdoptInfo selectById(Integer id) {
        AdoptInfoMapper adoptInfoMapper=sqlSession.getMapper(AdoptInfoMapper.class);
        AdoptInfo application = adoptInfoMapper.selectByPrimaryKey(id);
        return application;
    }

    @Override
    public void updateStatus(AdoptInfo adoptInfo) {
        AdoptInfoMapper adoptInfoMapper=sqlSession.getMapper(AdoptInfoMapper.class);
        adoptInfoMapper.updateByPrimaryKeySelective(adoptInfo);
    }

    @Override
    public List<AdoptInfo> findName(String username) {
        AdoptInfoMapper adoptInfoMapper=sqlSession.getMapper(AdoptInfoMapper.class);
        return adoptInfoMapper.findName(username);
    }

    @Override
    public List<AdoptInfo> findSome(AdoptInfo adoptInfo) {
        AdoptInfoMapper adoptInfoMapper=sqlSession.getMapper(AdoptInfoMapper.class);
        return adoptInfoMapper.findSome(adoptInfo);
    }
}
