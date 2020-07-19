package com.amperj.models;

import java.lang.reflect.Method;
import java.util.function.Function;

public class RouteModel {

    private String requestType;

    private String controllerBeanId;

    private Method method;

    private String requestPath;

    private Function<AmperRequest, AmperResponse> function;

    private Boolean functional;

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getControllerBeanId() {
        return controllerBeanId;
    }

    public void setControllerBeanId(String controllerBeanId) {
        this.controllerBeanId = controllerBeanId;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public String getRequestPath() {
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }

    public Function<AmperRequest, AmperResponse> getFunction() {
        return function;
    }

    public void setFunction(Function<AmperRequest, AmperResponse> function) {
        this.function = function;
    }

    public Boolean isFunctional() {
        return this.functional;
    }

    public void setFunctional(Boolean functional) {
        this.functional = functional;
    }
}
