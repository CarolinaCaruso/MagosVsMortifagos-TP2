package personaje.mortifago;

import personaje.Personaje;

public abstract class Mortifago extends Personaje {

	protected TipoMortifago tipo;
	
	public Mortifago(String nombre, TipoMortifago tipo) {
        super(nombre);
        this.tipo = tipo;
    }
}