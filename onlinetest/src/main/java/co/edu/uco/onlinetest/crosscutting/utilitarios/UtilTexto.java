package co.edu.uco.onlinetest.crosscutting.utilitarios;

public final class UtilTexto {

    private static UtilTexto instancia = new UtilTexto();
    private static final String PATRON_SOLO_LETRAS = "^[a-zA-ZáÁéÉíÍóÓúÚñÑ ]+$";
    public final static String VACIO = "";

    private UtilTexto (){

    }

    public static UtilTexto  getInstance() {
        return instancia;
    }

    public boolean patronEsValido(final String valor, final String patron) {
        return obtenerValorDefecto(valor).matches(obtenerValorDefecto(patron));
    }

    public boolean contieneSoloLetrasEspacios(final String valor) {
        return patronEsValido(quitarEspacioBlancoInicioFin(valor), PATRON_SOLO_LETRAS);
    }

    public boolean esNula(final String valor) {
        return UtilObjeto.getIntance().esNulo(valor);
    }

    public boolean estaVacia(final String valor) {
        return VACIO.equals(quitarEspacioBlancoInicioFin(valor));
    }

    public String obtenerValorDefecto(final String valorOriginal, final String valorDefecto) {
        return UtilObjeto.getIntance().obtenerValorDefecto(valorOriginal, valorDefecto);
    }

    public String obtenerValorDefecto(final String valor) {
        return obtenerValorDefecto (valor, VACIO);
    }

    public String obtenerValorDefecto() {
        return VACIO;
    }

    public String quitarEspacioBlancoInicioFin(final String valor) {
        return obtenerValorDefecto(valor).trim();
    }

    public boolean esValorDefecto(final String valor) {
        return obtenerValorDefecto(valor).equals(obtenerValorDefecto());
    }

    public boolean longitudValida(final String valor, int min, int max) {
        String limpio = quitarEspacioBlancoInicioFin(valor);      // ya maneja null → ""
        int len = limpio.length();
        return len >= min && len <= max;
    }

}