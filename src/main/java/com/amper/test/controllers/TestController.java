package com.amper.test.controllers;

import com.amperj.specifications.Controller;
import com.amperj.specifications.Get;
import com.amperj.specifications.Post;

@Controller(path = "/api/test")
public class TestController {

    @Get(path = "/getRequest")
    public void getTest(String param) {
        System.out.println(">> PROCESSED GET REQUEST");
    }

    @Post(path = "/postRequest")
    public void postRequest(String param) {
        System.out.println(">> PROCESSED POST REQUEST");
    }

}
