package excecoes;

public class CampoInvalidoException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public CampoInvalidoException(String campo) {
		super(campo + " é Inválido");
	}
}
