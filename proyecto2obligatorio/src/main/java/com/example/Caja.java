package com.example;
import java.util.concurrent.Semaphore;

public class Caja {
    Semaphore semaforo = new Semaphore(1);

    public void usar(Pedido pedido) throws InterruptedException {
        semaforo.acquire();
        System.out.println("Pedido " + pedido.numero + " usa caja");
        Thread.sleep(100);
        semaforo.release();
    }
}
