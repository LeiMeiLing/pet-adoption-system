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

    /**
     * 删除宠物秀
     * @param id
     * @return
     */
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

    /**
     * 通过宠物名找宠物秀
     * @param record
     * @return
     */
    List<PetIssue> findAllByPetName(PetIssue record);

    int insertSelective(PetIssue record);

    /**
     * 通过发布者id找宠物秀
     * @param id
     * @return
     */
    List<PetIssue> selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PetIssue record);

    int updateByPrimaryKey(PetIssue record);

}
