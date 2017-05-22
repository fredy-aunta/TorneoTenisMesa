package modelo;

/**
 * @author Sebasti�n
 * @version 1.0
 * @created 10-abr.-2017 11:34:17 p. m.
 */
public class Cuadros extends Estructura {

	public Cuadros(){
            this.setIdEstructura(FactoriaEstructura.ESTRUCTURA_CUADROS);
	}

    @Override
    public void crearEstructura(int cantidadJugadores) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getCantidadPartidos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getIdSiguientePartido() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}//end Cuadros