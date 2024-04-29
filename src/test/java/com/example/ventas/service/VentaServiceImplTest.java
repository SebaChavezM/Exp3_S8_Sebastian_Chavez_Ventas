package com.example.ventas.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.ventas.model.Venta;
import com.example.ventas.repository.VentaRepository;

@ExtendWith(MockitoExtension.class)
public class VentaServiceImplTest {

    @Mock
    private VentaRepository ventaRepository;

    @InjectMocks
    private VentaServiceImpl ventaService;

    @Test
    public void obtenerTodasLasVentasTest() {
        // Arrange
        Venta venta1 = new Venta();
        venta1.setId(1L);
        venta1.setProducto("Producto 1");
        venta1.setValor(10.0);
        venta1.setFecha(LocalDate.now());

        Venta venta2 = new Venta();
        venta2.setId(2L);
        venta2.setProducto("Producto 2");
        venta2.setValor(20.0);
        venta2.setFecha(LocalDate.now());

        List<Venta> ventas = Arrays.asList(venta1, venta2);

        // Act
        when(ventaRepository.findAll()).thenReturn(ventas);
        List<Venta> result = ventaService.obtenerTodasLasVentas();

        // Assert
        assertEquals(2, result.size());
    }

}
