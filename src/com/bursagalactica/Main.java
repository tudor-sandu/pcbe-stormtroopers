package com.bursagalactica;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
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
        server.createContext("/info", new InfoHandler(ny));
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    static class InfoHandler implements HttpHandler {
        private Bursa b;
        public InfoHandler(Bursa b) {
            this.b = b;
        }

        @Override
        public void handle(HttpExchange t) throws IOException {
            String encoding = "UTF-8";
            t.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
            t.getResponseHeaders().set("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
            t.getResponseHeaders().set("Access-Control-Allow-Credentials", "true");
            t.getResponseHeaders().set("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS,HEAD");
            t.getResponseHeaders().set("Content-Type", "text/html; charset=" + encoding);

            StringBuilder resp = new StringBuilder();
            ArrayList<Actiune> ActiuniCumparare = b.getActiuniCumparare();
            for (Actiune a : ActiuniCumparare
            ) {
                resp.append(a.getInfo());
                resp.append('\n');
            }
            t.sendResponseHeaders(200, resp.toString().length());
            OutputStream os = t.getResponseBody();
            os.write(resp.toString().getBytes());
            os.close();
        }
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
