package com.flipgrid.assignment.flipgridsignup.app;

public class RegistrationRepository {

    public void sendLoginRequest(Object request, IResponseCallback callback) {
        RegisterRequestService registerRequestService = new RegisterRequestService();
        registerRequestService.postAsync(request, callback);
    }
}
