package cn.bdqn.shopping.bean;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "用户")
public class User {
    // 自增长id
    @TableId(type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(value = "用户名", required = true)
    private String userName;
    @ApiModelProperty(value = "密码", required = true)
    private String passWord;
    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;
    @ApiModelProperty(value = "最后登陆时间", required = true)
    private Date lastLogin;

    public User() {

    }

    public User(Integer id, String userName) {
        this.id = id;
        this.userName = userName;
    }

}
