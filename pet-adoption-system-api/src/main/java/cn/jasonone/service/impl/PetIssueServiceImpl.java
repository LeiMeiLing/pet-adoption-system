package cn.jasonone.service.impl;

import cn.jasonone.bean.PetIssue;
import cn.jasonone.mapper.PetIssueMapper;
import cn.jasonone.service.PetIssueService;
import lombok.Setter;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class PetIssueServiceImpl implements PetIssueService {
    @Setter
    private SqlSession sqlSession;

    @Override
    public void add(PetIssue petIssue) {
        PetIssueMapper petIssueMapper = sqlSession.getMapper(PetIssueMapper.class);
        petIssueMapper.insert(petIssue);
    }

    @Override
    public List<PetIssue> findAll() {
        PetIssueMapper petIssueMapper = sqlSession.getMapper(PetIssueMapper.class);
        return petIssueMapper.findAll();
    }

    @Override
    public void del(Long id) {
        PetIssueMapper petIssueMapper = sqlSession.getMapper(PetIssueMapper.class);
        petIssueMapper.deleteByPrimaryKey(id);
    }
}
