package keeplive.je.com.keeplivesupportlibrary;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

public class NotifyJavaService extends Service {
    public static final int NOTIFICATION_ID = 0x11;

    public NotifyJavaService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //API 18以上，发送Notification并将其置为前台后，启动InnerService
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String notify_ch = "123";
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), notify_ch);
            NotificationChannel channel = new NotificationChannel(notify_ch, "test des", NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableLights(false); //是否在桌面icon右上角展示小红点
            notificationManager.createNotificationChannel(channel);
            Notification notification = mBuilder.build();
            startForeground(NOTIFICATION_ID, notification);
        } else {
            Notification.Builder builder = new Notification.Builder(this);
            builder.setSmallIcon(R.mipmap.ic_launcher);
            startForeground(NOTIFICATION_ID, builder.build());
        }
        startService(new Intent(this, InnerService.class));

    }

    public static class InnerService extends Service {
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public void onCreate() {
            super.onCreate();
            //发送与KeepLiveService中ID相同的Notification，然后将其取消并取消自己的前台显示
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                String notify_ch = "123";
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), notify_ch);
                NotificationChannel channel = new NotificationChannel(notify_ch, "test des", NotificationManager.IMPORTANCE_DEFAULT);
                channel.enableLights(false); //是否在桌面icon右上角展示小红点
                notificationManager.createNotificationChannel(channel);
                Notification notification = mBuilder.build();
                startForeground(NOTIFICATION_ID, notification);
            } else {
                Notification.Builder builder = new Notification.Builder(this);
                builder.setSmallIcon(R.mipmap.ic_launcher);
                startForeground(NOTIFICATION_ID, builder.build());
            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    stopForeground(true);
                    NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    manager.cancel(NOTIFICATION_ID);
                    stopSelf();
                }
            }, 100);

        }
    }
}
