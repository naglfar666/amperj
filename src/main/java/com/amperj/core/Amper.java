package com.amperj.core;

import com.amperj.models.AmperRunnerModel;

public class Amper {

    public static AmperContext run(Class<?> applicationClass, String[] args) {
        AmperRunnerModel amperRunnerModel = new AmperRunnerModel();
        amperRunnerModel.setApplicationArgs(args);
        amperRunnerModel.setApplicationClass(applicationClass);
        return AmperApplication.run(amperRunnerModel);
    }

}
