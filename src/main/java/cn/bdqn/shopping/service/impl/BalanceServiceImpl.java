package cn.bdqn.shopping.service.impl;

import cn.bdqn.shopping.entity.Balance;
import cn.bdqn.shopping.mapper.BalanceMapper;
import cn.bdqn.shopping.service.BalanceService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author PAG
 * @since 2023-01-14
 */
@Service
@Slf4j
public class BalanceServiceImpl extends ServiceImpl<BalanceMapper, Balance> implements BalanceService {

    @Autowired
    private BalanceService balanceService;
    @Autowired
    private BalanceMapper balanceMapper;
    @Override
    public List<Balance> balanceInquiry() {
        return list(null);
    }

    @Override
    public Balance getbyUserId(Integer userId) {
        QueryWrapper<Balance> wrapper=new QueryWrapper();
        wrapper.eq("user_id",userId);
        Balance balance = balanceMapper.selectOne(wrapper);
        return balance;
    }

    @Override
    public int changeBalance(Balance balance) {
        return this.baseMapper.updateById(balance);
    }


}
