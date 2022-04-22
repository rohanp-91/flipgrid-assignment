package com.flipgrid.assignment.flipgridsignup.app;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class AppLogger implements IAppLogger {

    private AppContext appContext;

    public AppLogger(Context context) {
        this.appContext = (AppContext) context;
    }

    @Override
    public void LogInfo(HashMap<String, String> params) {
        log(LogType.INFO, params);
    }

    @Override
    public void LogError(HashMap<String, String> params) {
        log(LogType.ERROR, params);
    }

    @Override
    public void LogLifecycle(HashMap<String, String> params) {
        log(LogType.LIFECYCLE, params);
    }

    private void log(LogType logType, HashMap<String, String> map) {
        addGlobalParameters(map);
        String params = new JSONObject(map).toString();
        switch (logType) {
            case INFO:
                Log.i(LogType.INFO.name(), params);
                break;
            case ERROR:
                Log.e(LogType.ERROR.name(), params);
                break;
            case LIFECYCLE:
                Log.i(LogType.LIFECYCLE.name(), params);
                break;
            default:
                Log.i(LogType.OTHER.name(), params);
        }
    }

    private void addGlobalParameters(HashMap<String, String> map) {
        map.put("Timestamp", String.valueOf(System.currentTimeMillis()/1000));
        map.put("SessionId", this.appContext.getSessionId());
        map.put("DeviceId", this.appContext.getPreferenceWrapper().readString(DataKey.DEVICE_ID.name()));
    }

    private enum LogType {
        INFO,
        ERROR,
        LIFECYCLE,
        OTHER
    }
}
