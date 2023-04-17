package cn.jasonone.service;

import cn.jasonone.bean.Comment;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * 评论
 */
public interface CommentService {
    void setSqlSession(SqlSession session);
    /**
     * 添加评论
     */
    void addComment(Comment comment);

    /**
     * 获得宠物秀的评论
     */
    PageInfo<Comment> findAll(Long id,int pageNum , int pageSize);
    /**
     * 删除评论
     */
    void deleteComment(Long id);

    /**
     * 通过用户名或评论信息查评论
     */
    PageInfo<Comment> findByUsernameOrComment(Comment comment,int pageNum , int pageSize);

}
