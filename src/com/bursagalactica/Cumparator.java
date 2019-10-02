package com.bursagalactica;

public class Cumparator {
    public void cumpara(Actiune e, Bursa b) {
        new Thread(() -> {
            b.cumpara(e);
        }).start();
    }
}
