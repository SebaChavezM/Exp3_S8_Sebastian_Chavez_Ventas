package com.example.ventas.service;

import com.example.ventas.model.Venta;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.time.Year;
import java.time.YearMonth;

public interface VentaService {
    List<Venta> obtenerTodasLasVentas();
    Optional<Venta> obtenerVentaPorId(Long id);
    Venta crearVenta(Venta venta);
    Venta actualizarVenta(Long id, Venta venta);
    void eliminarVenta(Long id);
    double calcularGananciasPorDia(LocalDate fecha);
    double calcularGananciasPorMes(YearMonth fecha);
    double calcularGananciasPorAnio(Year fecha);
}