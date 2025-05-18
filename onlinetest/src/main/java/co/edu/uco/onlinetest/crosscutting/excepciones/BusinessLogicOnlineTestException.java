package co.edu.uco.onlinetest.crosscutting.excepciones;

public class BusinessLogicOnlineTestException extends OnlineTestException{

    private static final long serialVersionUID = 376198123456789L;

    protected BusinessLogicOnlineTestException(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz) {
        super(mensajeUsuario, mensajeTecnico, excepcionRaiz, LayerException.BUSINESS_LOGIC);

    }

    public static OnlineTestException reportar(String mensajeUsuario) {
        return new BusinessLogicOnlineTestException(mensajeUsuario, mensajeUsuario, new Exception());
    }

    public static OnlineTestException reportar(String mensajeUsuario, String mensajeTecnico) {
        return new BusinessLogicOnlineTestException(mensajeUsuario, mensajeTecnico, new Exception());
    }

    public static OnlineTestException reportar(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz) {
        return new BusinessLogicOnlineTestException(mensajeUsuario, mensajeTecnico, excepcionRaiz);
    }
}
