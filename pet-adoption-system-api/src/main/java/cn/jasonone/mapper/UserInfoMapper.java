package cn.jasonone.mapper;

import cn.jasonone.bean.GoodsInfo;
import cn.jasonone.bean.UserInfo;

import java.util.List;

/**
* @author DELL
* @description 针对表【user_info(用户信息表)】的数据库操作Mapper
* @createDate 2023-04-10 15:12:55
* @Entity cn.jasonone.bean.UserInfo
*/
public interface UserInfoMapper {
    /*
        根据用户id删除用户信息
        */
    int deleteByPrimaryKey(Long id);

    /**
     * 通过id找到名字
     * @param id
     * @return
     */
    String selectByIdUsername(Long id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Long id);


    /*
    根据用户id修改用户信息
     */
    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
    UserInfo findByUsername(String username);

    /*
    查找所有用户信息
     */
    List<UserInfo> userFindAll();
    /**
     * 模糊查询
     * @param goods
     * @return
     */
    List<UserInfo> fuzzyQueries(UserInfo goods);


}
