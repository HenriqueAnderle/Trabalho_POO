package modelo.classes;

import enumerations.CondicaoEnum;
import modelo.Condicao;

public abstract class Entidade {

	private Classe classe;
	private Condicao condicao;
	
	public Entidade(Classe classe) {
		setClasse(classe);
		setCondicao(new Condicao(CondicaoEnum.NADA));
	}
	
	public Classe getClasse() {
		return classe;
	}
	public void setClasse(Classe classe) {
		this.classe = classe;
	}
	public Condicao getCondicao() {
		return condicao;
	}
	public void setCondicao(Condicao condicao) {
		this.condicao = condicao;
	}
}
