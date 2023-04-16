package cn.jasonone.bean;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 转让申请信息表
 * @TableName transfer_application
 */
@Data
public class TransferApplication implements Serializable {
    /**
     * 转让id
     */
    private Integer id;

    /**
     * 领养用户名
     */
    private String title;

    /**
     * 宠物昵称
     */
    private String petname;

    /**
     * 转让人用户名
     */
    private String transferName;
    private String username;

    /**
     * 申请说明
     */
    private String transferCause;

    /**
     * 状态
     */
    private String petStatus;

    /**
     * 发布时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}