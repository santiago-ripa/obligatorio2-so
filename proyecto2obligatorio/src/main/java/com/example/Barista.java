package com.example;

public class Barista extends Thread {
    int numero;
    Cola cola;
    Cafetera cafetera;
    Caja caja;
    Metricas metricas;

    public Barista(int numero, Cola cola, Cafetera cafetera,
                   Caja caja, Metricas metricas) {
        this.numero = numero;
        this.cola = cola;
        this.cafetera = cafetera;
        this.caja = caja;
        this.metricas = metricas;
    }

    public void run() {
        try {
            while (true) {
                Pedido pedido = cola.sacar();

                if (pedido == null) {
                    return;
                }

                pedido.inicio = (int) System.currentTimeMillis();

                System.out.println("Barista " + numero
                        + " prepara pedido " + pedido.numero);

                if (pedido.producto.equals("CAFE")) {
                    cafetera.usar(pedido);
                } else {
                    Thread.sleep(pedido.demora);
                }

                if (pedido.origen.equals("APP") == false) {
                    caja.usar(pedido);
                }

                pedido.fin = (int) System.currentTimeMillis();
                metricas.guardar(pedido);

                System.out.println("Termina pedido " + pedido.numero);
            }
        } catch (InterruptedException e) {
            System.out.println("Barista interrumpido");
        }
    }
}