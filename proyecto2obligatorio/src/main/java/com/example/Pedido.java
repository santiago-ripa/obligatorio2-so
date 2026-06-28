package com.example;

public class Pedido {
    int numero;
    Cliente cliente;
    String producto;
    int demora;
    String origen;
    int entrada;
    int inicio;
    int fin;

    public Pedido(int numero, Cliente cliente, String producto,
                  int demora, String origen) {
        this.numero = numero;
        this.cliente = cliente;
        this.producto = producto;
        this.demora = demora;
        this.origen = origen;
        entrada = (int) System.currentTimeMillis();
    }

    public int prioridad() {
        int puntos = cliente.fidelidad;

        if (cliente.rol.equals("PROFESOR")) {
            puntos = puntos + 5;
        }

        if (producto.equals("CAFE")) {
            puntos = puntos + 2;
        }

        int espera = (int) System.currentTimeMillis() - entrada;
        puntos = puntos + espera / 1000;

        return puntos;
    }
}