package co.com.sofka.arquitectura.limpia.model.comun.mensaje;

import co.com.sofka.arquitectura.limpia.model.persona.Persona;
import reactor.core.publisher.Mono;

import java.util.List;

public interface MensajeGateway {
    Mono<Void> publicarPersonasViajeras(List<Persona> personas);
}
