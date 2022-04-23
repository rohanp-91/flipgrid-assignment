package com.flipgrid.assignment.flipgridsignup.app.network;

public interface IRequestService<TRequest> {

    void getAsync(TRequest request, IResponseCallback responseCallback);

    void postAsync(TRequest request, IResponseCallback responseCallback);
}
