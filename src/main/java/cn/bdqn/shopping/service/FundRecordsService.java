package cn.bdqn.shopping.service;

import cn.bdqn.shopping.entity.Balance;
import cn.bdqn.shopping.entity.FundRecords;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface FundRecordsService extends IService<FundRecords> {
    List<FundRecords> queryFlow();

    int insert(FundRecords fundRecords);
}
