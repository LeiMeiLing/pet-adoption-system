package cn.jasonone.service.impl;

import cn.jasonone.bean.GoodsInfo;
import cn.jasonone.mapper.GoodsInfoMapper;
import cn.jasonone.service.GoodsInfoService;
import cn.jasonone.util.MyBatisUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.Setter;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class GoodsInfoServiceImpl implements GoodsInfoService {


    @Override
    public PageInfo<GoodsInfo> findAll(int pageNum, int pageSize) {
        GoodsInfoMapper goodsInfoMapper = MyBatisUtil.getSession().getMapper(GoodsInfoMapper.class);
        PageHelper.startPage(pageNum, pageSize);
        List<GoodsInfo> goods = goodsInfoMapper.selectAll();
        return new PageInfo<>(goods);
    }

    @Override
    public void add(GoodsInfo goods) {
        GoodsInfoMapper goodsInfoMapper = MyBatisUtil.getSession().getMapper(GoodsInfoMapper.class);
        goodsInfoMapper.insert(goods);
    }

    @Override
    public void delete(Long id) {
        GoodsInfoMapper goodsInfoMapper = MyBatisUtil.getSession().getMapper(GoodsInfoMapper.class);
        goodsInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(GoodsInfo goods) {
        GoodsInfoMapper goodsInfoMapper = MyBatisUtil.getSession().getMapper(GoodsInfoMapper.class);
        goodsInfoMapper.updateByPrimaryKeySelective(goods);
    }


    @Override
    public PageInfo<GoodsInfo> selectNameOrType(int pageNum, int pageSize, GoodsInfo goods) {
        GoodsInfoMapper goodsInfoMapper = MyBatisUtil.getSession().getMapper(GoodsInfoMapper.class);
        PageHelper.startPage(pageNum, pageSize);
        List<GoodsInfo> goodsInfos = goodsInfoMapper.fuzzyQueries(goods);
        return new PageInfo<>(goodsInfos);
    }
}
