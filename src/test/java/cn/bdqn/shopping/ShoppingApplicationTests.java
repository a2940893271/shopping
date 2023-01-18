package cn.bdqn.shopping;

import cn.bdqn.shopping.entity.Balance;
import cn.bdqn.shopping.entity.GoodsOrder;
import cn.bdqn.shopping.service.BalanceService;
import cn.bdqn.shopping.service.GoodsOrderService;
import cn.bdqn.shopping.utils.Result;
import cn.bdqn.shopping.vo.BuyReq;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@SpringBootTest
class ShoppingApplicationTests {
@Autowired
private BalanceService balanceService;
@Autowired
private GoodsOrderService goodsOrderService;
    @Test
    public void getBlance(){
        Balance balance=  balanceService.getbyUserId(1);
        System.out.println(balance);
    }

    @Test
    public void getGoodsOrder(){
        BuyReq buyReq=new BuyReq();
        buyReq.setQuantity(100);
        buyReq.setGoodsId(4);
        Result<?> goodsOrder=goodsOrderService.buy(buyReq,1);
        System.out.println(goodsOrder);
    }
    @Test
    public void getTime1(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime= simpleDateFormat.format(new Date());
        String orderOn= currentTime.replaceAll("[[\\s-:punct:]]", "");
        Random r = new Random();
        int orderOn1= r.nextInt(900000)+100000;//(int)(Math.random()*999999)
        String getOrderOn=orderOn+orderOn1;
        System.out.println(getOrderOn);
    }
    @Test
    public void selectCount(){
        long counts= goodsOrderService.selectCount(9);
        System.out.println(counts);
    }




}
