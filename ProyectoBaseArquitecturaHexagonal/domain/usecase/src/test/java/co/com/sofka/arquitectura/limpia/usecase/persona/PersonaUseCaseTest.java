package co.com.sofka.arquitectura.limpia.usecase.persona;

import co.com.sofka.arquitectura.limpia.model.persona.Persona;
import co.com.sofka.arquitectura.limpia.model.persona.gateways.PersonaRepository;
import co.com.sofka.arquitectura.limpia.model.viaje.Viaje;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static reactor.core.publisher.Mono.when;

@ExtendWith(MockitoExtension.class)
class PersonaUseCaseTest {
    @InjectMocks
    PersonaUseCase useCase;

    @Mock
    PersonaRepository personaRepository;

    private Persona persona;

    @BeforeEach
    void setUp() {
        List<Viaje> viajes = Arrays.asList(
                new Viaje(1, "Playa", "Cancun"),
                new Viaje(2, "Hotel", "Quindio")
        );

        persona = Persona.builder()
            .cedula(1)
            .nombre("Sebastian")
            .fechaNacimiento(new Date())
            .tipoSangre("O+")
            .viaje(viajes)
            .build();
    }

    @Test
    void testBuscarPersonasCasoFeliz() {
        when(personaRepository.buscarPersonas()).thenReturn(Flux.just(persona));

        StepVerifier.create(useCase.buscarPersonas()).verifyComplete();

        Mockito.verify(personaRepository, Mockito.times(1)).buscarPersonas();
    }

    @Test
    void guardarPersona() {

    }

    @Test
    void buscarPersonaPorId() {

    }
}