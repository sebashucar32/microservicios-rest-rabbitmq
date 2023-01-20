package co.com.sofka.arquitectura.limpia.model.comun.error;

import java.util.function.Supplier;

public class ErrorNegocio extends ErrorAplicacion {
    public enum Tipo {
        PERSONAS_NO_ENCONTRADAS("No se pudo encontrar ninguna persona para viajar"),
        NO_SE_ENCONTRO_NINGUNA_PERSONA_CON_EL_ID("No existe Ninguna persona con el ID Buscado"),
        PERSONA_NO_GUARDADA("NO Se pudo guardar la persona se mandaron datos incorrectos");

        private final String mensaje;

        public String getMensaje() {
            return mensaje;
        }

        public ErrorNegocio build() {
            return new ErrorNegocio(this);
        }

        public Supplier<Throwable> defer() {
            return () -> new ErrorNegocio(this);
        }

        Tipo(String mensaje) {
            this.mensaje = mensaje;
        }

    }

    private final Tipo tipo;

    public ErrorNegocio(Tipo tipo){
        super(tipo.mensaje);
        this.tipo = tipo;
    }

    public ErrorNegocio(Tipo tipo, String message) {
        super(message);
        this.tipo = tipo;
    }

    @Override
    public String getCodigo(){
        return tipo.name();
    }
}
