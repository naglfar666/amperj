package com.amperj.router;

import com.amperj.models.AmperRequest;
import com.amperj.models.AmperResponse;
import com.amperj.models.RouteModel;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public interface Router {

    /**
     * Регистрирует модель маршрутизации
     * @param routeModel Модель маршрутизации
     */
    public void register(RouteModel routeModel);

    /**
     * Ищет модель маршрутизации по пути запроса и методу
     * @param requestUri Путь запроса
     * @param method Метод запроса
     * @return RouteModel
     */
    public RouteModel findRoute(String requestUri, String method);

    /**
     * Записывает модель маршрутизации на GET запрос
     * @param path Путь запроса
     * @param function Точка входа, которая будет вызвана
     * @return Router
     */
    public Router get(String path, Function<AmperRequest, AmperResponse> function);

    /**
     * Записывает модель маршрутизации на GET запрос со списком промежуточных обработчиков
     * @param path Путь запроса
     * @param function Точка входа, которая будет вызвана
     * @param middlewares Промежуточные обработчики
     * @return Router
     */
    public Router get(String path, Function<AmperRequest, AmperResponse> function, List<Function<AmperRequest, AmperResponse>> middlewares);

    /**
     * Записывает модель маршрутизации на POST запрос
     * @param path Путь запроса
     * @param function Точка входа, которая будет вызвана
     * @return Router
     */
    public Router post(String path, Function<AmperRequest, AmperResponse> function);

    /**
     * Записывает модель маршрутизации на POST запрос со списком промежуточных обработчиков
     * @param path Путь запроса
     * @param function Точка входа, которая будет вызвана
     * @param middlewares Промежуточные обработчики
     * @return Router
     */
    public Router post(String path, Function<AmperRequest, AmperResponse> function, List<Function<AmperRequest, AmperResponse>> middlewares);

    /**
     * Записывает модель маршрутизации на PUT запрос
     * @param path Путь запроса
     * @param function Точка входа, которая будет вызвана
     * @return Router
     */
    public Router put(String path, Function<AmperRequest, AmperResponse> function);

    /**
     * Записывает модель маршрутизации на PUT запрос со списком промежуточных обработчиков
     * @param path Путь запроса
     * @param function Точка входа, которая будет вызвана
     * @param middlewares Промежуточные обработчики
     * @return Router
     */
    public Router put(String path, Function<AmperRequest, AmperResponse> function, List<Function<AmperRequest, AmperResponse>> middlewares);

    /**
     * Записывает модель маршрутизации на DELETE запрос
     * @param path Путь запроса
     * @param function Точка входа, которая будет вызвана
     * @return Router
     */
    public Router delete(String path, Function<AmperRequest, AmperResponse> function);

    /**
     * Записывает модель маршрутизации на DELETE запрос со списком промежуточных обработчиков
     * @param path Путь запроса
     * @param function Точка входа, которая будет вызвана
     * @param middlewares Промежуточные обработчики
     * @return Router
     */
    public Router delete(String path, Function<AmperRequest, AmperResponse> function, List<Function<AmperRequest, AmperResponse>> middlewares);

    /**
     * Записывает модель маршрутизации на OPTIONS запрос
     * @param path Путь запроса
     * @param function Точка входа, которая будет вызвана
     * @return Router
     */
    public Router options(String path, Function<AmperRequest, AmperResponse> function);

    /**
     * Записывает модель маршрутизации на OPTIONS запрос со списком промежуточных обработчиков
     * @param path Путь запроса
     * @param function Точка входа, которая будет вызвана
     * @param middlewares Промежуточные обработчики
     * @return Router
     */
    public Router options(String path, Function<AmperRequest, AmperResponse> function, List<Function<AmperRequest, AmperResponse>> middlewares);

    /**
     * Модификатор маршрутизатора
     * Группировка всех последующих вызовов по нужному пути
     * @param path Путь для группировки
     * @return Router
     */
    public Router group(String path);

    /**
     * Сбрасывает предыдущие модификаторы маршрутизатора
     * @return Router
     */
    public Router and();
}
