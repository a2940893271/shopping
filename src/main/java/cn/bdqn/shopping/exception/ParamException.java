package cn.bdqn.shopping.exception;


public class ParamException extends RuntimeException{

    private static final long serialVersionUID=-1L;

    private Integer code;

    public ParamException(int code,String message){
        super(message);
        this.code=code;
    }

    public Integer getCode(){
        return code;
    }

    public void setCode(Integer code){
        this.code=code;
    }

}