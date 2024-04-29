package com.example.ventas.model;

public class GananciasResponse {
    private String mensaje;
    private double ganancias;

    public GananciasResponse(String mensaje, double ganancias) {
        this.mensaje = mensaje;
        this.ganancias = ganancias;
    }

    // Getters y setters
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public double getGanancias() {
        return ganancias;
    }

    public void setGanancias(double ganancias) {
        this.ganancias = ganancias;
    }
}