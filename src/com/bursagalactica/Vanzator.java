package com.bursagalactica;

public class Vanzator {
    public void vinde(Actiune e, Bursa b) {
        new Thread(() -> {
            b.vinde(e);
        }).start();
    }
}
