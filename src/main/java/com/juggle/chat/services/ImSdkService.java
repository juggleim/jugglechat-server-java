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
        String imApiDomain = System.getProperty("im.api.domain");
        if (imApiDomain == null || imApiDomain.isEmpty()) {
            imApiDomain = System.getenv("IM_API_DOMAIN");
        }
        if (imApiDomain == null || imApiDomain.isEmpty()) {
            imApiDomain = "http://127.0.0.1:9001";
        }
        JuggleIm jimSdk = new JuggleIm(appkey, appInfo.getAppSecret(), imApiDomain);
        return jimSdk;
    }
}
