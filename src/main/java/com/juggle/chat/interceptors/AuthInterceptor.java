package com.juggle.chat.interceptors;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.juggle.chat.models.AppInfo;
import com.juggle.chat.models.AuthToken;
import com.juggle.chat.services.ApiKeyService;
import com.juggle.chat.services.AppInfoCache;
import com.juggle.chat.services.AuthTokenService;

@Component
public class AuthInterceptor implements HandlerInterceptor{
    private static String Header_Appkey = "appkey";
    private static String Header_Authorization = "Authorization";
    private static String Header_TraceId = "traceid";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        if (!(handler instanceof HandlerMethod)){
            return true;
        }

        //check appkey
        String appkey = request.getHeader(Header_Appkey);
        if(appkey==null||appkey.isEmpty()){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        }
        //check app exist
        AppInfo appInfo = AppInfoCache.getAppInfo(appkey);
        if(appInfo==null){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        }

        String currentUserId = "";
        try{
            List<String> excludePaths = List.of(
                "/jim/hello",
                "/jim/login",
                "/jim/sms/send",
                "/jim/sms_login",
                "/jim/sms/login",
                "/jim/email/send",
                "/jim/email/login",
                "/jim/login/qrcode",
                "/jim/login/qrcode/check"
            );
            String requestUri = request.getRequestURI();
            for (String path : excludePaths){
                if(path.equals(requestUri)){
                    return true;
                }
            }

            //get token
            String tokenStr = request.getHeader(Header_Authorization);
            if(tokenStr==null || tokenStr.isEmpty()){
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }

            if(tokenStr.startsWith("Bearer ")){
                tokenStr = tokenStr.substring(7);
                if(!ApiKeyService.checkApiKey(tokenStr, appkey, appInfo.getAppSecureKey())){
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return false;
                }
            }else{
                try {
                    AuthToken authToken = AuthTokenService.parseAuthToken(tokenStr, appInfo.getAppSecureKey());
                    currentUserId = authToken.getUserId();
                } catch (Exception e) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return false;
                }
            }
            return true;
        }finally{
            RequestContext ctx = new RequestContext(appkey, currentUserId, "traceId");
            RequestContext.set(ctx);
        }
    }
}
