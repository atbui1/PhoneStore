package com.edu.phonestore.utils;

import androidx.room.TypeConverter;

import com.edu.phonestore.payload.response.Role;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class RoleConverters {
//    @TypeConverter
//    public static ArrayList<String> fromString(String value) {
//        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
//        return new Gson().fromJson(value, listType);
//    }

//    @TypeConverter
//    public static String fromArrayList(ArrayList<String> list) {
//        Gson gson = new Gson();
//        String json = gson.toJson(list);
//        return json;
//    }

    @TypeConverter
    public String fromRoleToString(List<Role> roles) {
        if (roles == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Role>>() {}.getType();
        String json = gson.toJson(roles, type);
        return json;
    }

    @TypeConverter
    public List<Role> fromStringToList(String strRole) {
        if (strRole == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Role>>() {}.getType();
        List<Role> roles = gson.fromJson(strRole, type);
        return roles;
    }
}
