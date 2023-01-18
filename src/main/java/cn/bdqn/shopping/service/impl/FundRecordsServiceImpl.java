package cn.bdqn.shopping.service.impl;

import cn.bdqn.shopping.entity.FundRecords;
import cn.bdqn.shopping.mapper.FundRecordsMapper;
import cn.bdqn.shopping.service.FundRecordsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class FundRecordsServiceImpl extends ServiceImpl<FundRecordsMapper,FundRecords> implements FundRecordsService{


    @Override
    public List<FundRecords> queryFlow() {
        return list(null);
    }

    @Override
    public int insert(FundRecords fundRecords) {
        return this.baseMapper.insert(fundRecords);
    }
}
