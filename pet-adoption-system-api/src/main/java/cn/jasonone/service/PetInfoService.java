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

    PetInfo selectById(Integer id);
    void setSqlSession(SqlSession session);
    void add(PetInfo petInfo);
    void update(PetInfo petInfo);
    PageInfo<PetInfo> selectNameOrType(int pageNum, int pageSize, PetInfo petInfo);

    PageInfo<PetInfo> findAllPet(int PageNum, int PageSize);
}

