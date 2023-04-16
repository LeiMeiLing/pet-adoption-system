package cn.jasonone.service.impl;

import cn.jasonone.bean.ShoppingCart;
import cn.jasonone.mapper.ShoppingCartMapper;
import cn.jasonone.mapper.UserInfoMapper;
import cn.jasonone.service.ShoppingCartService;
import lombok.Setter;
import org.apache.ibatis.session.SqlSession;

public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Setter
    private SqlSession sqlSession;
    @Override
    public void join(ShoppingCart shoppingCart) {
        ShoppingCartMapper shoppingCartMapper = sqlSession.getMapper(ShoppingCartMapper.class);
        shoppingCartMapper.insert(shoppingCart);
    }
}
