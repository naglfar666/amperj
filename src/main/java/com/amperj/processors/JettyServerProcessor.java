package com.amperj.processors;

import org.eclipse.jetty.server.Server;

public class JettyServerProcessor {

    public static void runServer() {
        Server server = new Server(8080);
        try {
            server.setHandler(new JettyRequestProcessor());
            server.start();
            server.join();
        } catch (Exception e) {
            if (server.isStarted()) {
                try {
                    server.stop();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            e.printStackTrace();
        }
    }

}
