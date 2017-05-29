package com.easydevs.auth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by arekotto on 27/12/2016.
 */
public class Encryptor {

    private MessageDigest md;

    /**
     * Encrypt with md 5 string.
     *
     * @param string the string
     * @return the string
     */
    public String encryptWithMD5(String string){
        try {
            md = MessageDigest.getInstance("MD5");
        }catch (NoSuchAlgorithmException ex) {
            return null;
        }
        byte[] passBytes = string.getBytes();
        md.reset();
        byte[] digested = md.digest(passBytes);
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<digested.length;i++){
            sb.append(Integer.toHexString(0xff & digested[i]));
        }
        return sb.toString();
    }
}


