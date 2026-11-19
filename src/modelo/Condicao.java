package modelo;

import enumerations.CondicaoEnum;
import excecoes.CampoInvalidoException;

public class Condicao {

	private CondicaoEnum condicao;
	private int duracao;
	
	public Condicao (CondicaoEnum condicao, int duracao) {
		setCondicao(condicao);
		setDuracao(duracao);
	}
	
	public Condicao (CondicaoEnum condicao) {
		setCondicao(condicao);
	}
	
	public CondicaoEnum getCondicao() {
		return condicao;
	}
	public void setCondicao(CondicaoEnum condicao) {
		this.condicao = condicao;
	}
	public int getDuracao() {
		return duracao;
	}
	public void setDuracao(int duracao) {
		if(duracao < 0) {
			throw new CampoInvalidoException("Duração");
		}
		this.duracao = duracao;
	}
}
