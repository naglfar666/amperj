package com.amperj.router;

import com.amperj.models.RouteModel;

public interface Router {

    public void register(RouteModel routeModel);

    public RouteModel findRoute(String requestUri, String method);

}
