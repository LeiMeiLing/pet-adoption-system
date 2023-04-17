package cn.jasonone.mapper;

import cn.jasonone.bean.GoodsInfo;
import cn.jasonone.bean.ShoppingCart;

import java.util.List;

/**
* @author Jfang
* @description 针对表【shopping_cart(购物车信息表)】的数据库操作Mapper
* @createDate 2023-04-17 09:29:24
* @Entity cn.jasonone.bean.ShoppingCart
*/
public interface ShoppingCartMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ShoppingCart record);

    int insertSelective(ShoppingCart record);

    ShoppingCart selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShoppingCart record);

    int updateByPrimaryKey(ShoppingCart record);

    List<ShoppingCart> selectByUserId(ShoppingCart shoppingCart);

}
