package co.edu.uco.onlinetest.businesslogic.bussinesslogic.domain;

import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilUUID;

import java.util.UUID;

public class CiudadDomain {


    private UUID id;
    private String nombre;
    private DepartamentoDomain departamento;

    CiudadDomain() {
        setId(UtilUUID.obtenerValorDefecto());
        setNombre(UtilTexto.getInstance().obtenerValorDefecto());
        setDepartamento(DepartamentoDomain.obtenerValorDefecto());

    }

    public CiudadDomain(final UUID id, final String nombre, final DepartamentoDomain departamento) {
        setId(id);
        setNombre(nombre);
        setDepartamento(departamento);

    }


    static CiudadDomain obtenerValorDefecto() {
        return new CiudadDomain();
    }
    static CiudadDomain obtenerValorDefecto(final CiudadDomain ciudad) {
        return UtilObjeto.getIntance().obtenerValorDefecto(ciudad, obtenerValorDefecto());
    }


    public CiudadDomain(final UUID id) {
        setId(id);
        setNombre(UtilTexto.getInstance().obtenerValorDefecto());
        setDepartamento(DepartamentoDomain.obtenerValorDefecto());
    }


    public UUID getId() {
        return id;
    }


    private void setId( final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
    }


    public String getNombre() {
        return nombre;
    }


    private void setNombre(final String nombre) {
        this.nombre = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(nombre);
    }

    public DepartamentoDomain getDepartamento() {
        return departamento;
    }

    private void setDepartamento(final DepartamentoDomain departamento) {
        this.departamento = DepartamentoDomain.obtenerValorDefecto(departamento);
    }

}
