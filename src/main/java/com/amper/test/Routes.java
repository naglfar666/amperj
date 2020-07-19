package com.amper.test;

import com.amper.test.controllers.TestController;
import com.amper.test.middlewares.CorsMiddleware;
import com.amperj.core.AmperContext;
import com.amperj.models.AmperRequest;
import com.amperj.models.AmperResponse;
import com.amperj.specifications.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@com.amperj.specifications.Routes
public class Routes {

    @Autowired
    private TestController testController;

    @Autowired
    private CorsMiddleware corsMiddleware;

    public void register(AmperContext amperContext) {
        System.out.println(">> ROUTER " + amperContext.getRouter());
        List<Function<AmperRequest, AmperResponse>> generalMiddlewares = new ArrayList<>();
        generalMiddlewares.add(corsMiddleware::handle);

        amperContext.getRouter()
                .group("/api/test")
                .get("/funcRoute", testController::testFunction, generalMiddlewares)
                .get("/funcRoute2", testController::testFunction, generalMiddlewares)
                .and()
                .group("/api/test2")
                .get("/funcRoute", testController::testFunction, generalMiddlewares)
                .get("/funcRoute2", testController::testFunction, generalMiddlewares);
    }

}
