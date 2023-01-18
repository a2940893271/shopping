package cn.bdqn.shopping.controller;

import cn.bdqn.shopping.entity.Balance;
import cn.bdqn.shopping.mapper.BalanceMapper;
import cn.bdqn.shopping.service.BalanceService;
import cn.bdqn.shopping.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
@Api(tags = "余额服务相关接口描叙")
@RequestMapping("/balance")
public class BalanceController extends BaseController {
    @Autowired
    private BalanceService balanceService;
    @Autowired
    private BalanceMapper balanceMapper;

    @GetMapping("/list")
    @ApiOperation(value = "查询余额", notes = "查询余额的接口")
    public Result<List<Balance>> list() {
        List<Balance> balanceList = balanceService.balanceInquiry();
        return Result.success(balanceList);
    }

    @GetMapping("/balanceInquiryId")
    @ApiOperation(value = "根据id查询余额", notes = "查询余额的接口")
    public Result<List<Balance>> listone(Integer userId) {
        QueryWrapper<Balance> wrapper = new QueryWrapper();
        wrapper.eq("user_id", userId);
        return Result.success(balanceMapper.selectList(wrapper));
    }
}
