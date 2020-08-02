package com.xiepanpan.entity;

import java.io.Serializable;

/**
 * (TbFriend)实体类
 *
 * @author makejava
 * @since 2020-08-01 16:11:14
 */
public class TbFriend implements Serializable {
    private static final long serialVersionUID = 469772444090141503L;
    
    private String id;
    /**
    * 用户id
    */
    private String userid;
    /**
    * 好友id
    */
    private String friendsId;
    /**
    * 备注
    */
    private String comments;
    /**
    * 添加好友日期
    */
    private Object createtime;


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

    public String getFriendsId() {
        return friendsId;
    }

    public void setFriendsId(String friendsId) {
        this.friendsId = friendsId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Object getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Object createtime) {
        this.createtime = createtime;
    }

}