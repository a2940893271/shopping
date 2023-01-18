package cn.bdqn.shopping.service;

import cn.bdqn.shopping.entity.Balance;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author PAG
 * @since 2023-01-14
 */
public interface BalanceService extends IService<Balance> {
    List<Balance> balanceInquiry();

    Balance getbyUserId(Integer userId);

    int changeBalance(Balance balance);

}
