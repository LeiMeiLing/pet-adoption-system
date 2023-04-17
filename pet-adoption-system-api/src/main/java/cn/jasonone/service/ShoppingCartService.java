package cn.jasonone.service;


import cn.jasonone.bean.GoodsInfo;
import cn.jasonone.bean.ShoppingCart;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;

public interface ShoppingCartService {
    void join(ShoppingCart shoppingCart);

    PageInfo<ShoppingCart> selectMyCart(ShoppingCart shoppingCart);
    void setSqlSession(SqlSession session);
}
