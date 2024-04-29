package com.example.ventas.controller;

import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.example.ventas.model.Venta;
import com.example.ventas.service.VentaServiceImpl;

@WebMvcTest(VentaController.class)
public class VentaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VentaServiceImpl ventaServicioMock;

    @Test
    public void obtenerTodasTest() throws Exception {
        Venta venta1 = new Venta();
        venta1.setId(1L);
        venta1.setProducto("Comida para gatos");
        venta1.setValor(15.99);
    
        Venta venta2 = new Venta();
        venta2.setId(2L);
        venta2.setProducto("Juguete para perros");
        venta2.setValor(10.50);
    
        List<Venta> ventas = Arrays.asList(venta1, venta2);
        when(ventaServicioMock.obtenerTodasLasVentas()).thenReturn(ventas);
    
        mockMvc.perform(MockMvcRequestBuilders.get("/ventas"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.ventaList", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.ventaList[0].producto", Matchers.is("Comida para gatos")))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.ventaList[1].producto", Matchers.is("Juguete para perros")));
    }
}
