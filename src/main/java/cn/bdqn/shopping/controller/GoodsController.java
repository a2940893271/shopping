package cn.bdqn.shopping.controller;

import cn.bdqn.shopping.entity.Goods;
import cn.bdqn.shopping.service.GoodsService;
import cn.bdqn.shopping.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@Slf4j
@ResponseBody
@Api(tags = "商品服务相关接口描叙")
@RequestMapping("/goods")
public class GoodsController extends BaseController {
    @Autowired
    private GoodsService goodsService;

    @GetMapping("/list")
    @ApiOperation(value = "查询商品", notes = "查询商品的接口")
    public Result<List<Goods>> list() throws Exception {
        List<Goods> goodsList = goodsService.queryAllGoodslogList();
        return Result.success(goodsList);
    }
}
