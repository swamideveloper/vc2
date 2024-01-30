package com.ads.sdk.notify;

import static com.ads.sdk.new_configs.Data_Preference.notificationDatumList;
import static com.ads.sdk.notify.Live_Msg_Service.notify_Pos;

import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;

import com.ads.sdk.R;

public class CustomTabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_tab);

        String url = notificationDatumList.get(notify_Pos).getNotifyLink();
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(url));
        finish();
    }
}