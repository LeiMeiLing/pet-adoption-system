package cn.jasonone.service;

import cn.jasonone.bean.PetKnowledge;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public interface PetKnowledgeService {
    /**
     * 添加宠物知识
     * @param petKnowledge
     */
    void add(PetKnowledge petKnowledge);
    void setSqlSession(SqlSession session);

    /**
     * 查找所有宠物知识
     * @param
     */
    List<PetKnowledge> findAll();

    /**
     * 通过文章类型查找宠物知识
     * @param essayType
     * @return
     */
    List<PetKnowledge> selectByType(String essayType);

    /**
     * 查找最新的4条宠物知识
     * @param
     * @return
     */
    List<PetKnowledge> selectByTime();
}
