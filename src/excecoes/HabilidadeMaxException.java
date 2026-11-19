package excecoes;

public class HabilidadeMaxException extends IllegalArgumentException{

	private static final long serialVersionUID = 1L;

	public HabilidadeMaxException(String habilidade) {
		super(habilidade + " Maximizada");
	}
}
