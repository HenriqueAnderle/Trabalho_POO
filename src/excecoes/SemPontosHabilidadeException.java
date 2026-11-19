package excecoes;

public class SemPontosHabilidadeException extends IllegalArgumentException{

	private static final long serialVersionUID = 1L;

	public SemPontosHabilidadeException() {
		super("Sem Pontos de Habilidade");
	}
}
