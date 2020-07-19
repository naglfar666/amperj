package com.amperj.core;

import com.amperj.container.Container;
import com.amperj.registrar.Registrar;
import com.amperj.router.Router;

public class AmperContext {

    private Router router;

    private Container beanContainer;

    private Registrar beanRegistrar;

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

    public Registrar getBeanRegistrar() {
        return beanRegistrar;
    }

    public void setBeanRegistrar(Registrar beanRegistrar) {
        this.beanRegistrar = beanRegistrar;
    }
}
