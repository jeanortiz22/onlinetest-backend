package co.edu.uco.onlinetest.crosscutting.excepciones;

import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilTexto;

public class OnlineTestException extends Exception{

    private static final long serialVersionUID = 376198123456789L;

    private String mensajeUsuario;
    private LayerException capa;

    protected OnlineTestException(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz, LayerException capa) {
        super(mensajeTecnico, excepcionRaiz);
        setMensajeUsuario(mensajeUsuario);
        setCapa(capa);
    }

    public String getMensajeUsuario() {
        return mensajeUsuario;
    }

    private void setMensajeUsuario(String mensajeUsuario) {
        this.mensajeUsuario = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(mensajeUsuario);
    }

    public String getMensajeTecnico() {
        return UtilTexto.getInstance().obtenerValorDefecto(getMessage());
    }

    public Throwable getExcepcionRaiz() {
        return UtilObjeto.getIntance().obtenerValorDefecto(getCause(), new Exception(getMensajeUsuario()));
    }


    public LayerException getCapa() {
        return capa;
    }

    private void setCapa(LayerException capa) {
        this.capa = UtilObjeto.getIntance().obtenerValorDefecto(capa, LayerException.GENERAL);
    }

}
