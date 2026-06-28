package com.example;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Cola cola = new Cola();
        Cafetera cafetera = new Cafetera();
        Caja caja = new Caja();

        Fuente app = new Fuente(1, cola);
        Fuente totem = new Fuente(2, cola);
        Fuente mostrador = new Fuente(3, cola);

        Barista barista1 = new Barista(1, cola, cafetera, caja);
        Barista barista2 = new Barista(2, cola, cafetera, caja);

        barista1.start();
        barista2.start();

        app.start();
        totem.start();
        mostrador.start();

        app.join();
        totem.join();
        mostrador.join();

        cola.cerrar();

        barista1.join();
        barista2.join();

        System.out.println("Simulacion terminada");
    }
}