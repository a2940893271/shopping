package cn.bdqn.shopping.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "商品购买请求")
public class BuyReq {
    @ApiModelProperty(value = "商品id", required = true)
    private Integer goodsId;

    @ApiModelProperty(value = "购买数量", required = true)
    private Integer quantity;
}
