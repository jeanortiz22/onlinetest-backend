package co.edu.uco.onlinetest.api;


import co.edu.uco.onlinetest.businesslogic.facade.DepartamentoFacade;
import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;
import co.edu.uco.onlinetest.dto.DepartamentoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/departamentos")
public class DepartamentoController {

    private DepartamentoFacade departamentoFachada;

    public DepartamentoController(DepartamentoFacade departamentoFachada) {
        this.departamentoFachada = departamentoFachada;
    }

    @GetMapping("/dummy")
    public DepartamentoDTO getDummy() {
        return new DepartamentoDTO();
    }


    @GetMapping()
    public ResponseEntity<List<DepartamentoDTO>> consultar() throws OnlineTestException {
        var lista = departamentoFachada.consultarDepartamentos(getDummy());
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartamentoDTO> consultar(@PathVariable("id") UUID id) throws OnlineTestException {
        var departamento = departamentoFachada.consultarDepartamentoPorId(id);
        return new ResponseEntity<>(departamento, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> crear(@RequestBody DepartamentoDTO departamentoDTO) throws OnlineTestException {
        departamentoFachada.registrarNuevoDepartamento(departamentoDTO);
        var mensajeExito = "El departamento " + departamentoDTO.getNombre() + " se ha registrado exitosamente";
        return new ResponseEntity<>(mensajeExito, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modificar(@PathVariable("id") UUID id, @RequestBody DepartamentoDTO departamento) throws OnlineTestException {
        departamentoFachada.modificarDepartamentoExistente(id, departamento);
        var mensajeExito = "El departamento " + departamento.getNombre() + " se ha modificado exitosamente";
        return new ResponseEntity<>(mensajeExito, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") UUID id) throws OnlineTestException {
        var departamento = departamentoFachada.consultarDepartamentoPorId(id);
        departamentoFachada.darBajaDefinitivamenteDepartamentoExistente(id);
        var mensajeExito = "El departamento " + departamento.getNombre() + " se ha eliminado exitosamente";
        return new ResponseEntity<>(mensajeExito, HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<DepartamentoDTO>> consultarConFiltros(
            @RequestParam(required = false) UUID id,
            @RequestParam(required = false) String nombre
    ) throws OnlineTestException {

        DepartamentoDTO filtro = new DepartamentoDTO();
        filtro.setId(id);
        filtro.setNombre(nombre);

        var lista = departamentoFachada.consultarDepartamentos(filtro);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

}
