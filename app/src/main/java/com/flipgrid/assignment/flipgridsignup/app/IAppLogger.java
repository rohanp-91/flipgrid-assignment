package com.flipgrid.assignment.flipgridsignup.app;

import java.sql.Timestamp;
import java.util.HashMap;

public interface IAppLogger {

    void LogInfo(HashMap<String, String> params);

    void LogError(HashMap<String, String> params);

    void LogLifecycle(HashMap<String, String> params);
}
