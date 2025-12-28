package com.daniel_vasquez.veterinaria.controller;

import com.daniel_vasquez.veterinaria.model.Mascota;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/mascotas")
public class MascotaController {

    private List<Mascota> veterinaria = new ArrayList<>();

    public MascotaController() {
        veterinaria.add(new Mascota(1L, "Firulais", "Perro", LocalDate.of(2020, 5, 20), "Juan Perez")); // Tiene ~5 años
        veterinaria.add(new Mascota(2L, "Mishi", "Gato", LocalDate.of(2023, 1, 15), "Maria Lopez"));    // Tiene ~2 años
        veterinaria.add(new Mascota(3L, "Rex", "Perro", LocalDate.of(2015, 8, 10), "Carlos Ruiz"));     // Tiene ~10 años
    }

    @GetMapping
    public ResponseEntity<List<Mascota>> obtenerMascotas() {
        return new ResponseEntity<>(veterinaria, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mascota> obtenerMascotaPorId(@PathVariable Long id) {
        Optional<Mascota> mascota = veterinaria.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst();

        if (mascota.isPresent()) {
            return new ResponseEntity<>(mascota.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/menores")
    public ResponseEntity<List<Mascota>> buscarMascotasMenores(@RequestParam int edad) {
        List<Mascota> resultado = veterinaria.stream()
                .filter(m -> {
                    int edadMascota = Period.between(m.getFechaNacimiento(), LocalDate.now()).getYears();
                    return edadMascota < edad;
                })
                .collect(Collectors.toList());

        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Mascota> crearMascota(@RequestBody Mascota nuevaMascota) {
        nuevaMascota.setId((long) (veterinaria.size() + 1));
        veterinaria.add(nuevaMascota);
        return new ResponseEntity<>(nuevaMascota, HttpStatus.CREATED);
    }
}