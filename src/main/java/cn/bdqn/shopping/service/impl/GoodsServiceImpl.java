package cn.bdqn.shopping.service.impl;

import cn.bdqn.shopping.entity.Goods;
import cn.bdqn.shopping.mapper.GoodsMapper;
import cn.bdqn.shopping.service.GoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Override
    public List<Goods> queryAllGoodslogList() throws Exception {
//        if (2>1){
//            throw new Exception("商品不存在");
//        }
        return list(null);
    }

    @Override
    public Goods getbyId(Integer id) {
        Goods goods= this.baseMapper.selectById(id);
        return goods;
    }

    @Override
    public int changeStockNumber(Goods goods) {
        return this.baseMapper.updateById(goods);
    }
}
