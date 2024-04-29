package com.example.ventas.service;

import com.example.ventas.model.Venta;
import com.example.ventas.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Service
public class VentaServiceImpl implements VentaService {
    @Autowired
    private VentaRepository ventaRepository;

    @Override
    public List<Venta> obtenerTodasLasVentas() {
        return ventaRepository.findAll();
    }

    @Override
    public Optional<Venta> obtenerVentaPorId(Long id) {
        return ventaRepository.findById(id);
    }

    @Override
    public Venta crearVenta(Venta venta) {
        return ventaRepository.save(venta);
    }

    @Override
    public Venta actualizarVenta(Long id, Venta venta) {
        Venta ventaExistente = ventaRepository.findById(id).orElse(null);
        if (ventaExistente == null) {
            throw new RuntimeException("Venta no encontrada con ID: " + id);
        }

        ventaExistente.setProducto(venta.getProducto());
        ventaExistente.setValor(venta.getValor());
        ventaExistente.setFecha(venta.getFecha());

        return ventaRepository.save(ventaExistente);
    }

    @Override
    public void eliminarVenta(Long id) {
        ventaRepository.deleteById(id);
    }

    @Override
    public double calcularGananciasPorDia(LocalDate fecha) {
        List<Venta> ventasDelDia = ventaRepository.findByFecha(fecha);
        return ventasDelDia.stream().mapToDouble(Venta::getValor).sum();
    }

    @Override
    public double calcularGananciasPorMes(YearMonth fecha) {
        LocalDate inicioMes = fecha.atDay(1);
        LocalDate finMes = fecha.atEndOfMonth();
        List<Venta> ventasDelMes = ventaRepository.findByFechaBetween(inicioMes, finMes);
        return ventasDelMes.stream().mapToDouble(Venta::getValor).sum();
    }

    @Override
    public double calcularGananciasPorAnio(Year fecha) {
        LocalDate inicioAnio = fecha.atDay(1);
        LocalDate finAnio = fecha.atMonth(12).atEndOfMonth();
        List<Venta> ventasDelAnio = ventaRepository.findByFechaBetween(inicioAnio, finAnio);
        return ventasDelAnio.stream().mapToDouble(Venta::getValor).sum();
    }

}
