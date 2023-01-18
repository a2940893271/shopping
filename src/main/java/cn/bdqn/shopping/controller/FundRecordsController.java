package cn.bdqn.shopping.controller;

import cn.bdqn.shopping.entity.Balance;
import cn.bdqn.shopping.entity.FundRecords;
import cn.bdqn.shopping.service.BalanceService;
import cn.bdqn.shopping.service.FundRecordsService;
import cn.bdqn.shopping.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@ResponseBody
@Api(tags = "资金流水服务相关接口描叙")
@RequestMapping("/fundrecords")
public class FundRecordsController extends BaseController {
    @Autowired
    private FundRecordsService fundRecordsService;

    @GetMapping("/list")
    @ApiOperation(value = "查询资金流水", notes = "查询资金流水的接口")
    public Result<List<FundRecords>> list() {
        List<FundRecords> fundRecordsList = fundRecordsService.queryFlow();
        return Result.success(fundRecordsList);
    }
}
