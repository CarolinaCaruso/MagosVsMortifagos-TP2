package personaje.mortifago;

import personaje.Personaje;

public abstract class Mortifago extends Personaje {

	protected TipoMortifago tipo;
	
	public Mortifago(TipoMortifago tipo, String nombre) {
        super(nombre);
        this.tipo = tipo;
    }
	
	public Mortifago(TipoMortifago tipo, String nombrePersonaje, int nivelMagia, int puntosVida, boolean armado,
			boolean protegido) {
		super(nombrePersonaje, nivelMagia, puntosVida, armado, protegido);
	}
}