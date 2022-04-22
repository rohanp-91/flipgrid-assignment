package com.flipgrid.assignment.flipgridsignup.app;

public interface IResponseCallback<TRequest, TResponse> {

    void onSuccess(TRequest request, TResponse response);

    void onFailure(TRequest request, TResponse response);
}
