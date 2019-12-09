package com.shouhulife.utilslibrary.util.safe;


import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * DES加密解密（对称加密）
 * 作者：hong on 2017/2/20 15:16
 * 邮箱：xhdshz@foxmail.com
 */
public class DES {
    /**
     * des加密
     * 2014-12-26
     * @param encryptString
     * @param encryptKey
     * @return
     * @throws Exception
     */
    public static String encryptDES(String encryptString, String encryptKey, String iv) throws Exception {
        IvParameterSpec zeroIv;
        SecretKeySpec key;
        if(iv!=null){
            zeroIv = new IvParameterSpec(Base64.decode(iv));//iv
            key = new SecretKeySpec(Base64.decode(encryptKey), "DES");
        }else{
            zeroIv = new IvParameterSpec(encryptKey.getBytes());//iv
            key = new SecretKeySpec(encryptKey.getBytes(), "DES");
        }
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
        byte[] encryptedData = cipher.doFinal(encryptString.getBytes());

        return Base64.encode(encryptedData);
    }
    @SuppressWarnings("static-access")
    /**
     * des解密
     * 2014-12-26
     * @param decryptString
     * @param decryptKey
     * @return
     * @throws Exception
     */
    public static String decryptDES(String decryptString, String decryptKey, String iv) throws Exception {
        byte[] byteMi = new Base64().decode(decryptString);
        IvParameterSpec zeroIv;
        SecretKeySpec key;
        if(iv!=null){
            zeroIv = new IvParameterSpec(Base64.decode(iv));//iv
            key = new SecretKeySpec(Base64.decode(decryptKey), "DES");
        }else{
            zeroIv = new IvParameterSpec(decryptKey.getBytes());//iv
            key = new SecretKeySpec(decryptKey.getBytes(), "DES");
        }
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
        byte decryptedData[] = cipher.doFinal(byteMi);

        return new String(decryptedData);
    }
}
