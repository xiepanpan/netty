package com.xiepanpan.entity.vo;

/**
 * @author: xiepanpan
 * @Date: 2020/8/1 0001
 * @Description:  返回给客户端的数据封装到实体类中
 */
public class Result {

    //是否操作成功
    private boolean success;
    //返回消息
    private String message;
    //返回附件的对象
    private Object result;

    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Result(boolean success, String message, Object result) {
        this.success = success;
        this.message = message;
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}