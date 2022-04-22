package com.flipgrid.assignment.flipgridsignup.app;

public class RegisterRequestService extends BaseRequestService{

    @Override
    public void getAsync(Object request, IResponseCallback responseCallback) {
        Thread getAction = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    responseCallback.onSuccess(request, new Object());
                } catch (Exception e) {
                    // do nothing for now
                }
            }
        });
        getAction.run();
    }

    @Override
    public void postAsync(Object request, IResponseCallback responseCallback) {
        Thread postAction = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    responseCallback.onSuccess(request, new Object());
                } catch (Exception e) {
                    // do nothing for now
                }
            }
        });
        postAction.run();
    }
}
