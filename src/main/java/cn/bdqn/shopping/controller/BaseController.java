package cn.bdqn.shopping.controller;

import cn.bdqn.shopping.exception.ParamException;
import cn.bdqn.shopping.utils.Result;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.validation.BindException;

import java.util.List;

public class BaseController {

    @ExceptionHandler
    @ResponseBody
    public Result<?> exceptionHandler(Exception e){
       if(e instanceof BindException) {
            //@Valid注解
            BindException bindException=(BindException)e;
            List<FieldError> fieldErrors=bindException.getFieldErrors();
            for (FieldError error:fieldErrors){
                return Result.error("参数错误:"+error.getDefaultMessage());
            }
            //如果e是ParamException的实例
        } else if (e instanceof ParamException) {
           return Result.error(((ParamException) e).getCode(),e.getMessage());

       } else{
            return Result.error(e.getMessage());
        }
        return null;
    }

}