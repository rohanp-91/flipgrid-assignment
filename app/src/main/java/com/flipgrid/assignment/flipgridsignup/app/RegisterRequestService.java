package com.flipgrid.assignment.flipgridsignup.app;

import android.os.Handler;
import android.os.Looper;

public class RegisterRequestService extends BaseRequestService{

    @Override
    public void getAsync(Object request, IResponseCallback responseCallback) {
        Handler mainHandler = new Handler(Looper.getMainLooper());
        Thread getAction = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            responseCallback.onSuccess(request, new Object());
                        }
                    });
                } catch (Exception e) {
                    // do nothing for now
                }
            }
        });
        getAction.start();
    }

    @Override
    public void postAsync(Object request, IResponseCallback responseCallback) {
        Handler mainHandler = new Handler(Looper.getMainLooper());
        Thread postAction = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            responseCallback.onSuccess(request, new Object());
                        }
                    });
                } catch (Exception e) {
                    // do nothing for now
                    e.printStackTrace();
                }
            }
        });
        postAction.start();
    }
}
