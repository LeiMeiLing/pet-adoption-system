package cn.jasonone.bean;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName comment
 */
@Data
public class Comment implements Serializable {
    /**
     * 评论id
     */
    private Integer id;

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

    private static final long serialVersionUID = 1L;
}