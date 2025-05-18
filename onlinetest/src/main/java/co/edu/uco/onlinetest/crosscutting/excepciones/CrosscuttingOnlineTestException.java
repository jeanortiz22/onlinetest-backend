package co.edu.uco.onlinetest.crosscutting.excepciones;

public class CrosscuttingOnlineTestException extends OnlineTestException{

    private static final long serialVersionUID = 376198123456789L;

    protected CrosscuttingOnlineTestException(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz) {
        super(mensajeUsuario, mensajeTecnico, excepcionRaiz, LayerException.CROSSCUTTING);

    }

    public static OnlineTestException reportar(String mensajeUsuario) {
        return new CrosscuttingOnlineTestException(mensajeUsuario, mensajeUsuario, new Exception());
    }

    public static OnlineTestException reportar(String mensajeUsuario, String mensajeTecnico) {
        return new CrosscuttingOnlineTestException(mensajeUsuario, mensajeTecnico, new Exception());
    }

    public static OnlineTestException reportar(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz) {
        return new CrosscuttingOnlineTestException(mensajeUsuario, mensajeTecnico, excepcionRaiz);
    }
}
