package cn.jasonone.bean;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 转让信息表
 * @TableName transfer_info
 */
@Data
public class TransferInfo implements Serializable {
    /**
     * 转让编号
     */
    private Integer id;

    /**
     * 转让标题
     */
    private String title;

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
     * 城市
     */
    private String city;

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