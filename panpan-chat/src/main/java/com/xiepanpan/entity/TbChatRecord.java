package com.xiepanpan.entity;

import java.io.Serializable;

/**
 * (TbChatRecord)实体类
 *
 * @author makejava
 * @since 2020-08-01 16:11:03
 */
public class TbChatRecord implements Serializable {
    private static final long serialVersionUID = 313768925900338417L;
    /**
    * id
    */
    private String id;
    /**
    * 用户id
    */
    private String userid;
    /**
    * 好友id
    */
    private String friendid;
    /**
    * 是否已读
    */
    private Integer hasRead;
    /**
    * 聊天时间
    */
    private Object createtime;
    /**
    * 是否删除
    */
    private Integer hasDelete;
    /**
    * 消息
    */
    private String message;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFriendid() {
        return friendid;
    }

    public void setFriendid(String friendid) {
        this.friendid = friendid;
    }

    public Integer getHasRead() {
        return hasRead;
    }

    public void setHasRead(Integer hasRead) {
        this.hasRead = hasRead;
    }

    public Object getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Object createtime) {
        this.createtime = createtime;
    }

    public Integer getHasDelete() {
        return hasDelete;
    }

    public void setHasDelete(Integer hasDelete) {
        this.hasDelete = hasDelete;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}