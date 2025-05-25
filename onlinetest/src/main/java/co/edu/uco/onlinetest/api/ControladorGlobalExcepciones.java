package co.edu.uco.onlinetest.api;

import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControladorGlobalExcepciones {

    @ExceptionHandler(OnlineTestException.class)
    public ResponseEntity<String> controlarOnlineTestException(OnlineTestException exception) {
        exception.printStackTrace();
        return new ResponseEntity<>(exception.getMensajeUsuario(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> controlarException(Exception exception) {
        exception.printStackTrace();
        return new ResponseEntity<>("Se ha presentado un problema inesperado tratando de llevar a cabo la operación deseada ", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
