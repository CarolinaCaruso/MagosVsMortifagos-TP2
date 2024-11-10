package personaje.mago;

public class Auror extends Mago {
	
	private static final int NIVEL_MAGICO = 0;

	public Auror(String nombre) {
        super(TipoMago.Auror, nombre);
    }
	
	public Auror(String nombrePersonaje, int puntosVida, boolean armado, boolean protegido) {
	       super(TipoMago.Auror, nombrePersonaje, NIVEL_MAGICO, puntosVida, armado, protegido);
	}

	@Override
	public boolean esMago() {
		return true;
	}

	@Override
	public boolean esMortifago() {
		return false;
	}
}


