package com.amperj.processors;

import com.amperj.core.AmperContext;
import com.amperj.models.RouteModel;
import com.amperj.specifications.*;

import java.lang.reflect.Method;

public class RestControllerProcessor {

    public void process(AmperContext amperContext, Class<?>[] classes) {
        for (Class<?> appClass : classes) {
            if (appClass.isAnnotationPresent(Controller.class)) {
                try {
                    System.out.println("Registered controller " + appClass.getName());

                    // Регистрируем контроллер в качестве бина
                    amperContext.getBeanContainer().setController(appClass.getName(), appClass.getConstructor().newInstance());

                    // Регистрируем все методы контроллера
                    Method[] methods = appClass.getMethods();

                    for (Method method : methods) {
                        // Заполняем модель маршрута
                        RouteModel routeModel = new RouteModel();

//                        if (method.isAnnotationPresent(Get.class)) {
//                            routeModel.setRequestType("GET");
//                            routeModel.setRequestPath(appClass.getAnnotation(Controller.class).path() + method.getAnnotation(Get.class).path());
//                        } else if (method.isAnnotationPresent(Post.class)) {
//                            routeModel.setRequestType("POST");
//                            routeModel.setRequestPath(appClass.getAnnotation(Controller.class).path() + method.getAnnotation(Post.class).path());
//                        } else if (method.isAnnotationPresent(Delete.class)) {
//                            routeModel.setRequestType("DELETE");
//                            routeModel.setRequestPath(appClass.getAnnotation(Controller.class).path() + method.getAnnotation(Delete.class).path());
//                        } else if (method.isAnnotationPresent(Put.class)) {
//                            routeModel.setRequestType("PUT");
//                            routeModel.setRequestPath(appClass.getAnnotation(Controller.class).path() + method.getAnnotation(Put.class).path());
//                        }

                        routeModel.setMethod(method);
                        routeModel.setControllerBeanId(appClass.getName());

                        // Добавляем маршрут в маршрутизатор
                        amperContext.getRouter().register(routeModel);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
