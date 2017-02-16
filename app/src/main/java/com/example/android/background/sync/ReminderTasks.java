package com.example.android.background.sync;

// TODO (1) Create a class called ReminderTasks

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.preference.Preference;
import android.widget.Toast;

import com.example.android.background.utilities.PreferenceUtilities;

public class ReminderTasks {

// TODO (2) Create a public static constant String called ACTION_INCREMENT_WATER_COUNT

    public static final String ACTION_INCREMENT_WATER_COUNT = "increment-water-count";

    public static void executeTask(Context context, String action) {
        if(ACTION_INCREMENT_WATER_COUNT.equals(action)){

            incrementWaterCount(context);

        }
    }

    public static void incrementWaterCount(Context context) {
        PreferenceUtilities.incrementWaterCount(context);

    }


// TODO (6) Create a public static void method called executeTask
// TODO (7) Add a Context called context and String parameter called action to the parameter list

// TODO (8) If the action equals ACTION_INCREMENT_WATER_COUNT, call this class's incrementWaterCount

// TODO (3) Create a private static void method called incrementWaterCount
// TODO (4) Add a Context called context to the argument list
// TODO (5) From incrementWaterCount, call the PreferenceUtility method that will ultimately update the water count

}