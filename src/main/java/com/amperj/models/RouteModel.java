package com.amperj.models;

import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Function;

public class RouteModel {

    // Метод запроса POST GET PUT DELETE
    private String requestType;

    // ID класса, в котором лежит маршрут
    private String controllerBeanId;

    // Вызываемая функция, обрабатывающая маршрут
    private Method method;

    // Путь запроса
    private String requestPath;

    // Функция, обратываемая через маршрутизатор
    private Function<AmperRequest, AmperResponse> function;

    // Список промежуточных обработчиков
    private List<Function<AmperRequest, AmperResponse>> middlewares;

    // Признак того, что модель маршрута записана функционально
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

    public List<Function<AmperRequest, AmperResponse>> getMiddlewares() {
        return middlewares;
    }

    public void setMiddlewares(List<Function<AmperRequest, AmperResponse>> middlewares) {
        this.middlewares = middlewares;
    }
}
