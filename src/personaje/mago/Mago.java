package personaje.mago;

import personaje.Personaje;

public abstract class Mago extends Personaje {
	
	protected TipoMago tipo;
	
	public Mago(String nombre, TipoMago tipo) {
        super(nombre);
        this.tipo = tipo;
    }

	
}

