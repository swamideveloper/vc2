package com.videocall.globalcall.livecallwithfun;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.videocall.globalcall.livecallwithfun.surmesham.pp_show;

public class VC_Terms_Activity extends AppCompatActivity {
    private TextView tv_policy1, tv_policy2, tv_policy3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

        tv_policy1 = findViewById(R.id.tv_policy1);
        tv_policy2 = findViewById(R.id.tv_policy2);
        tv_policy3 = findViewById(R.id.tv_policy3);

        String str1 = "Following link :" + " <a href=\"\"> <b> Terms and conditions of use </b> </a> ";
        tv_policy1.setText(Html.fromHtml(str1));
        tv_policy1.setLinkTextColor(getResources().getColor(R.color.blue_lnk));
        tv_policy1.setMovementMethod(LinkMovementMethod.getInstance());

        String str2 = "Following link :" + " <a href=\"\"> <b> Privacy Policy </b> </a> ";
        tv_policy2.setText(Html.fromHtml(str2));
        tv_policy2.setLinkTextColor(getResources().getColor(R.color.blue_lnk));
        tv_policy2.setMovementMethod(LinkMovementMethod.getInstance());

        String str3 = "Following link :" + " <a href=\"\"> <b> App Community Guidelines </b> </a> ";
        tv_policy3.setText(Html.fromHtml(str3));
        tv_policy3.setLinkTextColor(getResources().getColor(R.color.blue_lnk));
        tv_policy3.setMovementMethod(LinkMovementMethod.getInstance());

        tv_policy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VC_Terms_Activity.this, pp_show.class);
                intent.putExtra("link", 1);
                startActivity(intent);
            }
        });

        tv_policy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VC_Terms_Activity.this, pp_show.class);
                intent.putExtra("link", 2);
                startActivity(intent);

            }
        });

        tv_policy3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VC_Terms_Activity.this, pp_show.class);
                intent.putExtra("link", 3);
                startActivity(intent);
            }
        });

        findViewById(R.id.tvAccept).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VC_Terms_Activity.this, VC_Terms_Activity_2.class).putExtra("issplash", 2));
                finish();
            }
        });

        findViewById(R.id.tvReject).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
    }
}