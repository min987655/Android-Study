package com.cos.mvvmex01;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class},version = 2) // 스키마 바뀌면 (컬럼 바꾸고 싶으면) 버전 업 해야 함
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserRepository userRepository();
}
