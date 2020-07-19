package com.amperj.router;

import com.amperj.models.AmperRequest;
import com.amperj.models.AmperResponse;
import com.amperj.models.RouteModel;

import java.util.List;
import java.util.function.Function;

public interface Router {

    /**
     * Регистрирует модель маршрутизации
     * @param routeModel
     */
    public void register(RouteModel routeModel);

    /**
     * Ищет модель маршрутизации по пути запроса и методу
     * @param requestUri
     * @param method
     * @return
     */
    public RouteModel findRoute(String requestUri, String method);

    /**
     * Записывает модель маршрутизации на GET запрос
     * @param path
     * @param function
     * @return
     */
    public Router get(String path, Function<AmperRequest, AmperResponse> function);

    /**
     * Записывает модель маршрутизации на GET запрос со списком промежуточных обработчиков
     * @param path
     * @param function
     * @param middlewares
     * @return
     */
    public Router get(String path, Function<AmperRequest, AmperResponse> function, List<Function<AmperRequest, AmperResponse>> middlewares);
}
