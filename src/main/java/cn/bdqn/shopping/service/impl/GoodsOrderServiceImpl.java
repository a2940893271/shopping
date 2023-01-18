package cn.bdqn.shopping.service.impl;

import cn.bdqn.shopping.entity.Balance;
import cn.bdqn.shopping.entity.FundRecords;
import cn.bdqn.shopping.entity.Goods;
import cn.bdqn.shopping.entity.GoodsOrder;
import cn.bdqn.shopping.exception.ParamException;
import cn.bdqn.shopping.mapper.GoodsOrderMapper;
import cn.bdqn.shopping.service.BalanceService;
import cn.bdqn.shopping.service.FundRecordsService;
import cn.bdqn.shopping.service.GoodsOrderService;
import cn.bdqn.shopping.service.GoodsService;
import cn.bdqn.shopping.utils.Result;
import cn.bdqn.shopping.vo.BuyReq;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
public class GoodsOrderServiceImpl extends ServiceImpl<GoodsOrderMapper, GoodsOrder> implements GoodsOrderService {
    @Autowired
    private BalanceService balanceService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private FundRecordsService fundRecordsService;
    @Autowired
    private GoodsOrderService goodsOrderService;
    @Autowired
    private GoodsOrderMapper goodsOrderMapper;

    @Override
    public List<GoodsOrder> queryAllGoodsOrderlogList() {
        return list(null);
    }

    @Override
    public Result<?> buy(BuyReq buyReq, Integer userId) throws Exception {
        if (buyReq.getQuantity()<=0){
            throw new ParamException(4001,"购买数量不正确");
        }
        //查商品
        Goods goods = goodsService.getbyId(buyReq.getGoodsId());
        if (goods == null){
            throw new ParamException(4002,"商品不存在");
        }
        double totalAmount =(buyReq.getQuantity()*goods.getPrice());
        //第一步判断用户余额
        //交易前余额
        Balance balanceObj=  balanceService.getbyUserId(userId);
        if (balanceObj == null){
            throw new ParamException(4003,"余额不足");
        }
        double beforeBalance = balanceObj.getBalance();
        if (totalAmount > balanceObj.getBalance()){
            throw new ParamException(4004,"余额不足");
        }
        //第二步判断库存是否足够
        if (goods.getStockNumber()<buyReq.getQuantity()){
            throw new ParamException(4005,"库存不足");
        }
        //扣余额
        balanceObj.setBalance(balanceObj.getBalance()-totalAmount);
        balanceService.changeBalance(balanceObj);
        //交易后余额
        double afterBalance=balanceObj.getBalance();
        //增加流水
        FundRecords fundRecords=new FundRecords();
        fundRecords.setAfterBalance(afterBalance);
        fundRecords.setBeforeBalance(beforeBalance);
        fundRecords.setChangeAmount(-totalAmount);
        fundRecords.setUserId(userId);
        fundRecords.setCreateTime(new Date());
        fundRecords.setUpdateTime(new Date());
        fundRecordsService.insert(fundRecords);
        //减库存
        goods.setStockNumber(goods.getStockNumber() - buyReq.getQuantity());
        goodsService.changeStockNumber(goods);
        int stockNumber=goods.getStockNumber();
        //生成订单号
        String randomOrderNumber = goodsOrderService.randomOrderNumber();
        //创建订单，inster数据
        GoodsOrder goodsOrder=new GoodsOrder();
        goodsOrder.setGoodsId(buyReq.getGoodsId());
        goodsOrder.setTotalNumber(buyReq.getQuantity());
        goodsOrder.setTotalAmount(totalAmount);
        goodsOrder.setUserId(userId);
        goodsOrder.setOrderNo(randomOrderNumber());
        goodsOrder.setCreateTime(new Date());
        goodsOrder.setUpdateTime(new Date());
        goodsOrder.setStatus(0);
        goodsOrder.setPayTime(new Date());
        goodsOrder.setCompleteTime(new Date());
        goodsOrderService.insert(goodsOrder);
        return Result.success("创建成功");
    }
    @Override
    public Result<?> buy2(BuyReq buyReq, Integer userId) {
        if (buyReq.getQuantity()<=0){
            return Result.error("购买数量不正确");
        }
        //查商品
        Goods goods = goodsService.getbyId(buyReq.getGoodsId());
        if (goods == null){
            return Result.error("商品不存在");
        }
        double totalAmount =(buyReq.getQuantity()*goods.getPrice());
        //第一步判断用户余额
        //交易前余额
        Balance balanceObj=  balanceService.getbyUserId(userId);
        if (balanceObj == null){
            return Result.error("余额不足");
        }
        double beforeBalance = balanceObj.getBalance();
        if (totalAmount > balanceObj.getBalance()){
            return Result.error("余额不足");
        }
        //第二步判断库存是否足够
        if (goods.getStockNumber()<buyReq.getQuantity()){
            return Result.error("库存不足");
        }
        //扣余额
        balanceObj.setBalance(balanceObj.getBalance()-totalAmount);
        balanceService.changeBalance(balanceObj);
        //交易后余额
        double afterBalance=balanceObj.getBalance();
        //增加流水
        FundRecords fundRecords=new FundRecords();
        fundRecords.setAfterBalance(afterBalance);
        fundRecords.setBeforeBalance(beforeBalance);
        fundRecords.setChangeAmount(-totalAmount);
        fundRecords.setUserId(userId);
        fundRecords.setCreateTime(new Date());
        fundRecords.setUpdateTime(new Date());
        fundRecordsService.insert(fundRecords);
        //减库存
        goods.setStockNumber(goods.getStockNumber() - buyReq.getQuantity());
        goodsService.changeStockNumber(goods);
        int stockNumber=goods.getStockNumber();
        //生成订单号
         String randomOrderNumber = goodsOrderService.randomOrderNumber();
        //创建订单，inster数据
        GoodsOrder goodsOrder=new GoodsOrder();
        goodsOrder.setGoodsId(buyReq.getGoodsId());
        goodsOrder.setTotalNumber(buyReq.getQuantity());
        goodsOrder.setTotalAmount(totalAmount);
        goodsOrder.setUserId(userId);
        goodsOrder.setOrderNo(randomOrderNumber());
        goodsOrder.setCreateTime(new Date());
        goodsOrder.setUpdateTime(new Date());
        goodsOrder.setStatus(0);
        goodsOrder.setPayTime(new Date());
        goodsOrder.setCompleteTime(new Date());
        goodsOrderService.insert(goodsOrder);
        return Result.success("创建成功");
    }


    @Override
    public int insert(GoodsOrder goodsOrder) {
        return this.baseMapper.insert(goodsOrder);
    }

    //生成随机订单号
    public String randomOrderNumber(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime= simpleDateFormat.format(new Date());
        String orderOn= currentTime.replaceAll("[[\\s-:punct:]]", "");
        Random r = new Random();
        int orderOn1= r.nextInt(900000)+100000;//(int)(Math.random()*999999)
        String getOrderOns=orderOn+orderOn1;
        return getOrderOns;
    }

    @Override
    public Long selectCount(Integer userId) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("user_id",userId);
        Long Count= goodsOrderMapper.selectCount(queryWrapper);
        return Count;
    }
}
