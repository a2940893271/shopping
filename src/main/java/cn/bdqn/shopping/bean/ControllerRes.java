package cn.bdqn.shopping.bean;

import lombok.Data;

@Data
public class ControllerRes {
    private Integer code;
    private String msg;

    public ControllerRes(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
