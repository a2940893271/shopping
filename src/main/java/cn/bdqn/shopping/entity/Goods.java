package cn.bdqn.shopping.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "商品")
public class Goods {
    // 自增长id
    @TableId(type = IdType.AUTO)

    private Integer id;

    @ApiModelProperty(value = "商品名称", required = true)
    private String name;

    @ApiModelProperty(value = "商品价格", required = true)
    private double price;

    @TableField("stock_number")
    @ApiModelProperty(value = "库存数量", required = true)
    private Integer stockNumber;

    @TableField("create_time")
    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

    @TableField("update_time")
    @ApiModelProperty(value = "更改时间", required = true)
    private Date updateTime;

}
