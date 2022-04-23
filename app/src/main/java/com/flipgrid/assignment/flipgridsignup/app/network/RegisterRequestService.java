package com.flipgrid.assignment.flipgridsignup.app.network;

import android.os.Handler;
import android.os.Looper;

public class RegisterRequestService extends BaseRequestService {

    private final Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public void getAsync(Object request, IResponseCallback responseCallback) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                responseCallback.onSuccess(request, new Object());
            }
        }, 2000);
    }

    @Override
    public void postAsync(Object request, IResponseCallback responseCallback) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                responseCallback.onSuccess(request, new Object());
            }
        }, 2000);
    }
}
