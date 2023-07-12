package com.edu.phonestore.payload.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Role implements Serializable {

    @SerializedName("id")
    private int id;
    @SerializedName("roleName")
    private String roleName;

    public Role(int id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

//    @Override
//    public String toString() {
//        return "Role{" +
//                "id=" + id +
//                ", roleName='" + roleName + '\'' +
//                '}';
//    }
}
