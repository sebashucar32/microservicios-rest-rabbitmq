package co.com.sofka.arquitectura.limpia.model.persona.gateways;

import co.com.sofka.arquitectura.limpia.model.persona.Persona;
import reactor.core.publisher.Mono;

public interface PersonaPublicador {
    Mono<String> publicarPersonasViajeras();
    Mono<Persona> publicarPersonaParaGuardar(Persona persona);
}
