package co.com.sofka.arquitectura.limpia.model.comun.error;

public class ErrorAplicacion extends RuntimeException {
    private final String codigo;

    public ErrorAplicacion(String mensaje) {
        this(mensaje, null);
    }

    public ErrorAplicacion(String message, String codigo) {
        super(message);
        this.codigo = codigo;
    }

    public String getCodigo(){
        return codigo;
    }
}
