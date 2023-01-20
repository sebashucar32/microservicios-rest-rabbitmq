package co.com.sofka.arquitectura.limpia.rabbitmq.publisher;

import co.com.sofka.arquitectura.limpia.model.persona.Persona;
import co.com.sofka.arquitectura.limpia.model.persona.gateways.PersonaPublicador;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.api.domain.Command;
import org.reactivecommons.async.api.DirectAsyncGateway;
import org.reactivecommons.async.impl.config.annotations.EnableDirectAsyncGateway;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@EnableDirectAsyncGateway
@RequiredArgsConstructor
public class PublicadorRabbitmq implements PersonaPublicador {
    private final DirectAsyncGateway disparadorComando;

    static final String PERSONAS_VIAJERAS = "personas.viajeras";
    static final String PERSONAS_GUARDADA = "personas.guardada";

    @Override
    public Mono<String> publicarPersonasViajeras() {
        final Command<String> comando = new Command<>(
                PERSONAS_VIAJERAS,
                UUID.randomUUID().toString(),
                "hola"
        );

        return disparadorComando.sendCommand(
            comando, "proyecto-arquitectura-hexagonal")
            .thenReturn("mensaje publicado");
    }

    @Override
    public Mono<Persona> publicarPersonaParaGuardar(Persona persona) {
        final Command<Persona> comandoPersonaParaEnviar = new Command<>(
                PERSONAS_GUARDADA,
                UUID.randomUUID().toString(),
                persona
        );

        return disparadorComando.sendCommand(
            comandoPersonaParaEnviar, "ejemplo-arquitectura-hexagonal")
                .thenReturn(persona);
    }

}
