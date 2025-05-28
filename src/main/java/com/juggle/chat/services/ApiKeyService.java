package com.juggle.chat.services;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.google.protobuf.InvalidProtocolBufferException;
import com.juggle.chat.utils.AESUtil;

public class ApiKeyService {
    public static boolean checkApiKey(String apiKey, String appkey, String secureKey) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidProtocolBufferException{
        byte[] bs = Base64.getUrlDecoder().decode(apiKey);
        byte[] decodedBs = AESUtil.aesDecrypt(bs, secureKey.getBytes(StandardCharsets.UTF_8));
        Appbus.ApiKey apikey = Appbus.ApiKey.parseFrom(decodedBs);
        if(!apikey.getAppkey().equals(appkey)){
            return false;
        }
        return true;
    }

    public static String generateApiKey(String appkey, String secureKey) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
        Appbus.ApiKey apikey = Appbus.ApiKey.newBuilder().setAppkey(appkey).setCreatedTime(System.currentTimeMillis()).build();
        byte[] bs = apikey.toByteArray();
        byte[] encodedBs = AESUtil.aesEncrypt(bs, secureKey.getBytes(StandardCharsets.UTF_8));
        return Base64.getUrlEncoder().encodeToString(encodedBs);
    }
}
