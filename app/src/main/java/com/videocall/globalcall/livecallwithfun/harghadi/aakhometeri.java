package com.videocall.globalcall.livecallwithfun.harghadi;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import androidx.browser.customtabs.CustomTabsIntent;

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
    public static Boolean DEBUG = false;

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

    public static String apiHost(Context applicationContext, String packageName) {
        try {
            return decrypt("+CSHLKtTMsKbcK9qOvqble1aRlClp9tWnGTpH66GAG8=", applicationContext.getResources().getString(R.string.pass));
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

    public static String buildMode(Boolean debug) {
        return debug ? "debug" : "release";
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    public static void log(String key, String value) {
        if (DEBUG) {
        }
    }

    public static void rateUs(Activity activity) {
        try {
            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + activity.getApplicationContext().getPackageName())));
        } catch (ActivityNotFoundException e) {
            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + activity.getApplicationContext().getPackageName())));
        }
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

    public static boolean isAppInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getApplicationInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static String getPlayStoreUrl(String app_packageName) {
        return app_packageName;
    }

    public static void openChromeCustomTabUrl(final Context context, String webUrl) {
        try {
            if (isAppInstalled(context, "com.android.chrome")) {
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                int coolorInt = Color.parseColor("#66bb6a");
                builder.setToolbarColor(coolorInt);
                builder.setStartAnimations(context, R.anim.sdk_slide_in_right, R.anim.sdk_slide_out_left);
                builder.setExitAnimations(context, R.anim.sdk_slide_in_left, R.anim.sdk_slide_out_right);
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.intent.setPackage("com.android.chrome");
                customTabsIntent.intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                customTabsIntent.launchUrl(context, Uri.parse(webUrl));
            } else {
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                int coolorInt = Color.parseColor("#66bb6a");
                builder.setToolbarColor(coolorInt);
                builder.setStartAnimations(context, R.anim.sdk_slide_in_right, R.anim.sdk_slide_out_left);
                builder.setExitAnimations(context, R.anim.sdk_slide_in_left, R.anim.sdk_slide_out_right);
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                customTabsIntent.launchUrl(context, Uri.parse(webUrl));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
