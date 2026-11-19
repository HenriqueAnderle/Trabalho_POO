package modelo;

import enumerations.CondicaoEnum;
import excecoes.CampoInvalidoException;

public class Dano {

	private double dano;
	private boolean perfurante;
	private Condicao condicao;
	private String descricao;
	
	public Dano(double dano, boolean perfurante, String descricao) {
		setDano(dano);
		setPerfurante(perfurante);
		setCondicao(new Condicao(CondicaoEnum.NADA));
		setDescricao(descricao);
	}
	
	public Dano(double dano, boolean perfurante, Condicao condicao, String descricao) {
		setDano(dano);
		setPerfurante(perfurante);
		setCondicao(condicao);
		setDescricao(descricao);
	}
	
	public double getDano() {
		return dano;
	}
	public void setDano(double dano) {
		if(dano < -1) {
			throw new CampoInvalidoException("Dano");
		}
		this.dano = dano;
	}
	public boolean isPerfurante() {
		return perfurante;
	}
	public void setPerfurante(boolean perfurante) {
		this.perfurante = perfurante;
	}
	public Condicao getCondicao() {
		return condicao;
	}
	public void setCondicao(Condicao condicao) {
		this.condicao = condicao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		if(descricao == null || descricao.isBlank() || descricao.length() < 3) {
			throw new CampoInvalidoException("Descrição Ataque");
		}
		this.descricao = descricao;
	}
}
