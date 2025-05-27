package com.juggle.chat.interceptors;

import java.util.Map;

public class RequestContext {
    private static final ThreadLocal<RequestContext> contextHolder = new ThreadLocal<>();
    
    private String appkey;
    private String currentUserId;
    private String traceId;
    private Map<String,Object> attributes;

    public static void set(RequestContext context){
        contextHolder.set(context);
    }

    public static RequestContext get(){
        return contextHolder.get();
    }

    public static void clear(){
        contextHolder.remove();
    }

    public static String getAppkeyFromCtx(){
        RequestContext context = contextHolder.get();
        if (context != null) {
            return context.appkey;
        }
        return null;
    }

    public static String getCurrentUserIdFromCtx(){
        RequestContext context = contextHolder.get();
        if (context != null) {
            return context.currentUserId;
        }
        return null;
    }

    public RequestContext(String appkey, String currentUserId, String traceId) {
        this.appkey = appkey;
        this.currentUserId = currentUserId;
        this.traceId = traceId;
        this.attributes = new java.util.HashMap<>();
    }

    public String getAppkey(){
        return this.appkey;
    }

    public String getCurrentUserId(){
        return this.currentUserId;
    }

    public String getTraceId(){
        return this.traceId;
    }
}
