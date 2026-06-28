package com.example;

public class Metricas {
    Pedido[] pedidos = new Pedido[30];
    int cantidad = 0;

    public synchronized void guardar(Pedido pedido) {
        pedidos[cantidad] = pedido;
        cantidad = cantidad + 1;
    }

    public void mostrar(Cafetera cafetera, int inicioSimulacion) {
        int esperas = 0;
        int totales = 0;
        int esperaProfesores = 0;
        int esperaEstudiantes = 0;
        int profesores = 0;
        int estudiantes = 0;
        int demorados = 0;

        System.out.println("");
        System.out.println("----- METRICAS -----");

        for (int i = 0; i < cantidad; i++) {
            int espera = pedidos[i].inicio - pedidos[i].entrada;
            int total = pedidos[i].fin - pedidos[i].entrada;

            esperas = esperas + espera;
            totales = totales + total;

            if (espera > 3000) {
                demorados = demorados + 1;
            }

            if (pedidos[i].cliente.rol.equals("PROFESOR")) {
                esperaProfesores = esperaProfesores + espera;
                profesores = profesores + 1;
            } else {
                esperaEstudiantes = esperaEstudiantes + espera;
                estudiantes = estudiantes + 1;
            }

            System.out.println("Pedido " + pedidos[i].numero
                    + ": espera " + espera + " ms");
        }

        if (cantidad > 0) {
            System.out.println("Espera promedio: "
                    + esperas / cantidad + " ms");
            System.out.println("Tiempo total promedio: "
                    + totales / cantidad + " ms");
        }

        if (profesores > 0) {
            System.out.println("Promedio profesores: "
                    + esperaProfesores / profesores + " ms");
        }

        if (estudiantes > 0) {
            System.out.println("Promedio estudiantes: "
                    + esperaEstudiantes / estudiantes + " ms");
        }

        int duracion = (int) System.currentTimeMillis() - inicioSimulacion;
        int uso = cafetera.tiempoUsada * 100 / duracion;

        System.out.println("Uso de cafetera: " + uso + "%");
        System.out.println("Pedidos demorados: " + demorados);
        System.out.println("Pedidos sin atender: "
                + (Fuente.proximo - 1 - cantidad));
    }
}
