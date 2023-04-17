package cn.jasonone.service;

import cn.jasonone.bean.GoodsInfo;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public interface GoodsInfoService {
    void setSqlSession(SqlSession session);
    //查询所有并分页
    PageInfo<GoodsInfo> findAll(int pageNum, int pageSize);
    //添加商品
    void add(GoodsInfo goods);
    //删除商品
    void delete(Long id);
    //修改商品
    void update(GoodsInfo goods);
    //根据商品类型和名字模糊查询
    List<GoodsInfo> selectNameOrType(GoodsInfo goods);

    GoodsInfo selectById(Integer id);
}
