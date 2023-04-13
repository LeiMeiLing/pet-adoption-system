package cn.jasonone.bean;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 宠物知识表
 * @TableName pet_knowledge
 */
@Data
public class PetKnowledge implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章作者
     */
    private String author;

    /**
     * 文章简介
     */
    private String description;

    /**
     * 内容
     */
    private String content;

    /**
     * 发布时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}