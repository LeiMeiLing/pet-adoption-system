package cn.jasonone.bean;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 评论表
 * @TableName comment
 */
@Data
public class Comment implements Serializable {
    /**
     * 评论id
     */
    private Integer id;

    /**
     * 宠物秀id
     */
    private Integer issueId;

    /**
     * 评论者id
     */
    private Integer commentId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 评论者id
     */
    private Integer userId;

    /**
     * 评论者名字
     */
    private String commentName;

    private static final long serialVersionUID = 1L;
}