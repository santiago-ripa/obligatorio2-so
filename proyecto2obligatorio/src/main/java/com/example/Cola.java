package com.example;

public class Cola {
    Pedido[] pedidos = new Pedido[100];
    int cantidad = 0;
    int tiempo = 0;
    boolean cerrada = false;

    public synchronized void poner(Pedido pedido) {
        pedido.llegada = tiempo;
        pedidos[cantidad] = pedido;
        cantidad = cantidad + 1;
        System.out.println("Entra pedido " + pedido.numero);
        notifyAll();
    }

    public synchronized Pedido sacar() throws InterruptedException {
        while (cantidad == 0 && cerrada == false) {
            wait();
        }

        if (cantidad == 0 && cerrada == true) {
            return null;
        }

        tiempo = tiempo + 1;
        int mejor = 0;

        for (int i = 1; i < cantidad; i++) {
            if (pedidos[i].prioridad(tiempo)
                    > pedidos[mejor].prioridad(tiempo)) {
                mejor = i;
            }
        }

        Pedido elegido = pedidos[mejor];

        for (int i = mejor; i < cantidad - 1; i++) {
            pedidos[i] = pedidos[i + 1];
        }

        cantidad = cantidad - 1;
        return elegido;
    }

    public synchronized void cerrar() {
        cerrada = true;
        notifyAll();
    }
}