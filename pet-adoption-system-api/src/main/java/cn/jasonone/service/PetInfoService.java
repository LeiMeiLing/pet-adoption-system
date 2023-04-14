package cn.jasonone.service;

import cn.jasonone.bean.GoodsInfo;
import cn.jasonone.bean.PetInfo;
import cn.jasonone.mapper.GoodsInfoMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.Setter;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * 宠物信息
 */
public interface PetInfoService {
    void setSqlSession(SqlSession session);
    void add(PetInfo petInfo);
    void deletePet(Long id);
    void update(PetInfo petInfo);
    List<PetInfo> selectNameOrType(PetInfo petInfo);

    List<PetInfo> findAllPet();
}

