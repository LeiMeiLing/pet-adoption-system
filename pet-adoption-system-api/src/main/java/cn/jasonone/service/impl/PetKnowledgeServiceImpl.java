package cn.jasonone.service.impl;

import cn.jasonone.bean.PetKnowledge;
import cn.jasonone.mapper.PetKnowledgeMapper;
import cn.jasonone.service.PetKnowledgeService;
import lombok.Setter;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class PetKnowledgeServiceImpl implements PetKnowledgeService {
    @Setter
    private SqlSession sqlSession;
    /*
    添加宠物知识
     */
    @Override
    public void add(PetKnowledge petKnowledge) {
        PetKnowledgeMapper petKnowledgeMapper = sqlSession.getMapper(PetKnowledgeMapper.class);
        petKnowledgeMapper.insertSelective(petKnowledge);
    }

    /*
    查找所有宠物知识
     */
    @Override
    public List<PetKnowledge> findAll() {
        List<PetKnowledge> list;
        PetKnowledgeMapper petKnowledgeMapper = sqlSession.getMapper(PetKnowledgeMapper.class);
        list=petKnowledgeMapper.findAll();
        return list;
    }
    //通过文章类型查询
    @Override
    public List<PetKnowledge> selectByType(String essayType) {
        List<PetKnowledge> list;
        PetKnowledgeMapper petKnowledgeMapper = sqlSession.getMapper(PetKnowledgeMapper.class);
        list=petKnowledgeMapper.selectByType(essayType);
        return list;
    }
    //查找最新的4条宠物知识
    @Override
    public List<PetKnowledge> selectByTime() {
        List<PetKnowledge> list;
        PetKnowledgeMapper petKnowledgeMapper = sqlSession.getMapper(PetKnowledgeMapper.class);
        list=petKnowledgeMapper.selectByTime();
        return list;
    }
    //随机生成6条宠物知识
    @Override
    public List<PetKnowledge> createMessages() {
        List<PetKnowledge> list;
        PetKnowledgeMapper petKnowledgeMapper = sqlSession.getMapper(PetKnowledgeMapper.class);
        list=petKnowledgeMapper.createMessages();
        return list;
    }


}
