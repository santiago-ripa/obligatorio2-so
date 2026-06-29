package com.example;
import java.util.concurrent.Semaphore;

public class Cafetera {
    Semaphore semaforo = new Semaphore(1);
    double tiempoUsada = 0;

    public void usar(Pedido pedido) throws InterruptedException {
        semaforo.acquire();
        double inicio = System.currentTimeMillis();

        try {
            System.out.println("Pedido " + pedido.numero + " usa cafetera");
            Thread.sleep(pedido.demora);
        } finally {
            tiempoUsada = tiempoUsada
                    + (System.currentTimeMillis() - inicio);
            semaforo.release();
        }
    }
}
