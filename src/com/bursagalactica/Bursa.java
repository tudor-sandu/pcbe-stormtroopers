package com.bursagalactica;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class Bursa implements HttpHandler {

    private ArrayList<Actiune> ActiuniCumparare = new ArrayList<Actiune>();
    private ArrayList<Actiune> ActiuniVanzare = new ArrayList<Actiune>();
    private ArrayList<Agent> AgentiDeBursa = new ArrayList<Agent>();

    synchronized void sell(Actiune e) {
        ActiuniVanzare.add(e);
    }

    synchronized void buy(Actiune e) {
        ActiuniCumparare.add(e);
    }

    private ArrayList<Actiune> getSellingStock() {
        return ActiuniCumparare;
    }

    private ArrayList<Actiune> getBuyingStock() {
        return ActiuniVanzare;
    }

    boolean createAgent(String numeAgent) {
        Agent newAgent = new Agent(numeAgent);
        if (!AgentiDeBursa.contains(newAgent)) {
            AgentiDeBursa.add(newAgent);
            return true;
        } else {
            return false;
        }
    }

    boolean checkIfAgentExists(String numeAgent) {
        Agent newAgent = new Agent(numeAgent);
        return AgentiDeBursa.contains(newAgent);
    }

    public Agent getAgentVanzari(String numeAgent) {
        Agent newAgent = new Agent(numeAgent);
        for(Agent ag : AgentiDeBursa) {
            if(ag.equals(newAgent)) {
                return ag;
            }
        }
        return null;
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
        ArrayList<Actiune> ActiuniCumparare = this.getSellingStock();
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


        ArrayList<Actiune> ActiuniVanzare = this.getBuyingStock();
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
        String newResp = resp.toString().replaceAll("\\r\\n|\\r|\\n", "");
        t.sendResponseHeaders(200, newResp.length());
        OutputStream os = t.getResponseBody();
        os.write(newResp.getBytes());
        os.close();
    }
}