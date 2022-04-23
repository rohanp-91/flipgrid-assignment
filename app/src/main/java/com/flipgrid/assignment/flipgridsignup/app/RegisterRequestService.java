package com.flipgrid.assignment.flipgridsignup.app;

import android.os.Handler;
import android.os.Looper;

public class RegisterRequestService extends BaseRequestService {

    private Handler mHandler;

    public RegisterRequestService() {
        super();
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void getAsync(Object request, IResponseCallback responseCallback) {
        Thread getAction = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    mHandler.post(new Runnable() {
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
        getAction.start();
    }

    @Override
    public void postAsync(Object request, IResponseCallback responseCallback) {
        Thread postAction = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    mHandler.post(new Runnable() {
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

    public Handler getHandler() {
        return mHandler;
    }
}
