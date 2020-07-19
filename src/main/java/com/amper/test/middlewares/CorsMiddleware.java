package com.amper.test.middlewares;

import com.amperj.models.AmperRequest;
import com.amperj.models.AmperResponse;
import com.amperj.specifications.Middleware;

@Middleware
public class CorsMiddleware {

    public AmperResponse handle(AmperRequest amperRequest) {
        System.out.println(">> CORS MIDDLEWARE WORKED");
        return null;
    }

}
