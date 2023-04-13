package cn.jasonone.service.impl;

import cn.jasonone.bean.TransferApplication;
import cn.jasonone.mapper.TransferApplicationMapper;
import cn.jasonone.service.TransferApplicationService;
import lombok.Setter;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class TransferApplicationServiceImpl implements TransferApplicationService {
    @Setter
    private SqlSession sqlSession;
    /**
     *
     * 添加申请订单
     * */
    @Override
    public void add(TransferApplication transferApplication) {
         transferApplication.setPetStatus("待审核");
         TransferApplicationMapper applicationMapper=sqlSession.getMapper(TransferApplicationMapper.class);
         applicationMapper.insertSelective(transferApplication);
    }

    //查所有
    @Override
    public List<TransferApplication> findAll() {
        List<TransferApplication> list;
        TransferApplicationMapper applicationMapper=sqlSession.getMapper(TransferApplicationMapper.class);
        list=applicationMapper.findAll();
        return list;
    }

    //通过id查
    @Override
    public TransferApplication selectById(Integer id) {
        TransferApplicationMapper applicationMapper=sqlSession.getMapper(TransferApplicationMapper.class);
        TransferApplication application = applicationMapper.selectByPrimaryKey(id);
        return application;
    }

    //管理员修改申请状态
    @Override
    public void updateStatus(TransferApplication application) {
        TransferApplicationMapper applicationMapper=sqlSession.getMapper(TransferApplicationMapper.class);
        applicationMapper.updateByPrimaryKeySelective(application);
    }


}
