package co.edu.uco.onlinetest.businesslogic.bussinesslogic.domain;

import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilUUID;

import java.util.UUID;

public class DepartamentoDomain {


    private UUID id;
    private String nombre;
    private PaisDomain pais;

    DepartamentoDomain() {
        setId(UtilUUID.obtenerValorDefecto());
        setNombre(UtilTexto.getInstance().obtenerValorDefecto());
        setPais(PaisDomain.obtenerValorDefecto());

    }
    public DepartamentoDomain(final UUID id) {
        setId(id);
        setNombre(UtilTexto.getInstance().obtenerValorDefecto());
        setPais(PaisDomain.obtenerValorDefecto());
    }

    public DepartamentoDomain(final UUID id, final String nombre, final PaisDomain pais) {
        setId(id);
        setNombre(nombre);
        setPais(pais);

    }


    static DepartamentoDomain obtenerValorDefecto() {
        return new DepartamentoDomain();
    }

    static DepartamentoDomain obtenerValorDefecto(final DepartamentoDomain departamento) {
        return UtilObjeto.getIntance().obtenerValorDefecto(departamento, obtenerValorDefecto());
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

    public PaisDomain getPais() {
        return pais;
    }

    private void setPais(final PaisDomain pais) {
        this.pais = PaisDomain.obtenerValorDefecto(pais);
    }


}
