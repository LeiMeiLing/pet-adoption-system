package cn.jasonone.mapper;

import cn.jasonone.bean.GoodsInfo;

import java.util.List;

/**
* @author DELL
* @description 针对表【goods_info(商品信息表)】的数据库操作Mapper
* @createDate 2023-04-10 22:01:34
* @Entity cn.jasonone.bean.GoodsInfo
*/
public interface GoodsInfoMapper {
    /**
     * 查询所有商品
     * @return
     */
    List<GoodsInfo> selectAll();

    /**
     * 添加商品
     * @param record
     * @return
     */
    int insert(GoodsInfo record);

    /**
     * 通过id删除商品
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 通过id修改商品
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(GoodsInfo record);



    int insertSelective(GoodsInfo record);

    GoodsInfo selectByPrimaryKey(Long id);



    int updateByPrimaryKey(GoodsInfo record);

}
