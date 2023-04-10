package cn.jasonone.mapper;

import cn.jasonone.bean.PetInfo;

/**
* @author DELL
* @description 针对表【pet_info(宠物信息表)】的数据库操作Mapper
* @createDate 2023-04-10 15:37:54
* @Entity cn.jasonone.bean.PetInfo
*/
public interface PetInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(PetInfo record);

    int insertSelective(PetInfo record);

    PetInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PetInfo record);

    int updateByPrimaryKey(PetInfo record);

}
