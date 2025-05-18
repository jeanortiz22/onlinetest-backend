package co.edu.uco.onlinetest.crosscutting.excepciones;

public class ApiOnlineTestException extends OnlineTestException{

    private static final long serialVersionUID = 376198123456789L;

    protected ApiOnlineTestException(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz) {
        super(mensajeUsuario, mensajeTecnico, excepcionRaiz, LayerException.API);

    }

    public static OnlineTestException reportar(String mensajeUsuario) {
        return new ApiOnlineTestException(mensajeUsuario, mensajeUsuario, new Exception());
    }

    public static OnlineTestException reportar(String mensajeUsuario, String mensajeTecnico) {
        return new ApiOnlineTestException(mensajeUsuario, mensajeTecnico, new Exception());
    }

    public static OnlineTestException reportar(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz) {
        return new ApiOnlineTestException(mensajeUsuario, mensajeTecnico, excepcionRaiz);
    }
}
