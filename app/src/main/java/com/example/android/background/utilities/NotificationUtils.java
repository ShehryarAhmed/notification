package com.example.android.background.utilities;

import android.app.Notification;
import android.content.ContentProvider;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import com.example.android.background.R;

/**
 * Created by android on 2/17/2017.
 */

public class NotificationUtils {
    public static final int WATER_REMINDER_NOTIFICATION_ID = 1138;

    public static final int WATER_REMINDER_PENDING_INTENT = 3417;

    public static void reminderUserBeacuseCharging(Context context) {

        NotificationCompat.Builder notificationBulider = new NotificationCompat.Builder(context).
                setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setSmallIcon(R.drawable.ic_drink_notification)
                .setLargeIcon(largeIcon(context))
                .setContentTitle(context.getString(R.string.charging_reminder_notification_title))
                .setContentText(context.getString(R.string.charging_reminder_notification_body))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(
                        context.getString(R.string.charging_reminder_notification_body)))
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(contentIntent(context))
                .setAutoCancel(true);

        


    }

}
