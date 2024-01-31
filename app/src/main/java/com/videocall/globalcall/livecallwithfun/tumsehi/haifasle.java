package com.videocall.globalcall.livecallwithfun.tumsehi;

import static com.videocall.globalcall.livecallwithfun.shyame.aakhometeri.native_ShowAds;
import static com.videocall.globalcall.livecallwithfun.surmesham.tumpass.connectInComing;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.ads.sdk.IntertitialAdsEvent;
import com.ads.sdk.new_configs.Data_Preference;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.videocall.globalcall.livecallwithfun.R;
import com.videocall.globalcall.livecallwithfun.VectorManager;
import com.videocall.globalcall.livecallwithfun.harghadi.teraname;
import com.videocall.globalcall.livecallwithfun.surmesham.hokebhi;

import java.util.List;


public class haifasle extends AppCompatActivity {

    public static boolean isVectorShow = true;
    private AppCompatButton welcome;

    //incoming call
    public static int incoming_counter = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        
        VectorManager.changeVectorStatus(this);

        if (new hokebhi().isTerms() && new Data_Preference(haifasle.this).getKeyTermscreen().equals("1")) {
            new hokebhi().stopTerms();
            IntertitialAdsEvent.ShowInterstitialAdsOnCreate(this);
        }


        if (new Data_Preference(this).getKeyOnresumenativeshow().equals("0")) {
            native_ShowAds(this, findViewById(R.id.native_container), 1);
        }


        welcome = findViewById(R.id.welcome);


        welcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPR();
            }
        });

    }

    private void goNextScreen() {
        if (new Data_Preference(haifasle.this).getIncoming_Welcome().equalsIgnoreCase("1") && incoming_counter == -1) {
            connectInComing(haifasle.this);
        } else {
            if (new Data_Preference(haifasle.this).getIncoming_Counter().equalsIgnoreCase(String.valueOf(incoming_counter))) {
                connectInComing(haifasle.this);
            } else {
                if (new Data_Preference(haifasle.this).getIsLongApp().equalsIgnoreCase("1")) {
                    startActivity(new Intent(haifasle.this, najanajane.class));
                } else {
                    startActivity(new Intent(haifasle.this, teraname.class));
                }
            }
        }
    }


    @Override
    public void onBackPressed() {
        if (new Data_Preference(this).getKeyExitscreen().equals("1")) {
            startActivity(new Intent(haifasle.this, tujanena.class).putExtra("issplash", 1));
        } else {
            tagAgainToExit();
        }
    }


    boolean doubleBackToExitPressedOnce = false;

    private void tagAgainToExit() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }


    private void requestPR() {

        Dexter.withActivity(haifasle.this).withPermissions(Manifest.permission.CAMERA).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {
                if (report.areAllPermissionsGranted()) {
                    goNextScreen();
                } else if (report.isAnyPermissionPermanentlyDenied()) {
                    showSettingsDialog();
                }
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List permissions, PermissionToken token) {
                token.continuePermissionRequest();
            }
        }).withErrorListener(new PermissionRequestErrorListener() {
            @Override
            public void onError(DexterError error) {
                Toast.makeText(getApplicationContext(), "Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }).onSameThread().check();

    }

    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(haifasle.this, R.style.Theme_Dllg);
        builder.setTitle("Need Permissions");
        builder.setMessage("This App needs CAMERA Permission to Use Video Call Feature. You can Grant them in App NetSettings.");
        builder.setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                openSettings();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }
}