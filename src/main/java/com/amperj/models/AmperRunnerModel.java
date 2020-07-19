package com.amperj.models;

import com.amperj.container.Container;
import com.amperj.registrar.Registrar;
import com.amperj.router.Router;

public class AmperRunnerModel {

    private Registrar beanRegistrar;

    private Router router;

    private Container beanContainer;

    private String[] applicationArgs;

    private Class<?> applicationClass;

    public Registrar getBeanRegistrar() {
        return beanRegistrar;
    }

    public void setBeanRegistrar(Registrar beanRegistrar) {
        this.beanRegistrar = beanRegistrar;
    }

    public Router getRouter() {
        return router;
    }

    public void setRouter(Router router) {
        this.router = router;
    }

    public Container getBeanContainer() {
        return beanContainer;
    }

    public void setBeanContainer(Container beanContainer) {
        this.beanContainer = beanContainer;
    }

    public String[] getApplicationArgs() {
        return applicationArgs;
    }

    public void setApplicationArgs(String[] applicationArgs) {
        this.applicationArgs = applicationArgs;
    }

    public Class<?> getApplicationClass() {
        return applicationClass;
    }

    public void setApplicationClass(Class<?> applicationClass) {
        this.applicationClass = applicationClass;
    }
}
