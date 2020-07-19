package com.amperj.router;

import com.amperj.models.AmperRequest;
import com.amperj.models.AmperResponse;
import com.amperj.models.RouteModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class RouterImpl implements Router {

    private final Map<String, RouteModel> routeModelMap = new HashMap<>();

    private String groupPath;

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
        register(fillFunctionRouteModel(path, function));
        return this;
    }

    @Override
    public Router get(String path, Function<AmperRequest, AmperResponse> function, List<Function<AmperRequest, AmperResponse>> middlewares) {
        RouteModel routeModel = fillFunctionRouteModel(path, function);
        routeModel.setMiddlewares(middlewares);
        register(routeModel);
        return this;
    }

    @Override
    public Router group(String path) {
        groupPath = path;
        return this;
    }

    @Override
    public Router and() {
        groupPath = null;
        return this;
    }

    /**
     * Предзаполняет модель функционального вызова
     * @param path
     * @param function
     * @return
     */
    private RouteModel fillFunctionRouteModel(String path, Function<AmperRequest, AmperResponse> function) {
        // Если включен модификатор группировки, используем его
        if (groupPath != null) {
            path = groupPath + path;
        }
        System.out.println(">> REGISTERING ROUTE " + path);
        // Создаем модель маршрутизации
        RouteModel routeModel = new RouteModel();
        routeModel.setRequestPath(path);
        routeModel.setFunction(function);
        routeModel.setFunctional(true);
        return routeModel;
    }


}
