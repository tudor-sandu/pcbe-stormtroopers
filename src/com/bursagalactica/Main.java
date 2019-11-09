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
        Vanzator v = new Vanzator(ny);
        Cumparator c = new Cumparator(ny);
        AgentFactory agf = new AgentFactory(ny);

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/buy", v);
        server.createContext("/sell", c);
        server.createContext("/info", ny);
        server.createContext("/login", agf);
        server.setExecutor(null); //default executor
        server.start();
    }
}
