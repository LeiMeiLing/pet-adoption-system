package cn.jasonone.mapper;

import cn.jasonone.bean.PetIssue;

import java.util.List;

/**
* @author miss
* @description 针对表【pet_issue(宠物秀发布动态表)】的数据库操作Mapper
* @createDate 2023-04-14 11:24:06
* @Entity cn.jasonone.bean.PetIssue
*/
public interface PetIssueMapper {

    int deleteByPrimaryKey(Long id);

    /**
     * 添加宠物秀
     * @param record
     * @return
     */
    int insert(PetIssue record);

    /**
     * 查询所有
     */
    List<PetIssue> findAll();

    int insertSelective(PetIssue record);

    PetIssue selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PetIssue record);

    int updateByPrimaryKey(PetIssue record);

}
