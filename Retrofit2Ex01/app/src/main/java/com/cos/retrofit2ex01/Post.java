package com.cos.retrofit2ex01;

import com.google.gson.annotations.SerializedName;
public class Post {
    private int userId;
    private int id;
    private String title;
    @SerializedName("body") // 키 값 작성 : 통신을 위한 데이터가 될 때 해당 값이 Body가 됨.
    private String text;
    public int getUserId() {
        return userId;
    }
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getText() {
        return text;
    }
}