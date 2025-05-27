package com.juggle.chat.services;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.juggle.chat.apimodels.LoginUserResp;
import com.juggle.chat.exceptions.JimErrorCode;
import com.juggle.chat.exceptions.JimException;
import com.juggle.chat.interceptors.RequestContext;
import com.juggle.chat.mappers.UserExtMapper;
import com.juggle.chat.mappers.UserMapper;
import com.juggle.chat.models.AppInfo;
import com.juggle.chat.models.AuthToken;
import com.juggle.chat.models.User;
import com.juggle.chat.models.UserExt;
import com.juggle.chat.models.UserExtKeys;
import com.juggle.chat.utils.CommonUtil;
import com.juggle.im.JuggleIm;
import com.juggle.im.models.user.UserInfo;
import com.juggle.im.models.user.UserTokenResult;

@Service
public class LoginService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserExtMapper userExtMapper;

    public LoginUserResp smsLogin(String phone,String code)throws JimException{
        this.checkPhoneSmsCode(phone, code);
        
        String appkey = RequestContext.getAppkeyFromCtx();
        User user = userMapper.findByPhone(appkey, phone);
        if(user==null){//新用户注册
            user = new User();
            user.setPhone(phone);
            user.setAppkey(appkey);
            user.setUserId(CommonUtil.generageShortUuid());
            user.setNickname("user"+CommonUtil.randomInt());
            user.setAppkey(appkey);
            userMapper.upsert(user);
            // user configure
            userExtMapper.upsert(new UserExt(appkey,user.getUserId(),UserExtKeys.UserExtKey_FriendVerifyType,CommonUtil.int2String(UserExtKeys.FriendVerifyType_Need),UserExtKeys.AttItemType_Setting));
            //send welcome message  TODO
        }

        LoginUserResp resp = new LoginUserResp();
        resp.setUserId(user.getUserId());
        resp.setNickname(user.getNickname());
        resp.setAuthorization(this.generateAuthToken(appkey, user.getUserId()));

        //imsdk
        JuggleIm imSdk = ImSdkService.getJimSdk(appkey);
        if(imSdk==null){
            throw new JimException(JimErrorCode.ErrorCode_APP_NOT_EXISTED);
        }
        try {
            UserTokenResult result = imSdk.user.register(new UserInfo(user.getUserId(), user.getNickname(), user.getUserPortrait()));
            resp.setImToken(result.getUserToken().getToken());
        } catch (Exception e) {
            throw new JimException(JimErrorCode.ErrorCode_APP_INTERNAL_TIMEOUT);
        }
        return resp;
    }
    
    public void smsSend(String phone)throws JimException{
        
    }

    public void checkPhoneSmsCode(String phone, String code)throws JimException{
        if(!code.equals("000000")){
            throw new JimException(JimErrorCode.ErrorCode_APP_SMS_CODE_EXPIRED, "");
        }
    }

    public String generateAuthToken(String appkey, String userId)throws JimException{
        AuthToken aToken = new AuthToken(appkey, userId, "", System.currentTimeMillis());
        AppInfo appInfo = AppInfoCache.getAppInfo(appkey);
        if(appInfo!=null){
            try {
                String tokenStr = AuthTokenService.toTokenString(aToken, appInfo.getAppSecureKey());
                return tokenStr;
            } catch (Exception e) {
                e.printStackTrace();
                throw new JimException(JimErrorCode.ErrorCode_APP_INTERNAL_TIMEOUT);
            }
        }
        throw new JimException(JimErrorCode.ErrorCode_APP_NOT_EXISTED);
    }
}
