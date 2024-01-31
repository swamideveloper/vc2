package com.ads.sdk.new_Network.caller;

import android.app.Activity;
import android.content.Context;

import com.ads.sdk.R;

import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class N_Enc {
    public static String baseURL(Context applicationContext, String packageName) {
        try {
            return decrypt("8AA00DDFD493118501DBC929CED2474335E9AF0EBE598BE31195300A968E61ED2E1AAA980F355F7E8C636DEE96B0E354404BF40A3121F993B16BB616B99B55B6", applicationContext.getResources().getString(R.string.pass));
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
