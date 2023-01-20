package co.com.sofka.arquitectura.limpia.model.persona;

import co.com.sofka.arquitectura.limpia.model.comun.evento.Evento;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PersonasViajeras implements Evento {
    public static final String NOMBRE_EVENTO = "personas.viajeras";

    private final String datos;

    @Override
    public String nombre() {
        return NOMBRE_EVENTO;
    }
}
