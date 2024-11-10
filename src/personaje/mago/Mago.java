package personaje.mago;

import personaje.Personaje;

public abstract class Mago extends Personaje {
	
	protected TipoMago tipo;
	
	public Mago(TipoMago tipo, String nombre) {
        super(nombre);
        this.tipo = tipo;
    }
	
	public Mago(TipoMago tipo, String nombre, int nivelMagia, int puntosVida, boolean armado, boolean protegido) {
		super(nombre, nivelMagia, puntosVida, armado, protegido);
		this.tipo = tipo;
	}

	
}

