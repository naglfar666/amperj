package com.amperj.router;

import com.amperj.models.AmperRequest;
import com.amperj.models.AmperResponse;
import com.amperj.models.RouteModel;

import java.util.function.Function;

public interface Router {

    public void register(RouteModel routeModel);

    public RouteModel findRoute(String requestUri, String method);

    public Router get(String path, Function<AmperRequest, AmperResponse> function);
}
