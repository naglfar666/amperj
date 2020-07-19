package com.amperj.processors;

import com.amperj.container.BeanContainer;
import com.amperj.container.Container;
import com.amperj.core.AmperApplication;
import com.amperj.models.AmperRequest;
import com.amperj.models.RouteModel;
import com.amperj.router.Router;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

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

        // Получаем маршрут из маршрутизатора
        Router router = AmperApplication.getAmperContext().getRouter();
        RouteModel routeModel = router.findRoute(target, httpServletRequest.getMethod());

        System.out.println(">> FOUND ROUTE MODEL " + routeModel);

        Container beanContainer = AmperApplication.getAmperContext().getBeanContainer();

        if (routeModel != null && routeModel.isFunctional()) {
            AmperRequest amperRequest = new AmperRequest();
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

}
