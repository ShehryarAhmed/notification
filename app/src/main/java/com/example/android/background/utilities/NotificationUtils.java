package com.example.android.background.utilities;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;

import com.example.android.background.MainActivity;
import com.example.android.background.R;

/**
 * Created by android on 2/17/2017.
 */

public class NotificationUtils {
    private static final int WATER_REMINDER_NOTIFICATION_ID = 1138;

    private static final int WATER_REMINDER_PENDING_INTENT = 3417;

    private static final int ACTION_DRINK_PENDING_INTENT_ID = 1;

    public static final int ACTION_IGNORE_PENDING_INTENT_ID = 14;

    public static void clearAllNotifications(Context context) {

NotificationManager notificationManager = (NotificationManager)
        context.getSystemService(context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
    }


    public static void reminderUserBeacuseCharging(Context context) {

        NotificationCompat.Builder notificationBulider = new NotificationCompat.Builder(context).
                setColor(ContextCompat.getColor(context, R.color.colorAccent))
                .setSmallIcon(R.drawable.ic_drink_notification)
                .setLargeIcon(largeIcon(context))
                .setContentTitle(context.getString(R.string.charging_reminder_notification_title))
                .setContentText(context.getString(R.string.charging_reminder_notification_body))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(
                        context.getString(R.string.charging_reminder_notification_body)))
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(contentIntent(context))
                .setAutoCancel(true);

        if (Build.VERSION.SDK_INT >=  Build.VERSION_CODES.JELLY_BEAN){
            notificationBulider.setPriority(Notification.PRIORITY_HIGH);
        }

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(WATER_REMINDER_NOTIFICATION_ID,notificationBulider.build());


    }

    private static PendingIntent contentIntent(Context context){

        Intent startActivityIntent = new Intent(context, MainActivity.class);

        return PendingIntent.getActivity(
                context,
                WATER_REMINDER_NOTIFICATION_ID,
                startActivityIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private static Bitmap largeIcon(Context context){
        Resources res = context.getResources();

        Bitmap largeIcon  = BitmapFactory.decodeResource(res,R.drawable.ic_local_drink_black_24px);

        return largeIcon;
    }

}
