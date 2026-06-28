package com.example;

public class Barista extends Thread {
    int numero;
    Cola cola;
    Cafetera cafetera;
    Caja caja;

    public Barista(int numero, Cola cola, Cafetera cafetera, Caja caja) {
        this.numero = numero;
        this.cola = cola;
        this.cafetera = cafetera;
        this.caja = caja;
    }

    public void run() {
        try {
            while (true) {
                Pedido pedido = cola.sacar();

                if (pedido == null) {
                    return;
                }

                System.out.println("Barista " + numero
                        + " prepara pedido " + pedido.numero);

                if (pedido.cafe == 1) {
                    cafetera.usar(pedido);
                } else {
                    Thread.sleep(300);
                }

                if (pedido.origen != 1) {
                    caja.usar(pedido);
                }

                System.out.println("Pedido " + pedido.numero + " terminado");
            }
        } catch (InterruptedException e) {
            System.out.println("Barista interrumpido");
        }
    }
}
