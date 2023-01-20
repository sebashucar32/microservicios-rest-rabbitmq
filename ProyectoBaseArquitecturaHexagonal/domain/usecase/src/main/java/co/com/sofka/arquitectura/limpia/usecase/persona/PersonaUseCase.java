package co.com.sofka.arquitectura.limpia.usecase.persona;

import co.com.sofka.arquitectura.limpia.model.comun.error.ErrorNegocio;
import co.com.sofka.arquitectura.limpia.model.persona.Persona;
import co.com.sofka.arquitectura.limpia.model.persona.gateways.PersonaPublicador;
import co.com.sofka.arquitectura.limpia.model.persona.gateways.PersonaRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static co.com.sofka.arquitectura.limpia.model.comun.error.ErrorNegocio.Tipo.*;

@RequiredArgsConstructor
public class PersonaUseCase {
    private final PersonaRepository personaRepository;
    private final PersonaPublicador personaPublicador;  //windows + g

    public Flux<Persona> buscarPersonas() {
        return personaRepository.buscarPersonas()
                .switchIfEmpty(Mono.error(new ErrorNegocio(PERSONAS_NO_ENCONTRADAS)));
    }

    public Mono<Persona> guardarPersona(Persona persona) {
        return personaRepository.guardarPersona(persona)
                .switchIfEmpty(Mono.error(new ErrorNegocio(PERSONA_NO_GUARDADA)));
    }

    public Mono<Persona> buscarPersonaPorId(Integer id) {
        return personaRepository.buscarPersonaPorId(id)
                .switchIfEmpty(Mono.error(new ErrorNegocio(NO_SE_ENCONTRO_NINGUNA_PERSONA_CON_EL_ID)));
    }

    public Mono<Persona> actualizarPersona(Persona persona, Integer id) {
        return personaRepository.actualizarPersona(persona, id);
    }

    public Mono<Void> eliminarPersona(Integer id) {
        return personaRepository.eliminarPersona(id);
    }

    public Mono<String> buscarPersonasPorComando() {
        return personaPublicador.publicarPersonasViajeras();
    }
}
