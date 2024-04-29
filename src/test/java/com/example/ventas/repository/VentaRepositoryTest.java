package com.example.ventas.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.ventas.model.Venta;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VentaRepositoryTest {

    @Autowired
    private VentaRepository ventaRepository;

    @Test
    public void guardarVentaTest() {

        // Arrage
        Venta venta = new Venta();
        venta.setProducto("Comida para gatos");
        venta.setValor(15.99);
        venta.setFecha(LocalDate.now());

        // Act
        Venta resultado = ventaRepository.save(venta);

        // Assert
        assertNotNull(resultado.getId());
        assertEquals("Comida para gatos", resultado.getProducto());
    }
}
