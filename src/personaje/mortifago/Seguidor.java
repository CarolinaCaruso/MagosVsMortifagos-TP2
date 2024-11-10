package personaje.mortifago;


public class Seguidor extends Mortifago {
	private static final int NIVEL_MAGICO = 3;
	
	public Seguidor(String nombre) {
        super(TipoMortifago.Seguidor, nombre);
    }
	
    public Seguidor(String nombrePersonaje, int puntosVida, boolean armado, boolean protegido) {
        super(TipoMortifago.Seguidor, nombrePersonaje, NIVEL_MAGICO, puntosVida, armado, protegido);
    }
	@Override
	public boolean esMago() {
		return false;
	}

	@Override
	public boolean esMortifago() {
		return true;
	}
}
