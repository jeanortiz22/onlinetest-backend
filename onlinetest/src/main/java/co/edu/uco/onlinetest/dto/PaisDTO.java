package co.edu.uco.onlinetest.dto;

import java.util.UUID;

import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilUUID;


public final class PaisDTO {

    private UUID id;
    private String nombre;

    public PaisDTO() {
        setId(UtilUUID.obtenerValorDefecto());
        setNombre(UtilTexto.getInstance().obtenerValorDefecto());

    }

    public PaisDTO(final UUID id) {
        setId(id);
        setNombre(UtilTexto.getInstance().obtenerValorDefecto());

    }

    public PaisDTO(final UUID id, final String nombre) {
        setId(id);
        setNombre(nombre);

    }

    public static PaisDTO obtenerValorDefecto() {
        return new PaisDTO();
    }

    public static PaisDTO obtenerValorDefecto(final PaisDTO pais) {
        return UtilObjeto.getIntance().obtenerValorDefecto(pais, obtenerValorDefecto());
    }

    public UUID getId() {
        return id;
    }


    public PaisDTO setId( final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
        return this;
    }


    public String getNombre() {
        return nombre;
    }


    public PaisDTO setNombre(final String nombre) {
        this.nombre = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(nombre);
        return this;
    }
}

