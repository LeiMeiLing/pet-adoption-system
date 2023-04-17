package cn.jasonone.service.impl;

import cn.jasonone.bean.Comment;
import cn.jasonone.mapper.CommentMapper;
import cn.jasonone.service.CommentService;
import cn.jasonone.until.PageInfoUtils;
import com.github.pagehelper.PageInfo;
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
    public PageInfo<Comment> findAll(Long id,int pageNum , int pageSize) {
        CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
        return PageInfoUtils.list2PageInfo(commentMapper.selectAllByIssueIdCommentList(id),pageNum,pageSize);

    }

    @Override
    public void deleteComment(Long id) {
        CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
        commentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo<Comment> findByUsernameOrComment(Comment comment,int pageNum , int pageSize) {
        CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
        return PageInfoUtils.list2PageInfo(commentMapper.findAllByCommentNameAndContent(comment),pageNum,pageSize);
    }
}
