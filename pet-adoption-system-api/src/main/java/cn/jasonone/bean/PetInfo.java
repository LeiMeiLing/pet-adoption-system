package cn.jasonone.bean;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 宠物信息表
 * @TableName pet_info
 */
@Data
public class PetInfo implements Serializable {
    /**
     * 宠物编号
     */
    private Integer petId;

    /**
     * 宠物名
     */
    private String petName;

    /**
     * 种类
     */
    private String variety;

    /**
     * 性别
     */
    private String petSex;

    /**
     * 领养状态
     */
    private String petStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 图片
     */
    private byte[] petPicture;

    private static final long serialVersionUID = 1L;
}