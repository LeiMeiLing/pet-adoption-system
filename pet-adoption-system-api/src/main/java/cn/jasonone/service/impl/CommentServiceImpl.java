package cn.jasonone.service.impl;

import cn.jasonone.bean.Comment;
import cn.jasonone.mapper.CommentMapper;
import cn.jasonone.service.CommentService;
import lombok.Setter;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CommentServiceImpl implements CommentService {
    @Setter
    private SqlSession sqlSession;


    @Override
    public void addComment(Comment comment) {
        CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
        commentMapper.insert(comment);
    }

    @Override
    public List<Comment> findAll(Long id) {
        CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
        return commentMapper.selectAllByIssueIdCommentList(id);

    }

    @Override
    public void deleteComment(Long id) {
        CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
        commentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Comment> findByUsernameOrComment(Comment comment) {
        CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
        return commentMapper.findAllByCommentNameAndContent(comment);
    }
}
