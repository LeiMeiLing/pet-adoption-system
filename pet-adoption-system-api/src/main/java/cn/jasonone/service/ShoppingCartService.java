package cn.jasonone.service;


import cn.jasonone.bean.GoodsInfo;
import cn.jasonone.bean.ShoppingCart;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public interface ShoppingCartService {
    void join(ShoppingCart shoppingCart);

    PageInfo<ShoppingCart> selectMyCart(ShoppingCart shoppingCart);
    void setSqlSession(SqlSession session);

    void deleteGoods(ShoppingCart shoppingCart);

    ShoppingCart findSome(String id);

    void delete(String s);


    void update(ShoppingCart shoppingCart);
}
