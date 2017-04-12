package com.anedia.vehicle.booking.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by xyadnep on 4/12/17.
 */
@Entity
@Table(name = "credential")
public class CredentialBean implements Serializable {

    @Id
    @Column(name = "user_id")
    private String userID;

    @Column(name = "password")
    private String password;

    @Column(name = "user_type")
    private String userType;

    @Column(name = "login_status")
    private int loginStatus;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public int getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(int loginStatus) {
        this.loginStatus = loginStatus;
    }

    @Override
    public String toString() {
        return "CredentialBean{" +
                "userID='" + userID + '\'' +
                ", password='" + password + '\'' +
                ", userType='" + userType + '\'' +
                ", loginStatus=" + loginStatus +
                '}';
    }
}
