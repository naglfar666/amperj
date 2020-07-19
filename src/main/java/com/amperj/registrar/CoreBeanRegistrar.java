package com.amperj.registrar;

import com.amperj.container.BeanContainer;
import com.amperj.container.Container;
import com.amperj.core.AmperApplication;
import com.amperj.core.AmperContext;
import com.amperj.models.AmperRunnerModel;
import com.amperj.router.Router;
import com.amperj.router.RouterImpl;

public class CoreBeanRegistrar implements Registrar {

    private final AmperRunnerModel amperRunnerModel;

    public CoreBeanRegistrar() {
        this.amperRunnerModel = AmperApplication.getAmperRunnerModel();
    }

    public void register(AmperContext amperContext) {
        // Выставляем маршрутизатор
        amperContext.setRouter(getRouter());
        // Выставляем контейнер бинов
        amperContext.setBeanContainer(getBeanContainer());
        // Выставляем регистратор бинов
        amperContext.setBeanRegistrar(getBeanRegistrar());
    }

    /**
     * Получаем экземпляр маршрутизатора
     * @return Router
     */
    private Router getRouter() {
        if (amperRunnerModel.getRouter() != null) {
            return amperRunnerModel.getRouter();
        } else {
            return new RouterImpl();
        }
    }

    /**
     * Получаем экземпляр контейнера бинов
     * @return Container
     */
    private Container getBeanContainer() {
        if (amperRunnerModel.getBeanContainer() != null) {
            return amperRunnerModel.getBeanContainer();
        } else {
            return new BeanContainer();
        }
    }

    /**
     * Получаем экземпляр регистратора бинов
     * @return Registrar
     */
    private Registrar getBeanRegistrar() {
        if (amperRunnerModel.getBeanRegistrar() != null) {
            return amperRunnerModel.getBeanRegistrar();
        } else {
            return new BeanRegistrar();
        }
    }

}
