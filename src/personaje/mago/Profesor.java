package personaje.mago;


public class Profesor extends Mago {
	private static final int NIVEL_MAGICO = 5;

	public Profesor(String nombre) {
        super(TipoMago.Profesor, nombre);
    }
	
    public Profesor(String nombrePersonaje, int puntosVida, boolean armado, boolean protegido) {
        super(TipoMago.Profesor, nombrePersonaje, NIVEL_MAGICO, puntosVida, armado, protegido);
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
