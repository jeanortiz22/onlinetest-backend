package co.edu.uco.onlinetest.dto;
import java.util.UUID;

import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilUUID;

public class CiudadDTO {


    private UUID id;
    private String nombre;
    private DepartamentoDTO departamento;

    public CiudadDTO() {
        setId(UtilUUID.obtenerValorDefecto());
        setNombre(UtilTexto.getInstance().obtenerValorDefecto());
        setDepartamento(DepartamentoDTO.obtenerValorDefecto());

    }

    public CiudadDTO(final UUID id) {
        setId(id);
        setNombre(UtilTexto.getInstance().obtenerValorDefecto());
        setDepartamento(DepartamentoDTO.obtenerValorDefecto());
    }


    public CiudadDTO(final UUID id, final String nombre, final DepartamentoDTO departamento) {
        setId(id);
        setNombre(nombre);
        setDepartamento(departamento);

    }

    public static CiudadDTO obtenerValorDefecto() {
        return new CiudadDTO();
    }

    public static CiudadDTO obtenerValorDefecto(final CiudadDTO departamento) {
        return UtilObjeto.getIntance().obtenerValorDefecto(departamento, obtenerValorDefecto());
    }



    public UUID getId() {
        return id;
    }


    public CiudadDTO setId( final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
        return this;
    }


    public String getNombre() {
        return nombre;
    }


    public CiudadDTO setNombre(final String nombre) {
        this.nombre = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(nombre);
        return this;
    }

    public DepartamentoDTO getDepartamento() {
        return departamento;
    }

    public CiudadDTO setDepartamento(final DepartamentoDTO departamento) {
        this.departamento = DepartamentoDTO.obtenerValorDefecto(departamento);
        return this;
    }


}
