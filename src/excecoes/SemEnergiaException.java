package excecoes;

public class SemEnergiaException extends IllegalArgumentException{

	private static final long serialVersionUID = 1L;
	
	public SemEnergiaException(String campo) {
		super(campo + " Insuficiente");
	}
}
