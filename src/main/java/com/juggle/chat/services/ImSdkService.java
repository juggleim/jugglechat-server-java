package com.juggle.chat.services;

import com.juggle.chat.models.AppInfo;
import com.juggle.im.JuggleIm;

//Cache for jimsdk
public class ImSdkService {
    public static JuggleIm getJimSdk(String appkey){
        AppInfo appInfo = AppInfoCache.getAppInfo(appkey);
        if(appInfo == null){
            return null;
        }
        JuggleIm jimSdk = new JuggleIm(appkey, appInfo.getAppSecret(), "http://127.0.0.1:9001");
        return jimSdk;
    }
}
