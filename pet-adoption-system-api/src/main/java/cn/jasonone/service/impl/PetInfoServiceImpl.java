package cn.jasonone.service.impl;

import cn.jasonone.bean.PetInfo;
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
    public void deletePet(Long id) {
        PetInfoMapper petInfoMapper = sqlSession.getMapper(PetInfoMapper.class);
        petInfoMapper.deleteByPrimaryKey(id);
    }



    @Override
    public List<PetInfo> selectNameOrType( PetInfo petInfo) {
        PetInfoMapper petInfoMapper = sqlSession.getMapper(PetInfoMapper.class);

        return petInfoMapper.findSome(petInfo);
    }

    @Override
    public List<PetInfo> findAllPet() {
        PetInfoMapper petInfoMapper = sqlSession.getMapper(PetInfoMapper.class);
        return petInfoMapper.findAllPet();
    }

}

