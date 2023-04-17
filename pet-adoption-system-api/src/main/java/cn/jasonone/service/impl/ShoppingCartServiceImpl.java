package cn.jasonone.service.impl;

import cn.jasonone.bean.GoodsInfo;
import cn.jasonone.bean.ShoppingCart;
import cn.jasonone.mapper.GoodsInfoMapper;
import cn.jasonone.mapper.ShoppingCartMapper;
import cn.jasonone.mapper.UserInfoMapper;
import cn.jasonone.service.ShoppingCartService;
import com.github.pagehelper.PageInfo;
import lombok.Setter;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Setter
    private SqlSession sqlSession;
    @Override
    public void join(ShoppingCart shoppingCart) {
        ShoppingCartMapper shoppingCartMapper = sqlSession.getMapper(ShoppingCartMapper.class);
        shoppingCartMapper.insert(shoppingCart);
    }

    @Override
    public PageInfo<ShoppingCart> selectMyCart(ShoppingCart shoppingCart) {
        ShoppingCartMapper shoppingCartMapper = sqlSession.getMapper(ShoppingCartMapper.class);
        List<ShoppingCart> shoppingCarts = shoppingCartMapper.selectByUserId(shoppingCart);
        return new PageInfo<>(shoppingCarts);
    }
}
