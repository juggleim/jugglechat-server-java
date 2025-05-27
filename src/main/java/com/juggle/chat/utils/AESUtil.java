package com.juggle.chat.utils;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {

    public static byte[] aesEncrypt(byte[] data, byte[] key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
        SecureRandom secureRandom = new SecureRandom();
        byte[] iv = new byte[16]; // AES block size is 16 bytes
        secureRandom.nextBytes(iv);

        //创建 Cipher
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key,"AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

        //加密
        byte[] encryptedBytes = cipher.doFinal(data);

        //拼接 iv 和 密文
        byte[] combined = new byte[iv.length + encryptedBytes.length];
        System.arraycopy(iv,0,combined, 0, iv.length);
        System.arraycopy(encryptedBytes, 0, combined, iv.length, encryptedBytes.length);
        return combined;
    }

    public static byte[] aesDecrypt(byte[] data, byte[] key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
        //分离 IV 和 密文
        byte[] iv = Arrays.copyOfRange(data, 0, 16);
        byte[] encryptedBs = Arrays.copyOfRange(data, 16, data.length);

        //创建 Cipher
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

        //解密
        byte[] decryptedBytes = cipher.doFinal(encryptedBs);
        return decryptedBytes;
    }
}
