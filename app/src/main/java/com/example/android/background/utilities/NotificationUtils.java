package com.example.android.background.utilities;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.drm.DrmStore;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;

import com.example.android.background.MainActivity;
import com.example.android.background.R;
import com.example.android.background.sync.ReminderTasks;
import com.example.android.background.sync.WaterReminderIntentService;

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
                .addAction(drinkWaterAction(context))
                .addAction(ignoreReminderAction(context))
                .setAutoCancel(true);

        if (Build.VERSION.SDK_INT >=  Build.VERSION_CODES.JELLY_BEAN){
            notificationBulider.setPriority(Notification.PRIORITY_HIGH);
        }

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(WATER_REMINDER_NOTIFICATION_ID,notificationBulider.build());


    }

    private static NotificationCompat.Action ignoreReminderAction(Context context){

        Intent ignoreReminderIntent = new Intent(context, WaterReminderIntentService.class);

        ignoreReminderIntent.setAction(ReminderTasks.ACTION_DISMISS_NOTIFICATION);

        PendingIntent ignoreReminderPendingIntent = PendingIntent.getService(
                context,
                ACTION_IGNORE_PENDING_INTENT_ID,
                ignoreReminderIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        NotificationCompat.Action ignoreReminderAction = new NotificationCompat.Action(R.drawable.ic_cancel_black_24px
        ,"No, Thanks",
                ignoreReminderPendingIntent)
                ;
        return ignoreReminderAction;

    }

    private static NotificationCompat.Action drinkWaterAction(Context context){

        Intent incrementWaterCountIntent = new Intent(context, WaterReminderIntentService.class);

        incrementWaterCountIntent.setAction(ReminderTasks.ACTION_INCREMENT_WATER_COUNT);

        PendingIntent incremenrWaterPendingIntent = PendingIntent.getService(
                context,
                ACTION_DRINK_PENDING_INTENT_ID,
                incrementWaterCountIntent,
                PendingIntent.FLAG_CANCEL_CURRENT
        );
        NotificationCompat.Action drinkWaterAction = new NotificationCompat.Action(R.drawable.ic_local_drink_black_24px,
                "I did it!",
                incremenrWaterPendingIntent);

        return drinkWaterAction;

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
