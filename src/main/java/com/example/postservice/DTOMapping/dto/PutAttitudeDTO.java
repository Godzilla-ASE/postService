package com.example.postservice.DTOMapping.dto;

public class PutAttitudeDTO {
    private int postid;
    private int userid;
    private boolean attitude_type;
    private boolean isCancel; // false

    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public boolean isAttitude_type() {
        return attitude_type;
    }

    public void setAttitude_type(boolean attitude_type) {
        this.attitude_type = attitude_type;
    }

    public boolean getisCancel() {
        return isCancel;
    }

    public void setCancel(boolean cancel) {
        isCancel = cancel;
    }
}
