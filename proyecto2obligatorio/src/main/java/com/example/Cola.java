package com.example;

public class Cola {
    Pedido[] pedidos = new Pedido[30];
    int cantidad = 0;
    boolean cerrada = false;

    public synchronized void agregar(Pedido pedido)
            throws InterruptedException {
        while (cantidad == pedidos.length) {
            wait();
        }

        pedidos[cantidad] = pedido;
        cantidad = cantidad + 1;
        System.out.println("Entra pedido " + pedido.numero);
        notifyAll();
    }

    public synchronized Pedido sacar() throws InterruptedException {
        while (cantidad == 0 && cerrada == false) {
            wait();
        }

        if (cantidad == 0) {
            return null;
        }

        int mejor = 0;

        for (int i = 1; i < cantidad; i++) {
            if (pedidos[i].prioridad() > pedidos[mejor].prioridad()) {
                mejor = i;
            }
        }

        Pedido pedido = pedidos[mejor];

        for (int i = mejor; i < cantidad - 1; i++) {
            pedidos[i] = pedidos[i + 1];
        }

        cantidad = cantidad - 1;
        notifyAll();
        return pedido;
    }

    public synchronized void cerrar() {
        cerrada = true;
        notifyAll();
    }
}