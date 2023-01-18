package cn.bdqn.shopping.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class User {
    // 自增长id
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("real_name")
    @ApiModelProperty(value = "姓名", required = true)
    private String realName;

    @TableField("user_name")
    @ApiModelProperty(value = "用户名", required = true)
    private String userName;

    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @TableField("create_time")
    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

    @TableField("update_time")
    @ApiModelProperty(value = "更改时间", required = true)
    private Date updateTime;

    @TableField("last_login")
    @ApiModelProperty(value = "最后登陆时间", required = true)
    private Date lastLogin;

    public User() {

    }

    public User(Integer id, String userName) {
        this.id = id;
        this.userName = userName;
    }

}
