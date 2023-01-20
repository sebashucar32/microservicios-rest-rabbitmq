package co.com.sofka.arquitectura.limpia.api;
import co.com.sofka.arquitectura.limpia.model.persona.Persona;
import co.com.sofka.arquitectura.limpia.usecase.persona.PersonaUseCase;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonasRest {
    private final PersonaUseCase personaUseCase;

    @GetMapping("/personas")
    public Flux<Persona> buscarPersonasParaViajar() {
        return personaUseCase.buscarPersonas();
    }

    @GetMapping("/personas/{id}")
    public Mono<Persona> buscarPersonas(@PathVariable Integer id) {
        return personaUseCase.buscarPersonaPorId(id);
    }

    @PostMapping("/personas")
    public Mono<Persona> guardarPersona(@RequestBody Persona persona) {
        return personaUseCase.guardarPersona(persona);
    }

    @GetMapping("/buscar-por-comando")
    public Mono<String> buscarPersonasPorComando() {
        return personaUseCase.buscarPersonasPorComando();
    }

    @PostMapping("/enviar-persona-al-otro-proyecto")
    public Mono<Persona> enviarPersonaAlOtroProyecto(@RequestBody Persona persona) {
        return personaUseCase.enviarPersona(persona);
    }
}
