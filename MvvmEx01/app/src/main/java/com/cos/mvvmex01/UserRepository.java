package com.cos.mvvmex01;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserRepository {

    // select 할 때만 쿼리 적고, 나머지 insert, update, delete 는 어노테이션 걸면 됨.

    @Query("SELECT * FROM user")
    List<User> findAll();

    @Insert
    void insert(User user);

    @Query("SELECT * FROM user WHERE uid = :uid")
    User findById(int uid);

//    @Delete
//    void delete(User user);
}
