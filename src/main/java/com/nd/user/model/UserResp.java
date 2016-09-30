package com.nd.user.model;

import com.nd.uc.repository.UserEntity;

/**
 * 类名称：返回值包装类
 * 类描述：
 * 创建人：newtonk
 * 创建日期：2016/9/12 0012
 */
public class UserResp {
    private String teacherId;
    private String nickName;
    private String realName;
    private String role;
    public UserResp(){}

    public UserResp(UserEntity userEntity){
        this.teacherId = userEntity.getUserId();
        this.nickName = userEntity.getNickName();
        this.realName = userEntity.getRealName();
        this.role = userEntity.getRole();
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
