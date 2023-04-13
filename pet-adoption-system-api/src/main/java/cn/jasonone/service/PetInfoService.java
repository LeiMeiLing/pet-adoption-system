package cn.jasonone.service;

import cn.jasonone.bean.PetInfo;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;

/**
 * 宠物信息
 */
public interface PetInfoService {
    void setSqlSession(SqlSession session);
    void add(PetInfo petInfo);
    void update(PetInfo petInfo);
    void deletePet(Long id);

    PageInfo<PetInfo> selectNameOrType(int pageNum, int pageSize, PetInfo petInfo);

    PageInfo<PetInfo> findAllPet(int PageNum, int PageSize);
}

