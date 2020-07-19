package com.amperj.utils;

import com.amperj.specifications.*;

public class SpecificationValidator {

    public static boolean isComponent(Class<?> appClass) {
        return appClass.isAnnotationPresent(Component.class) ||
                appClass.isAnnotationPresent(Controller.class) ||
                appClass.isAnnotationPresent(Routes.class) ||
                appClass.isAnnotationPresent(AmperApplication.class) ||
                appClass.isAnnotationPresent(Middleware.class) ||
                appClass.isAnnotationPresent(Entity.class);
    }

}
