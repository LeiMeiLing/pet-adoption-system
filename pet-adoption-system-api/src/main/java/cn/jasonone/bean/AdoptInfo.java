package cn.jasonone.bean;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 领养信息表
 * @TableName adopt_info
 */
@Data
public class AdoptInfo implements Serializable {
    /**
     * 领养编号
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 宠物名
     */
    private String petname;

    /**
     * 种类
     */
    private String variety;

    /**
     * 性别
     */
    private String petSex;

    /**
     * 状态
     */
    private String petStatus;

    /**
     * 申请时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 申请说明
     */
    private String describe;

    private static final long serialVersionUID = 1L;
}