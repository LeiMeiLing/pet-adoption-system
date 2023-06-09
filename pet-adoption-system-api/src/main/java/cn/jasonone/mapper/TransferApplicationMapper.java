package cn.jasonone.mapper;

import cn.jasonone.bean.TransferApplication;

import java.util.List;

/**
* @author DELL
* @description 针对表【transfer_application(转让申请信息表)】的数据库操作Mapper
* @createDate 2023-04-13 09:33:14
* @Entity cn.jasonone.bean.TransferApplication
*/
public interface TransferApplicationMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(TransferApplication application);

    List<TransferApplication> findAll();


    int insertSelective(TransferApplication record);

    TransferApplication selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TransferApplication record);

    int updateByPrimaryKey(TransferApplication record);

}
