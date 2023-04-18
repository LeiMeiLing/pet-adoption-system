package cn.jasonone.bean;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 购物车信息表
 * @TableName shopping_cart
 */
@Data
public class ShoppingCart implements Serializable {
    /**
     * 购物车ID
     */
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 商品ID
     */
    private Integer productId;

    /**
     * 商品数量
     */
    private Integer quantity;

    /**
     * 总价
     */
    private Double price;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

    /**
     * 商品名
     */
    private String goodsName;

    /**
     * 商品图片
     */
    private String goodsPicture;
    /**
     * 商品单价
     */
    private Float unitPrice;

    private static final long serialVersionUID = 1L;
}