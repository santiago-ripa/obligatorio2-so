package com.example;
import java.util.concurrent.Semaphore;

public class Cafetera {
    Semaphore semaforo = new Semaphore(1);

    public void usar(Pedido pedido) throws InterruptedException {
        semaforo.acquire();
        System.out.println("Pedido " + pedido.numero + " usa cafetera");
        Thread.sleep(500);
        semaforo.release();
    }
}