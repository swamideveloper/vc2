package com.videocall.globalcall.livecallwithfun.harghadi;

import android.app.Activity;

import com.ads.sdk.R;

import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class aakhometeri {
    public static String baseURL(Activity applicationContext, String packageName) {
        try {
            return decrypt("2F3802D5BB3A4C24F824872CAB5332C29B70AF6A3AFA9B95ED5A4650A5A7DB5626E6EC9FDE64AE4D61D72FB9D59EC166EC3CFB42DEC30ECB52A250F9DE38E328", applicationContext.getResources().getString(R.string.pass));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return packageName;
    }


    public static String decrypt(String encryptedtext, String key)
            throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException {
        byte[] KeyData = key.getBytes();
        SecretKeySpec KS = new SecretKeySpec(KeyData, "Blowfish");
        byte[] ecryptedtexttobytes = android.util.Base64.
                decode(encryptedtext, android.util.Base64.DEFAULT);
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.DECRYPT_MODE, KS);

        byte[] decrypted = cipher.doFinal(hexToBytes(encryptedtext));
        String decryptedString =
                new String(decrypted, Charset.forName("UTF-8"));

        return decryptedString;

    }

    public static byte[] hexToBytes(String str) {
        if (str == null) {
            return null;
        } else if (str.length() < 2) {
            return null;
        } else {
            int len = str.length() / 2;
            byte[] buffer = new byte[len];
            for (int i = 0; i < len; i++) {
                buffer[i] = (byte) Integer.parseInt(str.substring(i * 2, i * 2 + 2), 16);
            }
            return buffer;
        }

    }

}
