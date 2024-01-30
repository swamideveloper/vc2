package com.videocall.globalcall.livecallwithfun.harghadi;

import static com.videocall.globalcall.livecallwithfun.tumsehi.haifasle.incoming_counter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ads.sdk.new_configs.Data_Preference;
import com.videocall.globalcall.livecallwithfun.R;
import com.videocall.globalcall.livecallwithfun.khwab.MainActivity;
import com.videocall.globalcall.livecallwithfun.surmesham.hokebhi;

import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class musiconly extends AppCompatActivity {

    Random random = new Random();

    String[] randName = {"Aarushi", "Aditi", "Aishwarya", "Arundhati", "Arti", "Arpita", "Apoorva", "Aparna", "Anushree", "Anuradha", "Ankita", "Akhila", "Ambika", "Amita", "Amrita", "Ananya", "Anasuya", "Angelika", "Anjali", "Binita", "Bipasha", "Damayanti", "Deepa", "Deepali", "Deepika", "Deepti", "Devanshi", "Disha", "Dhanishka", "Eva", "Gayatri", "Gita", "Harshita", "Hemalata", "Himashree", "Hira", "Indira", "Indumati", "Joshna", "Juhi", "Kanchana", "Kanika", "Kareena", "Karthika", "Kavita", "Keerthi", "Kirtan", "Kumudini", "Lakshanya", "Lakshmi", "Lata", "Leela", "Leila", "Madhumita", "Madhuri", "Mallika", "Maneet", "Manjula", "Manorama", "Maya", "Meghana", "Meghna", "MiraMira", "Mohini", "Munira", "Nadia", "Nahla", "Naila", "Namrata", "Nandita", "Neelam", "Neetu", "Neha", "Niharika", "Neera", "Nikita", "Nisha", "Nimisha", "Nupur", "Padmavati", "Pallavi", "Parvati", "Phanita", "Poonam", "Pratibha", "Pratiksha", "Preeti", "Priyanka", "Priya", "Poonam", "Rajadhi", "Ranjan", "Ramya", "Rani", "Rashmi", "Rashmika", "Rekha", "Richa", "Ridhima", "Ritwika", "Roopa", "Riddhi", "Saloni", "Sanah", "Sandhya", "Sania", "Swathi", "Susmita", "Sushma", "Sulochana", "Sujata", "Sudha", "Sreelekha", "Sonali", "Sneha", "Simran", "Shweta", "Shreya", "Shilpa", "Sheetal", "Sheela", "Sharmila", "Savitri", "Satyana", "Sarita", "Saranya", "Sanjana", "Tanisha", "Tanushree", "Tanvi", "Tejal", "Tejaswi", "Tina", "Tisha", "Uma", "Urvisha", "Vani", "Varsha", "Vibha", "Vibhuti", "Vanshika", "Vishwa", "Vijaya Lakshmi", "Yamini", "Yashomati", "Yatri",};

    int[] randImages = {R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a5, R.drawable.a6, R.drawable.a7, R.drawable.a8, R.drawable.a10, R.drawable.a11, R.drawable.a12, R.drawable.a13, R.drawable.a15, R.drawable.a17, R.drawable.a18, R.drawable.a19, R.drawable.a20, R.drawable.a20, R.drawable.a23, R.drawable.a24, R.drawable.a25, R.drawable.a26, R.drawable.a28, R.drawable.a30, R.drawable.a31, R.drawable.a32, R.drawable.a34, R.drawable.a35, R.drawable.a38, R.drawable.a39, R.drawable.a41, R.drawable.a43, R.drawable.a44, R.drawable.a45, R.drawable.a48, R.drawable.a50, R.drawable.a52, R.drawable.a53, R.drawable.a56, R.drawable.a57, R.drawable.a59, R.drawable.a60, R.drawable.a61, R.drawable.a63, R.drawable.a64, R.drawable.a65, R.drawable.a66, R.drawable.a67, R.drawable.a69, R.drawable.a70, R.drawable.a71, R.drawable.a72, R.drawable.a73, R.drawable.a74, R.drawable.a75, R.drawable.a77, R.drawable.a78, R.drawable.a79, R.drawable.a80, R.drawable.a81, R.drawable.a83, R.drawable.a84, R.drawable.a88, R.drawable.a89, R.drawable.a92, R.drawable.a93, R.drawable.a94, R.drawable.a95, R.drawable.a96, R.drawable.a97, R.drawable.a99, R.drawable.a101, R.drawable.a103, R.drawable.a104, R.drawable.a105, R.drawable.a106, R.drawable.a111, R.drawable.a113, R.drawable.a114, R.drawable.a116, R.drawable.a119, R.drawable.a121, R.drawable.a122, R.drawable.a123, R.drawable.a124, R.drawable.a125, R.drawable.a126, R.drawable.a127, R.drawable.a128, R.drawable.a129,};

    private LinearLayout ll_call_accept;
    private LinearLayout ll_call_hangUp;

    private ImageView profile;
    private CircleImageView vc_profile;
    private TextView vc_name;

    private MediaPlayer mp, call_cut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.incoming_call);

        incoming_counter = 0;

        try {
            mp = MediaPlayer.create(this, R.raw.ahh);
            call_cut = MediaPlayer.create(this, R.raw.call_cut);
            mp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ll_call_accept = findViewById(R.id.ll_call_accept);
        ll_call_hangUp = findViewById(R.id.ll_call_hangUp);
        profile = findViewById(R.id.profile);
        vc_profile = findViewById(R.id.vc_profile);
        vc_name = findViewById(R.id.vc_name);

        //Random Images...
        try {
            vc_profile.setImageResource(randImages[random.nextInt(randImages.length)]);
        } catch (Exception e) {
            Toast.makeText(this, "Your device processor is very slow!", Toast.LENGTH_SHORT).show();
        }
        vc_profile.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Bitmap image = ((BitmapDrawable) vc_profile.getDrawable()).getBitmap();
        profile.setImageBitmap(image);
        profile.setScaleType(ImageView.ScaleType.CENTER_CROP);

        //Random Nmae...
        int iName = new Random().nextInt(randName.length);
        String randomName = randName[iName];
        vc_name.setText(randomName);

        ll_call_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (new Data_Preference(musiconly.this).getKeyInAppMode().equals("1")) {
                    if (new hokebhi().getVipType().equals("in_app_gold") || new hokebhi().getVipType().equals("in_app_silver") || new hokebhi().getVipType().equals("in_app_bronze")) {
                        connectVideoCall();
                    } else {
                        if (new hokebhi().isFreeIncomingActive()) {
                            new hokebhi().setFreeIncoming(false);
                            connectVideoCall();
                        } else {
                            startActivity(new Intent(musiconly.this, MainActivity.class));
                            finish();
                        }
                    }
                } else {
                    connectVideoCall();
                }
            }
        });

        ll_call_hangUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mp.stop();
                } catch (Exception e) {
                }
                onBackPressed();
                finish();
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    mp.stop();
                } catch (Exception e) {
                }
                finish();
            }
        }, 12000);

    }

    private void connectVideoCall() {
        try {
            mp.stop();
        } catch (Exception e) {
        }
        startActivity(new Intent(musiconly.this, kyuhona.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        try {
            mp.stop();
            call_cut.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onBackPressed();
    }


    @Override
    protected void onPause() {
        try {
            mp.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onPause();
    }
}