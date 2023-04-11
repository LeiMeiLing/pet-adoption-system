package cn.jasonone.service;

import cn.jasonone.bean.GoodsInfo;
import com.github.pagehelper.PageInfo;

public interface GoodsInfoService {
    //查询所有并分页
    PageInfo<GoodsInfo> findAll(int pageNum, int pageSize);
    //添加商品
    void add(GoodsInfo goods);
    //删除商品
    void delete(Long id);

    void update(GoodsInfo goods);
}
