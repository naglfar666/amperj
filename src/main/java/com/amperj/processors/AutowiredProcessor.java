package com.amperj.processors;

import com.amperj.container.Container;
import com.amperj.core.AmperContext;
import com.amperj.specifications.Autowired;
import com.amperj.utils.SpecificationValidator;

import java.lang.reflect.Field;

public class AutowiredProcessor {

    public void process(AmperContext amperContext, Class<?>[] classes) {
        Container beanContainer = amperContext.getBeanContainer();
        // Проходим по всем классам
        for (Class<?> appClass : classes) {
            if (!SpecificationValidator.isComponent(appClass)) continue;

            System.out.println(">> Проходим класс " + appClass.getName());
            // Получаем список полей и проходим по нему
            Field[] fields = appClass.getDeclaredFields();
            for (Field field : fields) {
                System.out.println(">> Проходим поле " + field.getName());
                // Если поле помечено аннотацией Autowired, внедряем в него нужную зависимость
                if (field.isAnnotationPresent(Autowired.class)) {
                    System.out.println(">> В поле присутствует нужная аннотация");
                    String componentName = field.getType().getName();
                    field.setAccessible(true);
                    try {
                        System.out.println(">> Пытаюсь внедрить зависимость в бин " + appClass.getName() + " " + componentName);
                        // Внедрение зависимости в экземпляр нужного бина
                        field.set(beanContainer.get(appClass.getName()), beanContainer.get(componentName));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

}
