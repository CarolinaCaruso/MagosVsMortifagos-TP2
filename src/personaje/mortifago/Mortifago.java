package personaje.mortifago;

import personaje.Personaje;

public class Mortifago extends Personaje {

	private TipoMortifago tipo;
	
	public Mortifago(String nombre, TipoMortifago tipo) {
        super(nombre);
        this.tipo = tipo;
    }
}