package cn.jasonone.service.impl;

import cn.jasonone.bean.GoodsInfo;
import cn.jasonone.bean.PetInfo;
import cn.jasonone.mapper.GoodsInfoMapper;
import cn.jasonone.mapper.PetInfoMapper;
import cn.jasonone.service.PetInfoService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.Setter;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class PetInfoServiceImpl implements PetInfoService {
    @Setter
    private SqlSession sqlSession;

    //根据id查找
    @Override
    public PetInfo selectById(Integer id) {
        PetInfoMapper petInfoMapper = sqlSession.getMapper(PetInfoMapper.class);
        PetInfo petInfo = petInfoMapper.selectByPrimaryKey(id);
        return petInfo;
    }

    @Override
    public void add(PetInfo petInfo) {
        petInfo.setPetStatus("未领养");
        PetInfoMapper petInfoMapper = sqlSession.getMapper(PetInfoMapper.class);
        petInfoMapper.insertSelective(petInfo);
    }

    @Override
    public void update(PetInfo petInfo) {
        PetInfoMapper petInfoMapper = sqlSession.getMapper(PetInfoMapper.class);
        petInfoMapper.updateByPrimaryKey(petInfo);
    }

    @Override
    public PageInfo<PetInfo> selectNameOrType(int pageNum, int pageSize, PetInfo petInfo) {
        PetInfoMapper petInfoMapper = sqlSession.getMapper(PetInfoMapper.class);
        PageHelper.startPage(pageNum, pageSize);
        List<PetInfo> some = petInfoMapper.findSome(petInfo);
        return new PageInfo<>(some);
    }

    @Override
    public PageInfo<PetInfo> findAllPet(int PageNum, int PageSize) {
        PetInfoMapper PetInfo = sqlSession.getMapper(PetInfoMapper.class);
        PageHelper.startPage(PageNum, PageSize);
        List<PetInfo> pets = PetInfo.findAllPet();
        return new PageInfo<>(pets);

    }
}
