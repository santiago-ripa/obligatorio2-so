package com.example;

public class Fuente extends Thread {
    static int proximo = 1;
    int origen;
    Cola cola;

    public Fuente(int origen, Cola cola) {
        this.origen = origen;
        this.cola = cola;
    }

    public static synchronized int siguienteNumero() {
        int numero = proximo;
        proximo = proximo + 1;
        return numero;
    }

    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(200 * origen);

                int numero = siguienteNumero();
                int profesor = 0;
                int cafe = 0;

                if (numero % 4 == 0) {
                    profesor = 1;
                }

                if (numero % 2 == 0) {
                    cafe = 1;
                }

                Pedido pedido = new Pedido(numero, profesor,
                        numero % 3, cafe, origen);
                cola.poner(pedido);
            }
        } catch (InterruptedException e) {
            System.out.println("Fuente interrumpida");
        }
    }
}
