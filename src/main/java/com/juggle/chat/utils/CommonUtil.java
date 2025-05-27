package com.juggle.chat.utils;

import java.util.Random;

import com.alibaba.fastjson.JSON;

public class CommonUtil {
    public static final String toJson(Object obj){
        String jsonStr = JSON.toJSONString(obj);
        return jsonStr;
    }

    public static final String generateUuid(){
        return java.util.UUID.randomUUID().toString().replace("-", "");
    }

    public static final String generageShortUuid(){
        return java.util.UUID.randomUUID().toString().replace("-", "").substring(0, 16);
    }

    public static final int randomInt(){
        Random ran = new Random();
        return ran.nextInt(100000);
    }

    public static final String int2String(int n){
        return String.valueOf(n);
    }
}
