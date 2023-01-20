package co.com.sofka.arquitectura.limpia.api;

import co.com.sofka.arquitectura.limpia.model.comun.error.ErrorNegocio;
import co.com.sofka.arquitectura.limpia.model.persona.Persona;
import co.com.sofka.arquitectura.limpia.usecase.persona.PersonaUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class PersonasRest {
    private final PersonaUseCase personaUseCase;

    @GetMapping("/personas")
    public Flux<Persona> BuscarPersonasParaViajar() {
        return personaUseCase.buscarPersonas();
    }

    @GetMapping("/personas/{id}")
    public Mono<Persona> buscarPersona(@PathVariable Integer id) {
        return personaUseCase.buscarPersonaPorId(id);
    }

    @PostMapping("/personas")
    public Mono<Persona> guardarPersona(@RequestBody Persona persona) {
        return personaUseCase.guardarPersona(persona);
    }

    @PutMapping("/personas/{id}")
    public Mono<Persona> actualizarPersona(@RequestBody Persona persona, @PathVariable Integer id) {
        return personaUseCase.actualizarPersona(persona, id);
    }

    @DeleteMapping("/persona/{id}")
    public Mono<Void> eliminarPersona(@PathVariable Integer id) {
        return personaUseCase.eliminarPersona(id);
    }

    @GetMapping("/buscar-por-comando")
    public Mono<String> buscarPersonasPorComando() {
        return personaUseCase.buscarPersonasPorComando();
    }

    @ExceptionHandler(ErrorNegocio.class)
    public final ResponseEntity<String> handleBusinessExceptions(ErrorNegocio exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
