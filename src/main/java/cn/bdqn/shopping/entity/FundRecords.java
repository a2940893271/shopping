package cn.bdqn.shopping.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "资金流水")
public class FundRecords {
    // 自增长id
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("before_balance")
    @ApiModelProperty(value = "购买前余额", required = true)
    private double beforeBalance;

    @TableField("after_balance")
    @ApiModelProperty(value = "购买后余额", required = true)
    private double afterBalance;

    @TableField("change_amount")
    @ApiModelProperty(value = "改变金额", required = true)
    private double changeAmount;

    @TableField("create_time")
    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

    @TableField("update_time")
    @ApiModelProperty(value = "更改时间", required = true)
    private Date updateTime;

    @TableField("user_id")
    @ApiModelProperty(value = "用户id", required = true)
    private Integer userId;


}