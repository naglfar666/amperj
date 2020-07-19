package com.amperj.models;

import java.lang.reflect.Method;

public class RouteModel {

    private String requestType;

    private String controllerBeanId;

    private Method method;

    private String requestPath;

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
}
