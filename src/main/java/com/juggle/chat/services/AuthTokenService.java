package com.juggle.chat.services;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import com.google.protobuf.ByteString;
import com.juggle.chat.models.AuthToken;
import com.juggle.chat.services.Appbus.AuthTokenValue;
import com.juggle.chat.utils.AESUtil;

public class AuthTokenService {
    public static String toTokenString(AuthToken token, String secureKey)throws Exception{
        AuthTokenValue tokenValue = AuthTokenValue.newBuilder()
                .setUserId(token.getUserId()==null?"":token.getUserId())
                .setDeviceId(token.getDeviceId()==null?"":token.getDeviceId())
                .setTokenTime(token.getTokenTime())
                .build();
        byte[] tokenBs = tokenValue.toByteArray();
        
        byte[] encryptToken = AESUtil.aesEncrypt(tokenBs, secureKey.getBytes(StandardCharsets.UTF_8));
        com.juggle.chat.services.Appbus.AuthToken authToken = com.juggle.chat.services.Appbus.AuthToken.newBuilder()
                .setAppkey(token.getAppkey())
                .setTokenValue(ByteString.copyFrom(encryptToken)).build();
        byte[] bs = authToken.toByteArray();
        return Base64.getUrlEncoder().encodeToString(bs);
    }

    public static AuthToken parseAuthToken(String tokenStr,String secureKey)throws Exception{
        byte[] authTokenBs = Base64.getUrlDecoder().decode(tokenStr);
        com.juggle.chat.services.Appbus.AuthToken authTokenProto = com.juggle.chat.services.Appbus.AuthToken.parseFrom(authTokenBs);
        String appkey = authTokenProto.getAppkey();
        byte[] encryptedBs = authTokenProto.getTokenValue().toByteArray();
        byte[] tokenBs = AESUtil.aesDecrypt(encryptedBs, secureKey.getBytes(StandardCharsets.UTF_8));
        AuthTokenValue tokenValue = AuthTokenValue.parseFrom(tokenBs);
        if (tokenValue==null||tokenValue.getUserId()==null||tokenValue.getUserId().isEmpty()){
            throw new IllegalArgumentException("Invalid token format or missing userId");
        }
        
        AuthToken authToken = new AuthToken(appkey,tokenValue.getUserId(),tokenValue.getDeviceId(),tokenValue.getTokenTime());
        return authToken;
    }
}
