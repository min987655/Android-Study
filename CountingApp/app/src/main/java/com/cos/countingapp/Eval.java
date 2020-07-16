package com.cos.countingapp;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

// 자바스트립트 엔진 생성
// js 적고 .찍으면 eval 사용할 수 있음.
public class Eval {
    public static String cal(String result){
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        try{
            return engine.eval(result).toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
