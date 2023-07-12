package com.edu.phonestore.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.edu.phonestore.room.entity.User;

@Dao
public interface UserDao {
    @Insert
    void addUser(User... users);

    @Query("DELETE from user")
    void deleteUser();

    @Query("SELECT * FROM user")
    User getUserInfo();
}
