package com.xiepanpan.exception;

import com.xiepanpan.entity.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: xiepanpan
 * @Date: 2020/8/2 0002
 * @Description:  全局异常拦截器
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 拦截异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result exceptionHandler(Exception e){
        return new Result(false,e.getMessage());
    }
}