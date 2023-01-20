package co.com.sofka.arquitectura.limpia.usecase.persona;

import co.com.sofka.arquitectura.limpia.model.persona.Persona;
import co.com.sofka.arquitectura.limpia.model.persona.gateways.PersonaRepository;
import co.com.sofka.arquitectura.limpia.model.viaje.Viaje;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonaUseCaseTest {
    @InjectMocks
    PersonaUseCase useCase;

    @Mock
    PersonaRepository personaRepository;

    private Persona persona;

    private Flux<Persona> personas;

    @BeforeEach
    void setUp() {
        List<Viaje> viajes = Arrays.asList(
                new Viaje(1, "Playa", "Cancun"),
                new Viaje(2, "Hotel", "Quindio")
        );

        List<Persona> personaList = new ArrayList<>();
        persona = Persona.builder()
                .cedula(1)
                .nombre("Sebastian")
                .fechaNacimiento(new Date())
                .tipoSangre("O+")
                .viajes(viajes)
                .build();

        personaList.add(persona);

        persona = Persona.builder()
                .cedula(2)
                .nombre("Rosita")
                .fechaNacimiento(new Date())
                .tipoSangre("O-")
                .viajes(viajes)
                .build();

        personaList.add(persona);
        personas = Flux.fromIterable(personaList);
    }

    @Test
    void testBuscarPersonasCasoFeliz() {
        when(personaRepository.buscarPersonas()).thenReturn(personas);

        StepVerifier.create(useCase.buscarPersonas())
                .assertNext(result -> {
                    Assertions.assertEquals(1, result.getCedula());
                    Assertions.assertEquals("Sebastian", result.getNombre());
                    Assertions.assertEquals("O+", result.getTipoSangre());
                })
                .assertNext(result -> Assertions.assertEquals(2, result.getCedula()))
                .verifyComplete();

        Mockito.verify(personaRepository, Mockito.times(1)).buscarPersonas();
    }

    @Test
    void buscarPersonas() {

    }

    @Test
    void buscarPersonaPorId() {
    }

    @Test
    void guardarPersona() {
    }
}