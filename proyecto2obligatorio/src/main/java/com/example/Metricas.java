package com.example;

public class Metricas {
    static final int UMBRAL = 3000;

    Pedido[] pedidos = new Pedido[30];
    int cantidad = 0;

    public synchronized void guardar(Pedido pedido) {
        pedidos[cantidad] = pedido;
        cantidad = cantidad + 1;
    }

    public void mostrar(Cafetera cafetera, long inicioSimulacion) {
        long esperas = 0;
        long totales = 0;
        long esperaProfesores = 0;
        long esperaEstudiantes = 0;
        int profesores = 0;
        int estudiantes = 0;
        int demorados = 0;

        System.out.println("");
        System.out.println("METRICAS");

        for (int i = 0; i < cantidad; i++) {
            long espera = pedidos[i].inicio - pedidos[i].entrada;
            long total = pedidos[i].fin - pedidos[i].entrada;

            esperas = esperas + espera;
            totales = totales + total;

            if (espera > UMBRAL) {
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
                    + " (" + pedidos[i].cliente.rol + ", "
                    + pedidos[i].producto + "): espera " + espera + " ms");
        }

        if (cantidad > 0) {
            System.out.println("Espera promedio: "
                    + esperas / cantidad + " ms");
            System.out.println("Tiempo total promedio: "
                    + totales / cantidad + " ms");
        }

        if (profesores > 0) {
            System.out.println("Espera promedio profesores: "
                    + esperaProfesores / profesores + " ms");
        }

        if (estudiantes > 0) {
            System.out.println("Espera promedio estudiantes: "
                    + esperaEstudiantes / estudiantes + " ms");
        }

        long duracion = System.currentTimeMillis() - inicioSimulacion;
        if (duracion > 0) {
            long uso = cafetera.tiempoUsada * 100 / duracion;
            System.out.println("Uso de cafetera: " + uso + "%");
        }

        System.out.println("Pedidos que superaron el umbral de "
                + UMBRAL + " ms: " + demorados);
    }
}
