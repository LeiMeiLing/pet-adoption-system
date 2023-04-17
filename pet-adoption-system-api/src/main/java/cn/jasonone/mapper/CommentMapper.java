package cn.jasonone.mapper;

import cn.jasonone.bean.Comment;

import java.util.List;

/**
* @author DELL
* @description 针对表【comment(评论表)】的数据库操作Mapper
* @createDate 2023-04-10 15:37:27
* @Entity cn.jasonone.bean.Comment
*/
public interface CommentMapper {
    /**
     * 添加评论
     * @param record
     * @return
     */
    int insert(Comment record);

    /**
     * 通过宠物秀id查找评论
     * @param issueId
     * @return
     */
    List<Comment> selectAllByIssueIdCommentList(Long issueId);

    /**
     * 通过id删除评论
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);


    /**
     * 模糊查询用户名，评论
     * @param record
     * @return
     */
    List<Comment> findAllByCommentNameAndContent(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

}
