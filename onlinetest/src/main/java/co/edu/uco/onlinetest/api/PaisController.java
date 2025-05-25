package co.edu.uco.onlinetest.api;


import co.edu.uco.onlinetest.businesslogic.facade.PaisFacade;
import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;
import co.edu.uco.onlinetest.dto.PaisDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/paises")
public class PaisController {

    private PaisFacade paisFachada;

    public PaisController (PaisFacade paisFachada) {
        this.paisFachada = paisFachada;
    }

    @GetMapping ("/dummy")
    public PaisDTO getDummy() {
        return new PaisDTO();
    }


    @GetMapping()
    public ResponseEntity<List<PaisDTO>> consultar() throws OnlineTestException {
        var lista = paisFachada.consultarPaises(getDummy());
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaisDTO> consultar(@PathVariable("id") UUID id) throws OnlineTestException {
        var pais = paisFachada.consultarPaisPorId(id);
        return new ResponseEntity<>(pais, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> crear( @RequestBody PaisDTO paisDTO) throws OnlineTestException {
        paisFachada.registrarNuevoPais(paisDTO);
        var mensajeExito = "El pais " + paisDTO.getNombre() + " se ha registrado exitosamente";
        return new ResponseEntity<>(mensajeExito, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modificar(@PathVariable("id") UUID id, @RequestBody PaisDTO pais) throws OnlineTestException {
        paisFachada.modificarPaisExistente(id,pais);
        var mensajeExito = "El pais " + pais.getNombre() + " se ha modificado exitosamente";
        return new ResponseEntity<>(mensajeExito, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable ("id") UUID id) throws OnlineTestException {

        var pais = paisFachada.consultarPaisPorId(id);
        paisFachada.darBajaDefinitivamentePaisExistente(id);
        var mensajeExito = "El pais " + pais.getNombre() + " se ha eliminado exitosamente";
        return new ResponseEntity<>(mensajeExito, HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<PaisDTO>> consultarConFiltros(
            @RequestParam(required = false) UUID id,
            @RequestParam(required = false) String nombre
    ) throws OnlineTestException {

        PaisDTO filtro = new PaisDTO();
        filtro.setId(id);
        filtro.setNombre(nombre);

        var lista = paisFachada.consultarPaises(filtro);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }


}
