package com.flipgrid.assignment.flipgridsignup.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.UUID;

public class AppContext extends Application implements Application.ActivityLifecycleCallbacks {

    private PreferenceWrapper preferenceWrapper;
    private AppLogger logger;
    private int activityCounter = 0;
    private Activity currentActivity;
    private String sessionId;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public static AppContext getInstance(Context context) {
        return (AppContext) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        preferenceWrapper = new PreferenceWrapper(this);

        initializeLogger();
        initializeDataStore();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {

    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        if (activityCounter == 0) {
            sessionId = UUID.randomUUID().toString();
            logger.LogLifecycle(new HashMap<String, String>(){
                { put("LifecycleStatus","APP_ACTIVE"); }
            });
        }

        this.currentActivity = activity;
        activityCounter++;
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {

    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {
        activityCounter--;
        if (activityCounter == 0) {
            logger.LogLifecycle(new HashMap<String, String>(){
                { put("LifecycleStatus","APP_INACTIVE"); }
            });
        }
        if (this.currentActivity == activity) {
            this.currentActivity = null;
        }
    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {

    }

    public AppLogger getLogger() {
        return logger;
    }

    public PreferenceWrapper getPreferenceWrapper() {
        return preferenceWrapper;
    }

    public String getSessionId() {
        return sessionId;
    }

    private void initializeLogger() {
        logger = new AppLogger(this);
        registerActivityLifecycleCallbacks(this);

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(@NonNull Thread thread, @NonNull Throwable throwable) {
                logger.LogError(new HashMap<String, String>() {
                    { put("Exception", throwable.getClass().getSimpleName()); }
                    { put("Message", throwable.getMessage()); }
                });
            }
        });

    }

    private void initializeDataStore() {
        preferenceWrapper.writeString(DataKey.DEVICE_NAME.name(), Build.MODEL);
        preferenceWrapper.writeInt(DataKey.ANDROID_VERSION.name(), Build.VERSION.SDK_INT);
        preferenceWrapper.writeString(DataKey.DEVICE_ID.name(), getDeviceId());
    }

    private String getDeviceId() {
        String deviceId = preferenceWrapper.readString(DataKey.DEVICE_ID.name());
        if (deviceId == null) {
            deviceId = UUID.randomUUID().toString();
        }
        return deviceId;
    }
}
