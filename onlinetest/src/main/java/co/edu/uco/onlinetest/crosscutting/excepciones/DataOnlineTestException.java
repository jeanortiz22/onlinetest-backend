package co.edu.uco.onlinetest.crosscutting.excepciones;

public class DataOnlineTestException extends OnlineTestException{

    private static final long serialVersionUID = 376198123456789L;

    private DataOnlineTestException(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz) {
        super(mensajeUsuario, mensajeTecnico, excepcionRaiz, LayerException.DATA);

    }

    public static OnlineTestException reportar(String mensajeUsuario) {
        return new DataOnlineTestException(mensajeUsuario, mensajeUsuario, new Exception());
    }

    public static OnlineTestException reportar(String mensajeUsuario, String mensajeTecnico) {
        return new DataOnlineTestException(mensajeUsuario, mensajeTecnico, new Exception());
    }

    public static OnlineTestException reportar(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz) {
        return new DataOnlineTestException(mensajeUsuario, mensajeTecnico, excepcionRaiz);
    }
}
