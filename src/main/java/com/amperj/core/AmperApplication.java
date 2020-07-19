package com.amperj.core;

import com.amperj.models.AmperRunnerModel;
import com.amperj.processors.JettyServerProcessor;
import com.amperj.registrar.CoreBeanRegistrar;

public class AmperApplication {

    private static final AmperContext amperContext = new AmperContext();

    private static AmperRunnerModel amperRunnerModel;

    public static AmperContext run(AmperRunnerModel model) {
        amperRunnerModel = model;
        // Регистрируем основные модули ядра
        new CoreBeanRegistrar().register(amperContext);
        // Регистрируем все подключенные бины
        amperContext.getBeanRegistrar().register(amperContext);
        // Запускаем веб сервер
        JettyServerProcessor.runServer();
        // Возвращаем контекст
        return amperContext;
    }

    public static AmperContext getAmperContext() {
        return amperContext;
    }

    public static AmperRunnerModel getAmperRunnerModel() {
        return amperRunnerModel;
    }

}
