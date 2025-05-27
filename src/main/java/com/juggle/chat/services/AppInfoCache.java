package com.juggle.chat.services;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.juggle.chat.mappers.AppInfoMapper;
import com.juggle.chat.models.AppInfo;

@Component
public class AppInfoCache {

    private static Cache<String,AppInfo> cache = Caffeine.newBuilder().maximumSize(1000).expireAfterWrite(10,TimeUnit.MINUTES).build();
    private static final AppInfo notExist = new AppInfo();
    private static AppInfoMapper appInfoMapper;

    @Autowired
    public void setAppInfoMapper(AppInfoMapper mapper){
        appInfoMapper = mapper;
    }

    public static AppInfo getAppInfo(String appkey){
        AppInfo cacheAppInfo = cache.get(appkey, k->getAppInfoFromDb(appkey));
        if (cacheAppInfo == null || cacheAppInfo == notExist){
            return null;
        }else{
            return cacheAppInfo;
        }
    }

    private static AppInfo getAppInfoFromDb(String appkey){
        AppInfo appInfo = appInfoMapper.findByAppkey(appkey);
        if(appInfo!=null){
            return appInfo;
        }
        return notExist;
    }
}
