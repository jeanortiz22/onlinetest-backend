package co.edu.uco.onlinetest.api;


import co.edu.uco.onlinetest.businesslogic.facade.CiudadFacade;
import co.edu.uco.onlinetest.businesslogic.facade.DepartamentoFacade;
import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;
import co.edu.uco.onlinetest.dto.CiudadDTO;
import co.edu.uco.onlinetest.dto.DepartamentoDTO;
import co.edu.uco.onlinetest.dto.PaisDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/ciudades")
public class CiudadController {

    private CiudadFacade ciudadFachada;

    public CiudadController(CiudadFacade ciudadFachada) {
        this.ciudadFachada = ciudadFachada;
    }

    @GetMapping("/dummy")
    public CiudadDTO getDummy() {
        return new CiudadDTO();
    }


    @GetMapping()
    public ResponseEntity<List<CiudadDTO>> consultar() throws OnlineTestException {
        var lista = ciudadFachada.consultarCiudades(getDummy());
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CiudadDTO> consultar(@PathVariable("id") UUID id) throws OnlineTestException {
        var ciudad = ciudadFachada.consultarCiudadPorId(id);
        return new ResponseEntity<>(ciudad, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> crear(@RequestBody CiudadDTO ciudadDTO) throws OnlineTestException {
        ciudadFachada.registrarNuevaCiudad(ciudadDTO);
        var mensajeExito = "La ciudad " + ciudadDTO.getNombre() + " se ha registrado exitosamente";
        return new ResponseEntity<>(mensajeExito, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modificar(@PathVariable("id") UUID id, @RequestBody CiudadDTO ciudad) throws OnlineTestException {
        ciudadFachada.modificarCiudadExistente(id, ciudad);
        var mensajeExito = "La ciudad " + ciudad.getNombre() + " se ha modificado exitosamente";
        return new ResponseEntity<>(mensajeExito, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") UUID id) throws OnlineTestException {
        var ciudad = ciudadFachada.consultarCiudadPorId(id);
        ciudadFachada.darBajaDefinitivamenteCiudadExistente(id);
        var mensajeExito = "La ciudad " + ciudad.getNombre() + " se ha eliminado exitosamente";
        return new ResponseEntity<>(mensajeExito, HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<CiudadDTO>> consultarConFiltros(
            @RequestParam(required = false) UUID id,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) UUID idDepartamento
    ) throws OnlineTestException {
        DepartamentoDTO departamento = new DepartamentoDTO();
        departamento.setId(idDepartamento);

        CiudadDTO filtro = new CiudadDTO();
        filtro.setId(id);
        filtro.setNombre(nombre);

        var lista = ciudadFachada.consultarCiudades(filtro);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

}
