package com.amper.test.controllers;

import com.amperj.models.AmperRequest;
import com.amperj.models.AmperResponse;
import com.amperj.specifications.Controller;
import com.amperj.specifications.Get;
import com.amperj.specifications.Post;

@Controller(path = "/api/test")
public class TestController {

    @Get(path = "/getRequest")
    public void getTest(Object param) {
        System.out.println(">> PROCESSED GET REQUEST");
    }

    @Post(path = "/postRequest")
    public void postRequest(String param) {
        System.out.println(">> PROCESSED POST REQUEST");
    }

    public AmperResponse testFunction(AmperRequest httpServletRequest) {
        System.out.println(">> PROCESSED FUNCTIONAL REQUEST");
        return new AmperResponse();
    }

}
