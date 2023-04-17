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
    /**
     * 删除评论
     */
    void deleteComment(Long id);

    /**
     * 通过用户名或评论信息查评论
     */
    List<Comment> findByUsernameOrComment(Comment comment);

}
