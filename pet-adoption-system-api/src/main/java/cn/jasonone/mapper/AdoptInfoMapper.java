package cn.jasonone.mapper;

import cn.jasonone.bean.AdoptInfo;
import cn.jasonone.bean.TransferApplication;

import java.util.List;

/**
* @author DELL
* @description 针对表【adopt_info(领养信息表)】的数据库操作Mapper
* @createDate 2023-04-13 09:12:03
* @Entity cn.jasonone.bean.AdoptInfo
*/
public interface AdoptInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(AdoptInfo record);

    int insertSelective(AdoptInfo record);

    AdoptInfo selectByPrimaryKey(Integer id);
    List<AdoptInfo> findAll();

    int updateByPrimaryKeySelective(AdoptInfo record);

    int updateByPrimaryKey(AdoptInfo record);

    List<AdoptInfo> findName(String username);

}
