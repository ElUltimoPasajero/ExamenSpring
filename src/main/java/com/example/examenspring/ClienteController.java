package com.example.examenspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class ClienteController {


    @Autowired
    private ClienteRepository repository;

    @GetMapping("/all")

    public List<Cliente> getAll() {

        return repository.findAll();

    }


    @GetMapping("/{id}")
    public Cliente getClienteById(@PathVariable Integer id) {

        return repository.getClienteById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<Cliente> nuevo(@RequestBody Cliente cliente) {

        Cliente nuevoCliente = repository.save(cliente);
        return new ResponseEntity<>(nuevoCliente, HttpStatus.OK);

    }


    @GetMapping("/busquedaventaTotal/{numVentaTotal}")
    public List<Cliente> getNombreByNumeroSalasCine(@PathVariable Integer numVentaTotal) {
        List<Cliente> result = repository.getClientePorVentaTotal(numVentaTotal);
        return result;
    }


    @GetMapping("/informacionVentas")
    public ResponseEntity<List<Object[]>> obtenerInformacionVentas() {
        List<Object[]> resultados = repository.getInformacionVentas();
        return ResponseEntity.ok(resultados);
    }
}







