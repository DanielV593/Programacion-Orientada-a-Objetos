package com.daniel_vasquez.biblioteca.controller;

import com.daniel_vasquez.biblioteca.model.Libro;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/libros")
public class LibroController {

    private List<Libro> biblioteca = new ArrayList<>();

    public LibroController() {
        biblioteca.add(new Libro(1L, "123-45", "Cien Años de Soledad", "Gabriel Garcia Marquez", 1967, true));
        biblioteca.add(new Libro(2L, "678-90", "El Principito", "Antoine de Saint-Exupéry", 1943, true));
        biblioteca.add(new Libro(3L, "111-22", "Crónica de una muerte anunciada", "Gabriel Garcia Marquez", 1981, true));
    }

    @GetMapping
    public ResponseEntity<List<Libro>> obtenerLibros() {
        return new ResponseEntity<>(biblioteca, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerLibroPorId(@PathVariable Long id) {
        Optional<Libro> libroEncontrado = biblioteca.stream()
                .filter(libro -> libro.getId().equals(id))
                .findFirst();

        if (libroEncontrado.isPresent()) {
            return new ResponseEntity<>(libroEncontrado.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Libro>> buscarLibros(
            @RequestParam String autor,
            @RequestParam(required = false) Integer anio) {

        List<Libro> resultados = biblioteca.stream()
                .filter(l -> l.getAutor().equalsIgnoreCase(autor))

                .filter(l -> anio == null || l.getAnioPublicacion().equals(anio))
                .collect(Collectors.toList());

        return new ResponseEntity<>(resultados, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro nuevoLibro) {
        nuevoLibro.setId((long) (biblioteca.size() + 1));
        biblioteca.add(nuevoLibro);

        return new ResponseEntity<>(nuevoLibro, HttpStatus.CREATED);
    }
}