package com.amperj.utils;

import com.amperj.specifications.Component;
import com.amperj.specifications.Controller;
import com.amperj.specifications.Entity;
import com.amperj.specifications.Routes;
import com.amperj.specifications.AmperApplication;

public class SpecificationValidator {

    public static boolean isComponent(Class<?> appClass) {
        return appClass.isAnnotationPresent(Component.class) ||
                appClass.isAnnotationPresent(Controller.class) ||
                appClass.isAnnotationPresent(Routes.class) ||
                appClass.isAnnotationPresent(AmperApplication.class) ||
                appClass.isAnnotationPresent(Entity.class);
    }

}
