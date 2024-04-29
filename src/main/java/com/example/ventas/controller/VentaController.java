package com.example.ventas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ventas.model.GananciasResponse;
import com.example.ventas.model.Venta;
import com.example.ventas.service.VentaService;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    private static final Logger log = LoggerFactory.getLogger(VentaController.class);

    @Autowired
    private VentaService ventaServicio;

    @GetMapping
    public CollectionModel<EntityModel<Venta>> obtenerTodasLasVentas() {
        List<Venta> ventas = ventaServicio.obtenerTodasLasVentas();
        log.info("GET /ventas");
        log.info("Retornando todas las ventas");
        List<EntityModel<Venta>> ventaResources = ventas.stream()
            .map(venta -> EntityModel.of(venta,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getVentaPorId(venta.getId())).withSelfRel()
            ))
            .collect(Collectors.toList());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).obtenerTodasLasVentas());
        return CollectionModel.of(ventaResources, linkTo.withRel("ventas"));
    }

    @GetMapping("/{id}")
    public EntityModel<Venta> getVentaPorId(@PathVariable Long id) {
        Optional<Venta> venta = ventaServicio.obtenerVentaPorId(id);

        if (venta.isPresent()) {
            return EntityModel.of(venta.get(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getVentaPorId(id)).withSelfRel());
        } else {
            throw new VentaNotFoundException("Venta no encontrada con id: " + id);
        }
    }

    @PostMapping
    public EntityModel<Venta> crearVenta(@Validated @RequestBody Venta venta) {
        Venta ventaCreada = ventaServicio.crearVenta(venta);
        return EntityModel.of(ventaCreada,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getVentaPorId(ventaCreada.getId())).withSelfRel());
    }

    @PutMapping("/{id}")
    public EntityModel<Venta> actualizarVenta(@PathVariable Long id, @RequestBody Venta venta) {
        Venta ventaActualizada = ventaServicio.actualizarVenta(id, venta);
        return EntityModel.of(ventaActualizada,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getVentaPorId(id)).withSelfRel());
    }

    @DeleteMapping("/{id}")
    public void eliminarVenta(@PathVariable Long id) {
        ventaServicio.eliminarVenta(id);
    }

    @GetMapping("/ganancias-diarias")
    public ResponseEntity<GananciasResponse> obtenerGananciasDiarias() {
        LocalDate fechaHoy = LocalDate.now();
        double gananciasDiarias = ventaServicio.calcularGananciasPorDia(fechaHoy);
        String mensaje = "Estas son las ganancias diarias";
        GananciasResponse response = new GananciasResponse(mensaje, gananciasDiarias);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/ganancias-mensuales")
    public ResponseEntity<GananciasResponse> obtenerGananciasMensuales() {
        YearMonth esteMes = YearMonth.now();
        double gananciasMensuales = ventaServicio.calcularGananciasPorMes(esteMes);
        String mensaje = "Estas son las ganancias mensuales";
        GananciasResponse response = new GananciasResponse(mensaje, gananciasMensuales);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/ganancias-anuales")
    public ResponseEntity<GananciasResponse> obtenerGananciasAnuales() {
        Year esteAnio = Year.now();
        double gananciasAnuales = ventaServicio.calcularGananciasPorAnio(esteAnio);
        String mensaje = "Estas son las ganancias anuales";
        GananciasResponse response = new GananciasResponse(mensaje, gananciasAnuales);
        return ResponseEntity.ok(response);
    }


}
