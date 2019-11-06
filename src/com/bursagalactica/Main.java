package com.bursagalactica;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

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
            Iterator<Actiune> iterator = ActiuniCumparare.iterator();
            resp.append("{ \"actiuni_cumparare\":");

            resp.append('[');
            while (iterator.hasNext()) {
                Actiune actiune = iterator.next();
                if (!iterator.hasNext()) {
                    resp.append(actiune.getJSON());
                } else {
                    resp.append(actiune.getJSON());
                    resp.append(',');
                }
            }
            resp.append("],");


            ArrayList<Actiune> ActiuniVanzare = b.getActiuniVanzare();
            Iterator<Actiune> iterator_vanzare = ActiuniVanzare.iterator();
            resp.append("\"actiuni_vanzare\":");

            resp.append('[');
            while (iterator_vanzare.hasNext()) {
                Actiune actiune = iterator_vanzare.next();
                if (!iterator_vanzare.hasNext()) {
                    resp.append(actiune.getJSON());
                } else {
                    resp.append(actiune.getJSON());
                    resp.append(',');
                }
            }
            resp.append("]}");
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
