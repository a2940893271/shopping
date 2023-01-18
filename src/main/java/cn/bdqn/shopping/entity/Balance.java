package cn.bdqn.shopping.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "余额")
public class Balance {
    // 自增长id

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("create_time")
    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

    @TableField("update_time")
    @ApiModelProperty(value = "更改时间", required = true)
    private Date updateTime;

    @ApiModelProperty(value = "余额", required = true)
    private double balance;

    @TableField("user_id")
    @ApiModelProperty(value = "用户id", required = true)
    private Integer userId;

}
