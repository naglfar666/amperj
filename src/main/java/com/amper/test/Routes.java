package com.amper.test;

import com.amper.test.controllers.TestController;
import com.amperj.core.AmperContext;
import com.amperj.specifications.Autowired;

@com.amperj.specifications.Routes
public class Routes {

    @Autowired
    private TestController testController;

    public void register(AmperContext amperContext) {
        System.out.println(">> ROUTER " + amperContext.getRouter());
        amperContext.getRouter().get("/api/test/funcRoute", testController::testFunction);
    }

}
