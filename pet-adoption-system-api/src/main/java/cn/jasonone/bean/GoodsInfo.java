package cn.jasonone.bean;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 商品信息表
 * @TableName goods_info
 */
@Data
public class GoodsInfo implements Serializable {
    /**
     * 商品id
     */
    private Integer id;

    /**
     * 商品类型
     */
    private String goodsType;

    /**
     * 商品名
     */
    private String goodsname;

    /**
     * 商品图片
     */
    private String goodsPicture;

    /**
     * 价格
     */
    private Long goodsPrice;

    /**
     * 商品描述
     */
    private String goodsDesc;

    /**
     * 状态：-1 已删除 0 下架 1 上架
     */
    private Integer goodsStatus;

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