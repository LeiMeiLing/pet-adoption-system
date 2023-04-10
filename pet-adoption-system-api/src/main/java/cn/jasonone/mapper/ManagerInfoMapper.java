package cn.jasonone.mapper;

import cn.jasonone.bean.ManagerInfo;
import cn.jasonone.bean.UserInfo;

/**
* @author DELL
* @description 针对表【manager_info(管理员信息表)】的数据库操作Mapper
* @createDate 2023-04-10 18:00:14
* @Entity cn.jasonone.bean.ManagerInfo
*/
public interface ManagerInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ManagerInfo record);

    int insertSelective(ManagerInfo record);

    ManagerInfo selectByPrimaryKey(Long id);
    ManagerInfo findByUsername(String username);

    int updateByPrimaryKeySelective(ManagerInfo record);

    int updateByPrimaryKey(ManagerInfo record);

}
