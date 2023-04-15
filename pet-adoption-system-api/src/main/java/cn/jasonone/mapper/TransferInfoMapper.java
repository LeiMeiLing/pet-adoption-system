package cn.jasonone.mapper;

import cn.jasonone.bean.TransferInfo;

import java.util.List;

/**
* @author DELL
* @description 针对表【transfer_info(转让信息表)】的数据库操作Mapper
* @createDate 2023-04-13 11:51:18
* @Entity cn.jasonone.bean.TransferInfo
*/
public interface TransferInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TransferInfo record);

    int insertSelective(TransferInfo record);
    List<TransferInfo> findAll();
    List<TransferInfo> selectStatus();

    TransferInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TransferInfo record);

    int updateByPrimaryKey(TransferInfo record);

}
