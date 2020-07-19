package com.amper.test;

import com.amperj.core.Amper;
import com.amperj.core.AmperContext;
import com.amperj.specifications.AmperApplication;
import com.amperj.specifications.Autowired;

@AmperApplication
public class Application {

    @Autowired
    private static Routes routes;

    public static void main(String[] args) {
        AmperContext amperContext = Amper.init(Application.class, args);
        routes.register(amperContext);
        Amper.run();
    }

}
