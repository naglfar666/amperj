package com.amperj.processors;

import com.amperj.container.Container;
import com.amperj.core.AmperApplication;
import com.amperj.models.AmperRequest;
import com.amperj.models.AmperResponse;
import com.amperj.models.RouteModel;
import com.amperj.router.Router;
import com.amperj.settings.RequestMethod;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.function.Function;

public class JettyRequestProcessor extends AbstractHandler {

    @Override
    public void handle(
            String target,
            Request request,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) throws IOException, ServletException {
        System.out.println(">> TARGET " + target);
        System.out.println(">> METHOD " + httpServletRequest.getMethod());
        System.out.println(">> METHOD GET EQUAL " + httpServletRequest.getMethod().toUpperCase().equals(RequestMethod.GET.name().toUpperCase()));

        String body = null;
        try {
            body = getRequestBody(httpServletRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(">> REQUEST BODY " + body);

        // Получаем маршрут из маршрутизатора
        Router router = AmperApplication.getAmperContext().getRouter();

        RouteModel routeModel = router.findRoute(target, httpServletRequest.getMethod());

        System.out.println(">> FOUND ROUTE MODEL " + routeModel);

        Container beanContainer = AmperApplication.getAmperContext().getBeanContainer();

        // Если маршрут был зарегистрирован функционально с помощью объекта маршрутов, вызываем вначале их
        if (routeModel != null && routeModel.isFunctional()) {
            // Инициализируем объект запроса
            AmperRequest amperRequest = new AmperRequest();

            // Если к маршруту приложены промежуточные обработчики, вызываем их по очереди
            if (routeModel.getMiddlewares() != null) {
                for (Function<AmperRequest, AmperResponse> middleware : routeModel.getMiddlewares()) {
                    AmperResponse amperResponse = middleware.apply(amperRequest);
                    // Если промежуточный обработчик закончил запрос
                    if (amperResponse != null && amperResponse.getHandled()) {
                        System.out.println(">> REQUEST FINISHED BY MIDDLEWARE");
                    }
                }
            }
            // Вызываем функцию из контроллера
            routeModel.getFunction().apply(amperRequest);

        } else {
            // Вызываем метод, закрепленный за маршрутом
            try {

                if (routeModel != null) {
                    String[] args = { "test" };
                    routeModel.getMethod().invoke(beanContainer.getController(routeModel.getControllerBeanId()), "test");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        httpServletResponse.setContentType("text/html; charset=utf-8");
        if (routeModel == null) {
            httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            httpServletResponse.getWriter().println("<h1>Route not found</h1>");
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.getWriter().println("<h1>Hello World</h1>");
        }
        request.setHandled(true);
    }

    /**
     * Получение тела запроса
     * @param httpServletRequest
     * @return
     * @throws Exception
     */
    private String getRequestBody(HttpServletRequest httpServletRequest) throws Exception {
        StringBuilder requestBodyBuilder = new StringBuilder();

        BufferedReader bodyReader = httpServletRequest.getReader();
        String line = bodyReader.readLine();
        while (line != null) {
            requestBodyBuilder.append(line);
            line = bodyReader.readLine();
        }

        return requestBodyBuilder.toString();
    }

}
