package com.bursagalactica;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Arrays;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Main {

    public static void main(String[] args) throws Exception {

        Bursa ny = new Bursa();
        Vanzator v = new Vanzator();
        Cumparator c = new Cumparator();
        Agent a = new Agent();
        a.tranzactioneaza(ny);

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/buy", new BuyHandler(c,ny));
        server.createContext("/sell", new SellHandler(v,ny));
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    static class BuyHandler implements HttpHandler {
        private Cumparator c;
        private Bursa b;
        public BuyHandler(Cumparator c, Bursa b) {
            this.c = c;
            this.b = b;
        }

        @Override
        public void handle(HttpExchange t) throws IOException {
            String body = Util.convertStreamToString(t.getRequestBody());
            String[] cerere = body.split("-");
            c.cumpara(new Actiune(cerere[0], Double.parseDouble(cerere[1]), Double.parseDouble(cerere[2])),b);
            t.sendResponseHeaders(200, 0);
            OutputStream os = t.getResponseBody();
            os.close();
        }
    }

    static class SellHandler implements HttpHandler {
        private Vanzator v;
        private Bursa b;
        public SellHandler(Vanzator v, Bursa b) {
            this.v = v;
            this.b = b;
        }
        @Override
        public void handle(HttpExchange t) throws IOException {
            String body = Util.convertStreamToString(t.getRequestBody());
            String[] cerere = body.split("-");
            v.vinde(new Actiune(cerere[0], Double.parseDouble(cerere[1]), Double.parseDouble(cerere[2])),b);
            t.sendResponseHeaders(200, 0);
            OutputStream os = t.getResponseBody();
            os.close();
        }
    }

}
