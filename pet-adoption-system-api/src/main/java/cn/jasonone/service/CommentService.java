package cn.jasonone.service;

import cn.jasonone.bean.Comment;
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
    List<Comment> findAll(Long id);


}
