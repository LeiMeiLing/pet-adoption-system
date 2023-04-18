package cn.jasonone.service;

import cn.jasonone.bean.PetIssue;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public interface PetIssueService {
    void setSqlSession(SqlSession session);
    void add(PetIssue petIssue);

    PageInfo<PetIssue> findAll(int pageNum , int pageSize);

    /**
     * 删除宠物秀
     */
    void del(Long id);

    /**
     * 用名字找宠物秀
     * @param petIssue
     * @return
     */
    PageInfo<PetIssue> findByName(PetIssue petIssue,int pageNum , int pageSize);

    PageInfo<PetIssue> findMy(Long id,int pageNum , int pageSize);
}
