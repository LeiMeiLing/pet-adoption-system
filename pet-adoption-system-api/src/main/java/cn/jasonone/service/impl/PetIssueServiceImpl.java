package cn.jasonone.service.impl;

import cn.jasonone.bean.PetIssue;
import cn.jasonone.bean.UserInfo;
import cn.jasonone.mapper.PetIssueMapper;
import cn.jasonone.service.PetIssueService;
import cn.jasonone.until.PageInfoUtils;
import com.github.pagehelper.PageInfo;
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
    public PageInfo<PetIssue> findAll(int pageNum , int pageSize) {
        PetIssueMapper petIssueMapper = sqlSession.getMapper(PetIssueMapper.class);
        return PageInfoUtils.list2PageInfo(petIssueMapper.findAll(),pageNum,pageSize);
    }

    @Override
    public void del(Long id) {
        PetIssueMapper petIssueMapper = sqlSession.getMapper(PetIssueMapper.class);
        petIssueMapper.deleteByPrimaryKey(id);
    }


    @Override
    public PageInfo<PetIssue> findByName(PetIssue petIssue,int pageNum , int pageSize) {
        PetIssueMapper petIssueMapper = sqlSession.getMapper(PetIssueMapper.class);
        return PageInfoUtils.list2PageInfo(petIssueMapper.findAllByPetName(petIssue),pageNum,pageSize);
    }

    @Override
    public PageInfo<PetIssue> findMy(Long id,int pageNum , int pageSize) {
        PetIssueMapper petIssueMapper = sqlSession.getMapper(PetIssueMapper.class);
        return PageInfoUtils.list2PageInfo(petIssueMapper.selectByPrimaryKey(id),pageNum,pageSize);
    }
}
