package com.example;

public class Pedido {
    int numero;
    int profesor;
    int fidelidad;
    int cafe;
    int origen;
    int llegada;

    public Pedido(int numero, int profesor, int fidelidad,
                  int cafe, int origen) {
        this.numero = numero;
        this.profesor = profesor;
        this.fidelidad = fidelidad;
        this.cafe = cafe;
        this.origen = origen;
    }

    public int prioridad(int tiempo) {
        int valor = fidelidad;

        if (profesor == 1) {
            valor = valor + 10;
        }

        if (cafe == 1) {
            valor = valor + 5;
        }

        valor = valor + tiempo - llegada;
        return valor;
    }
}