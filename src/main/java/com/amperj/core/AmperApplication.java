package com.amperj.core;

import com.amperj.models.AmperRunnerModel;
import com.amperj.processors.JettyServerProcessor;
import com.amperj.registrar.CoreBeanRegistrar;

public class AmperApplication {

    private static final AmperContext amperContext = new AmperContext();

    private static AmperRunnerModel amperRunnerModel;

    public static AmperContext init(AmperRunnerModel model) {
        amperRunnerModel = model;
        // Регистрируем основные модули ядра
        new CoreBeanRegistrar().register(amperContext);
        // Регистрируем все подключенные бины
        amperContext.getBeanRegistrar().register(amperContext);
        // Возвращаем контекст
        return amperContext;
    }

    public static void run() {
        // Запускаем веб сервер
        JettyServerProcessor.runServer();
    }

    public static AmperContext getAmperContext() {
        return amperContext;
    }

    public static AmperRunnerModel getAmperRunnerModel() {
        return amperRunnerModel;
    }

}
