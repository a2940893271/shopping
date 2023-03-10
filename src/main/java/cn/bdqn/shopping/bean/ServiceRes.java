package cn.bdqn.shopping.bean;

import lombok.Data;

@Data
public class ServiceRes {
    private Integer code;
    private String msg;
    private String jwt;

    public ServiceRes(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }

    public ServiceRes(Integer code,String msg,String jwt){
        this.code=code;
        this.msg=msg;
        this.jwt=jwt;
    }

}
