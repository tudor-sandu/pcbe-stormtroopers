package com.bursagalactica;


import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class AgentFactory implements HttpHandler {
    private Bursa b;

    public AgentFactory(Bursa b) {
        this.b = b;
    }

    @Override
    public void handle(HttpExchange t) throws IOException {
        String body = Util.convertStreamToString(t.getRequestBody());
        boolean isCreated = b.createAgent(body);
        if (isCreated) {
            t.sendResponseHeaders(200, 0);
        } else {
            t.sendResponseHeaders(400, 0);
        }
        OutputStream os = t.getResponseBody();
        os.close();
    }
}
