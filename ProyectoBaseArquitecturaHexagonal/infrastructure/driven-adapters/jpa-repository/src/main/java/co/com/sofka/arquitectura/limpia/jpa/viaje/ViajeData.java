package co.com.sofka.arquitectura.limpia.jpa.viaje;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "VIAJE")
public class ViajeData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_viaje")
    private Integer idViaje;

    @Column(name = "tipo_viaje")
    private String tipoViaje;

    @Column(name = "nombre_viaje")
    private String nombreViaje;
}
