package com.juggle.chat.utils;

import com.alibaba.fastjson.JSON;

public class CommonUtil {
    public static final String toJson(Object obj){
        String jsonStr = JSON.toJSONString(obj);
        return jsonStr;
    }
}
