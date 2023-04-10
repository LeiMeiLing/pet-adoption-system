package cn.jasonone.mapper;

import cn.jasonone.bean.GoodsInfo;

/**
* @author DELL
* @description 针对表【goods_info(商品信息表)】的数据库操作Mapper
* @createDate 2023-04-10 22:01:34
* @Entity cn.jasonone.bean.GoodsInfo
*/
public interface GoodsInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(GoodsInfo record);

    int insertSelective(GoodsInfo record);

    GoodsInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsInfo record);

    int updateByPrimaryKey(GoodsInfo record);

}
