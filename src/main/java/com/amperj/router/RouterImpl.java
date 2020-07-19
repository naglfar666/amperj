package com.amperj.router;

import com.amperj.models.AmperRequest;
import com.amperj.models.AmperResponse;
import com.amperj.models.RouteModel;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class RouterImpl implements Router {

    private final Map<String, RouteModel> routeModelMap = new HashMap<>();

    @Override
    public void register(RouteModel routeModel) {
        routeModelMap.put(routeModel.getRequestPath(), routeModel);
    }

    @Override
    public RouteModel findRoute(String requestUri, String method) {
        return routeModelMap.get(requestUri);
    }

    @Override
    public Router get(String path, Function<AmperRequest, AmperResponse> function) {
        System.out.println(">> REGISTERING ROUTE " + path);
        RouteModel routeModel = new RouteModel();

        routeModel.setRequestPath(path);
        routeModel.setFunction(function);
        routeModel.setFunctional(true);
        routeModelMap.put(path, routeModel);

        return this;
    }


}
