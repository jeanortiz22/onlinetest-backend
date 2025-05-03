package co.edu.uco.onlinetest.crosscutting.utilitarios;

public final class UtilTexto {
	
	private static UtilTexto instancia = new UtilTexto();
	public final static String VACIO = "";
	
	private UtilTexto (){
		
	}
	
	public static UtilTexto  getInstance() {
		return instancia;
	}
	
	public boolean esNula(final String valor) {
		return UtilObjeto.getIntance().esNulo(valor);
	};
	
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
	
}