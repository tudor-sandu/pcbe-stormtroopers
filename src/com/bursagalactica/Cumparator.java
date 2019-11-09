package com.bursagalactica;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;


public class Cumparator implements HttpHandler {
    private Bursa b;

    public Cumparator(Bursa b) {
        this.b = b;
    }

    @Override
    public void handle(HttpExchange t) throws IOException {
        String body = Util.convertStreamToString(t.getRequestBody());
        String[] cerere = body.split("-");
        if (b.checkIfAgentExists(cerere[3])) {
            b.buy(new Actiune(cerere[0], Double.parseDouble(cerere[1]), Double.parseDouble(cerere[2]), b.getAgentVanzari(cerere[3])));
            t.sendResponseHeaders(200, 0);
        } else {
            t.sendResponseHeaders(401, 0);
        }
        OutputStream os = t.getResponseBody();
        os.close();
    }
}
