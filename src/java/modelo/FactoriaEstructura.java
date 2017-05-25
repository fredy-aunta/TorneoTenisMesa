package modelo;

/**
 * @author Sebasti�n
 * @version 1.0
 * @created 10-abr.-2017 11:34:17 p. m.
 */
public class FactoriaEstructura {
    public static final int ESTRUCTURA_ARBOL = 1;
    public static final int ESTRUCTURA_CUADROS = 2;

	public FactoriaEstructura(){

	}

	public static Estructura getEstructura(int idEstructura){
            Estructura estructura = null;
            switch(idEstructura) {
                case ESTRUCTURA_ARBOL:
                    estructura = new Arbol();
                    break;
                case ESTRUCTURA_CUADROS:
                    estructura =  new Cuadros();
                    break;
            }
		return estructura;
	}

	public String getTipoEstructura(){
		return "";
	}
}//end FactoriaEstructura