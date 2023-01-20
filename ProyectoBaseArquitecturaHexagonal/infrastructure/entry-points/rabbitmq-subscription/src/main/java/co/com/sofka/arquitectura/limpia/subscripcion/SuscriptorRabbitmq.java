package co.com.sofka.arquitectura.limpia.subscripcion;

import co.com.sofka.arquitectura.limpia.model.persona.Persona;
import co.com.sofka.arquitectura.limpia.usecase.persona.PersonaUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.reactivecommons.async.api.HandlerRegistry;
import org.reactivecommons.async.impl.config.annotations.EnableCommandListeners;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Log
@Configuration
@EnableCommandListeners
@RequiredArgsConstructor
public class SuscriptorRabbitmq {
    private final PersonaUseCase personaUseCase;

    static final String PERSONAS_VIAJERAS = "personas.viajeras";
    static final String PERSONAS_GUARDADA = "personas.guardada";

    @Bean
    public HandlerRegistry comandoSuscriptor() {
        return HandlerRegistry.register().handleCommand(PERSONAS_VIAJERAS, p -> {
            personaUseCase.buscarPersonas();
            return Mono.empty();
        }, String.class);
    }

    @Bean
    public HandlerRegistry RecibirPersonaParaGuardar() {
        return HandlerRegistry.register().handleCommand(PERSONAS_GUARDADA, comando -> {
            personaUseCase.guardarPersona(comando.getData());
            return Mono.empty();
        }, Persona.class);
    }
}
