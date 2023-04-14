package cn.jasonone.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import lombok.Data;

/**
 * 宠物秀发布动态表
 * @TableName pet_issue
 */
@Data
public class PetIssue implements Serializable {
    /**
     * 发布者id
     */
    private Integer id;

    /**
     * 评论者id
     */
    private String commentId;

    /**
     * 发布内容
     */
    private String content;

    /**
     * 发布时间
     */
    private Date createTime;

    /**
     * 发布内容
     */
    private String petName;

    /**
     * 图片
     */
    private String picture;

    private static final long serialVersionUID = 1L;




}