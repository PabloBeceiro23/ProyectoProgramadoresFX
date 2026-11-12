package com.example.proyectoprogramadores.entity;

public class Sesion {
    private static Programador programadorActual;

    public static void setProgramador(Programador programador) {
        programadorActual = programador;
    }

    public static Programador getProgramador() {
        return programadorActual;
    }

    public static void cerrarSesion() {
        programadorActual = null;
    }
}
