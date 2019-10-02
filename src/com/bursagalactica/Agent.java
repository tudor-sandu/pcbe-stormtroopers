package com.bursagalactica;


public class Agent {
    public void tranzactioneaza(Bursa b) {
        new Thread(() -> {
            while (true) {
                try {
                    b.verificaTranzactii();
                    Thread.sleep(60000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
    }
}
