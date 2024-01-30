package com.ads.sdk.notify;


import static com.ads.sdk.new_configs.Data_Preference.Glob_Notification_Minutes;
import static com.ads.sdk.new_configs.Data_Preference.notificationDatumList;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.ads.sdk.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

public class Live_Msg_Service extends Service {

    public Handler handler;
    public static boolean IS_ACTIVITY_RUNNING = false;
    public static Random r;
    public static int notify_Pos = 0;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        IS_ACTIVITY_RUNNING = true;

    }


    @Override
    public int onStartCommand(Intent intent, int i, int i2) {

        if (Build.VERSION.SDK_INT >= 26) {
            startMyOwnForeground();
        } else {
            startForeground(1, new Notification());
        }


        fun();

        return Service.START_NOT_STICKY;
    }

    private void startMyOwnForeground() {
        NotificationChannel notificationChannel = new NotificationChannel("mainNot", "Background Service", 0);
        notificationChannel.setLightColor(-16776961);
        notificationChannel.setLockscreenVisibility(0);
        ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(notificationChannel);
        startForeground(2, new NotificationCompat.Builder(this, "mainNot").setOngoing(false).setSmallIcon(R.drawable.ic_notification).setContentTitle("This app is running in background").setPriority(1).setCategory(NotificationCompat.CATEGORY_SERVICE).build());
    }


    @Override
    public void onDestroy() {
        IS_ACTIVITY_RUNNING = false;
        handler.removeCallbacks(null);

        stopForeground(true);

        super.onDestroy();
    }


    public void fun() {
        handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                notify_Pos = getRandomNum();
                new generatePictureStyleNotification(getApplicationContext(), notificationDatumList.get(notify_Pos).getNotifyTitle(), notificationDatumList.get(notify_Pos).getNotifySubtitle(), notificationDatumList.get(notify_Pos).getNotifyImageLink()).execute();
                //todo: instant time check (10 seconds)
                handler.postDelayed(this, (Integer.parseInt(Glob_Notification_Minutes) * 1000 * 60));
//                handler.postDelayed(this, (10 * 1000));

            }
        });
    }

    public class generatePictureStyleNotification extends AsyncTask<String, Void, Bitmap> {

        private Context mContext;
        private String title, message, imageUrl;

        public generatePictureStyleNotification(Context context, String title, String message, String imageUrl) {
            super();
            this.mContext = context;
            this.title = title;
            this.message = message;
            this.imageUrl = imageUrl;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            InputStream in;
            try {
                URL url = new URL(this.imageUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                in = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(in);
                return myBitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);

            int reqCode = 55;
            Intent notificationIntent;

            if (notificationDatumList.get(notify_Pos).getNotifyLink().contains("play.google.com")) {
                String mURL = notificationDatumList.get(notify_Pos).getNotifyLink();
                String[] mArray = mURL.split("=");
                notificationIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + mArray[1]));
            } else {
                notificationIntent = new Intent(getApplicationContext(), CustomTabActivity.class);
            }
            PendingIntent intent = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);
            String CHANNEL_ID = "channel_name";// The id of the channel.
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_notification)
                    .setLargeIcon(result)
                    .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(result))
                    .setContentTitle(title)
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setSilent(false)
                    .setContentIntent(intent);
            NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                CharSequence name = "Channel Name";
                int importance = NotificationManager.IMPORTANCE_HIGH;
                NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
                notificationManager.createNotificationChannel(mChannel);
            }
            notificationManager.notify(reqCode, notificationBuilder.build());
//            Log.e("@@notify", "notify fire---");
        }
    }


    public static int getRandomNum() {
        int size = notificationDatumList.size();
        int min = 0;
        int max = size - 1;
        r = new Random();
        int output = r.nextInt((max - min) + 1) + min;
        return output;
    }
}