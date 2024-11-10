package personaje.mortifago;


public class Comandante extends Mortifago {
	private static final int NIVEL_MAGICO = 10;

	public Comandante(String nombre) {
        super(TipoMortifago.Comandante, nombre);
    }
	
    public Comandante(String nombrePersonaje, int puntosVida, boolean armado, boolean protegido) {
        super(TipoMortifago.Comandante, nombrePersonaje, NIVEL_MAGICO, puntosVida, armado, protegido);
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
