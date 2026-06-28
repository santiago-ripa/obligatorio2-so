package com.example;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int inicio = (int) System.currentTimeMillis();

        Cola cola = new Cola();
        Cafetera cafetera = new Cafetera();
        Caja caja = new Caja();
        Metricas metricas = new Metricas();

        Cliente[] clientes = new Cliente[4];
        clientes[0] = new Cliente("Ana", "ESTUDIANTE", 0);
        clientes[1] = new Cliente("Bruno", "PROFESOR", 1);
        clientes[2] = new Cliente("Carla", "ESTUDIANTE", 2);
        clientes[3] = new Cliente("Diego", "PROFESOR", 2);

        Fuente app = new Fuente("APP", 150, cola, clientes);
        Fuente totem = new Fuente("TOTEM", 250, cola, clientes);
        Fuente mostrador = new Fuente("MOSTRADOR", 350, cola, clientes);

        Barista uno = new Barista(1, cola, cafetera, caja, metricas);
        Barista dos = new Barista(2, cola, cafetera, caja, metricas);

        uno.start();
        dos.start();
        app.start();
        totem.start();
        mostrador.start();

        app.join();
        totem.join();
        mostrador.join();

        cola.cerrar();

        uno.join();
        dos.join();

        metricas.mostrar(cafetera, inicio);
    }
}