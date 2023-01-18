package cn.bdqn.shopping.controller;

import cn.bdqn.shopping.entity.GoodsOrder;
import cn.bdqn.shopping.entity.User;
import cn.bdqn.shopping.service.GoodsOrderService;
import cn.bdqn.shopping.utils.Result;
import cn.bdqn.shopping.vo.BuyReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Slf4j
@ResponseBody
@Api(tags = "订单服务相关接口描叙")
@RequestMapping("/goodsorder")
public class GoodsOrderController extends BaseController {
    @Autowired
    private GoodsOrderService goodsOrderService;

    @GetMapping("/list")
    @ApiOperation(value = "查询订单", notes = "查询订单的接口")
    public Result<List<GoodsOrder>> list() {
        List<GoodsOrder> goodsOrderList = goodsOrderService.queryAllGoodsOrderlogList();
        return Result.success(goodsOrderList);
    }

    @GetMapping("/buy")
    @ApiOperation(value = "购买商品", notes = "购买商品的接口")
    public Result<?> buy(BuyReq buyReq, HttpServletRequest request) throws Exception {
        User jwtUser = (User) request.getAttribute("user");
        if (jwtUser == null) {
            return Result.error("token不正确");
        }
        Result<?> result = goodsOrderService.buy(buyReq, jwtUser.getId());
        return result;
    }

    @GetMapping("/buy2")
    @ApiOperation(value = "购买商品版本2", notes = "购买商品的接口")
    public Result<?> buy2(BuyReq buyReq, HttpServletRequest request) {
        User jwtUser = (User) request.getAttribute("user");
        if (jwtUser == null) {
            return Result.error("token不正确");
        }
        Result<?> result = goodsOrderService.buy2(buyReq, jwtUser.getId());
        return result;
    }

    @GetMapping("/select")
    @ApiOperation(value = "根据用户id查询一共几条订单", notes = "根据用户id查询一共几条订单的接口")
    public Result<Long> select(Integer userId) {
        long counts = goodsOrderService.selectCount(userId);
        return Result.success(counts);
    }

}
