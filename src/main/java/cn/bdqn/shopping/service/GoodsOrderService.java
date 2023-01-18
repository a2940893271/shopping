package cn.bdqn.shopping.service;

import cn.bdqn.shopping.entity.GoodsOrder;
import cn.bdqn.shopping.utils.Result;
import cn.bdqn.shopping.vo.BuyReq;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface GoodsOrderService extends IService<GoodsOrder> {

    List<GoodsOrder> queryAllGoodsOrderlogList();

    Result<?> buy(BuyReq buyReq, Integer userId) throws Exception;

    Result<?> buy2(BuyReq buyReq, Integer userId);

    int insert(GoodsOrder goodsOrder);

    String randomOrderNumber();

    Long selectCount(Integer userId);
}
