package cn.jasonone.mapper;

import cn.jasonone.bean.PetKnowledge;

import java.util.List;

/**
* @author DELL
* @description 针对表【pet_knowledge(宠物知识表)】的数据库操作Mapper
* @createDate 2023-04-13 10:08:44
* @Entity cn.jasonone.bean.PetKnowledge
*/
public interface PetKnowledgeMapper {

    int deleteByPrimaryKey(Long id);

    int insert(PetKnowledge record);

    int insertSelective(PetKnowledge record);

    PetKnowledge selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PetKnowledge record);

    int updateByPrimaryKey(PetKnowledge record);

    List<PetKnowledge> findAll();

    List<PetKnowledge> selectByType(String essayType);

    List<PetKnowledge> selectByTime();
}
