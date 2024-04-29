package com.example.ventas.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class VentaTest {

    @Test
    public void crearVentaTest() {
        // Arrange
        Venta venta = new Venta();
        venta.setProducto("Comida para gatos");
        venta.setValor(15.99);
        venta.setFecha(LocalDate.now());

        // Act & Assert
        assertNotNull(venta);
        assertEquals("Comida para gatos", venta.getProducto());
        assertEquals(15.99, venta.getValor());
        assertEquals(LocalDate.now(), venta.getFecha());
    }

}