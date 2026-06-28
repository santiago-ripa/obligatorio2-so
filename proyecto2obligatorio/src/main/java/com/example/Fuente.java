package com.example;

public class Fuente extends Thread {
    static int proximo = 1;
    String nombre;
    int pausa;
    Cola cola;
    Cliente[] clientes;

    public Fuente(String nombre, int pausa, Cola cola,
                  Cliente[] clientes) {
        this.nombre = nombre;
        this.pausa = pausa;
        this.cola = cola;
        this.clientes = clientes;
    }

    public static synchronized int nuevoNumero() {
        int numero = proximo;
        proximo = proximo + 1;
        return numero;
    }

    public void run() {
        try {
            for (int i = 0; i < 4; i++) {
                Thread.sleep(pausa);

                int numero = nuevoNumero();
                Cliente cliente = clientes[numero % clientes.length];
                String producto = "AGUA";
                int demora = 200;

                if (numero % 3 == 0) {
                    producto = "CAFE";
                    demora = 500;
                }

                if (numero % 3 == 1) {
                    producto = "SANDWICH";
                    demora = 400;
                }

                Pedido pedido = new Pedido(numero, cliente, producto,
                        demora, nombre);
                cola.agregar(pedido);
            }
        } catch (InterruptedException e) {
            System.out.println("Fuente interrumpida");
        }
    }
}