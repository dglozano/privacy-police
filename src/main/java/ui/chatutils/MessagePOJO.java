package ui.chatutils;

public class MessagePOJO {

    public enum TipoMensaje{
        CARTOY, RESPUESTA_AGENTE;
    }

    private String mensaje;
    private TipoMensaje tipoMensaje;

    public MessagePOJO() {
    }

    public MessagePOJO(String mensaje, TipoMensaje tipoMensaje) {
        this.mensaje = mensaje;
        this.tipoMensaje = tipoMensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public TipoMensaje getTipoMensaje() {
        return tipoMensaje;
    }

    public void setTipoMensaje(TipoMensaje tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
    }
}
