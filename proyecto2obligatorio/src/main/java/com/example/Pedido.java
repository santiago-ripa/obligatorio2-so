package com.example;

public class Pedido {
    static final int FACTOR_ENVEJECIMIENTO = 200;

    int numero;
    Cliente cliente;
    String producto;
    int demora;
    String origen;
    long entrada;
    long inicio;
    long fin;

    public Pedido(int numero, Cliente cliente, String producto, int demora, String origen) {
        this.numero = numero;
        this.cliente = cliente;
        this.producto = producto;
        this.demora = demora;
        this.origen = origen;
        entrada = System.currentTimeMillis();
    }

    public int prioridad() {
        int puntos = cliente.fidelidad;

        if (cliente.rol.equals("PROFESOR")) {
            puntos = puntos + 5;
        }

        if (producto.equals("CAFE")) {
            puntos = puntos + 2;
        }

        double espera = System.currentTimeMillis() - entrada;
        puntos = puntos + (int) (espera / FACTOR_ENVEJECIMIENTO);

        return puntos;
    }
}
