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

    @Override
    public void add(PetInfo petInfo) {
        PetInfoMapper petInfoMapper = sqlSession.getMapper(PetInfoMapper.class);
        petInfoMapper.insert(petInfo);
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

