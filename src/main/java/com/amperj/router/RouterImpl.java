package com.amperj.router;

import com.amperj.models.RouteModel;

import java.util.HashMap;
import java.util.Map;

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
}
