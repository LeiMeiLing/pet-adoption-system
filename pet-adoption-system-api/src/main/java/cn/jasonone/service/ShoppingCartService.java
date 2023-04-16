package cn.jasonone.service;


import cn.jasonone.bean.ShoppingCart;
import org.apache.ibatis.session.SqlSession;

public interface ShoppingCartService {
    void join(ShoppingCart shoppingCart);
    void setSqlSession(SqlSession session);
}
