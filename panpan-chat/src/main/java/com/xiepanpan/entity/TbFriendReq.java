package com.xiepanpan.entity;

import java.io.Serializable;

/**
 * (TbFriendReq)实体类
 *
 * @author makejava
 * @since 2020-08-01 16:11:14
 */
public class TbFriendReq implements Serializable {
    private static final long serialVersionUID = 211281481350214892L;
    /**
    * id
    */
    private String id;
    /**
    * 请求好友用户id
    */
    private String fromUserid;
    /**
    * 被请求好友用户id
    */
    private String toUserid;
    /**
    * 请求时间
    */
    private Object createtime;
    /**
    * 发送的消息
    */
    private String message;
    /**
    * 是否已处理
    */
    private Integer status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFromUserid() {
        return fromUserid;
    }

    public void setFromUserid(String fromUserid) {
        this.fromUserid = fromUserid;
    }

    public String getToUserid() {
        return toUserid;
    }

    public void setToUserid(String toUserid) {
        this.toUserid = toUserid;
    }

    public Object getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Object createtime) {
        this.createtime = createtime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}