package cn.bdqn.shopping.service;

import cn.bdqn.shopping.entity.Balance;
import cn.bdqn.shopping.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface GoodsService extends IService<Goods> {
    List<Goods> queryAllGoodslogList() throws Exception;

    Goods getbyId(Integer id);

    int changeStockNumber(Goods goods);
}
