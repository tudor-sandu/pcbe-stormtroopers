package com.bursagalactica;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class Bursa {

    private volatile ArrayList<Actiune> vindeActiuni ;
    private volatile HashMap<Cerere, Thread> oferteActiuni ;

    synchronized void sell(Actiune e) {
        ActiuniVanzare.add(e);
    }

    public Bursa(){
        ActiuniCumparare = new ArrayList<>();
        ActiuniVanzare = new HashMap<>();
    }

    public synchronized void punePeBursa(VindeActiune actiune) {
        vindeActiuni.add(actiune);
    }

    public synchronized void scoateDePeBursa(VindeActiune actiune) {
        vindeActiuni.remove(actiune);
    }

    public synchronized void adaugaCerere(CumparaActiune cumparaActiune) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (1) {
                    for (VindeActiune vindeActiune : vindeActiuni) {
                        vindeActiuni.tryTransaction(cumparaActiune);
                        if (vindeActiune.getCount() == 0) {
                            scoateDePeBursa(vindeActiune);
                        }
                        if (cumparaActiune.getCount() == 0) {
                            scoateCerere(cumparaActiune);
                        }
                    }
                }
            }
        });
        thread.start();
        this.oferteActiuni.put(cumparaActiune, thread);
    }

    public synchronized void scoateCerere(CumparaActiune cumparaActiune) {
        Thread thread = this.oferteActiuni.get(cumparaActiune);
        if(thread == null) {
            return;
        }

        thread.interrupt();
        this.oferteActiuni.remove(cumparaActiune);
    }
}