package cn.bdqn.shopping.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "订单")
public class GoodsOrder {
    // 自增长id
    @TableId(value = "id",type = IdType.INPUT)
    private Integer id;

    @TableField("goods_id")
    @ApiModelProperty(value = "商品id", required = true)
    private Integer goodsId;

    @TableField("total_number")
    @ApiModelProperty(value = "商品数量", required = true)
    private Integer totalNumber;

    @TableField("total_amount")
    @ApiModelProperty(value = "商品总价钱", required = true)
    private double totalAmount;

    @TableField("user_id")
    @ApiModelProperty(value = "用户id", required = true)
    private Integer userId;

    @TableField("order_no")
    @ApiModelProperty(value = "订单号", required = true)
    private String orderNo;

    @TableField("create_time")
    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

    @TableField("update_time")
    @ApiModelProperty(value = "更改时间", required = true)
    private Date updateTime;

    @ApiModelProperty(value = "订单状态", required = true)
    private Integer status;

    @TableField("pay_time")
    @ApiModelProperty(value = "付款时间", required = true)
    private Date payTime;

    @TableField("complete_time")
    @ApiModelProperty(value = "完成时间", required = true)
    private Date completeTime;
}
