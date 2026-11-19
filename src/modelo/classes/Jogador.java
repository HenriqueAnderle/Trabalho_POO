package modelo.classes;

import excecoes.CampoInvalidoException;
import modelo.itens.PocaoDeCura;

public class Jogador extends Entidade{

	private int vidasTotais;
	private int moedas;
	private int pontuacao;
	private int pontosHabilidade;
	
	public Jogador(Classe classe) {
		super(classe);
		setVidasTotais(1);
		setMoedas(50);
		setPontuacao(0);
		setPontosHabilidade(4);
	}

	public int getVidasTotais() {
		return vidasTotais;
	}

	public void setVidasTotais(int vidasTotais) {
		if(vidasTotais < 0) {
			throw new CampoInvalidoException("Vidas Totais");
		}
		this.vidasTotais = vidasTotais;
	}

	public int getMoedas() {
		return moedas;
	}

	public void setMoedas(int moedas) {
		if(moedas < 0) {
			throw new CampoInvalidoException("Moedas");
		}
		this.moedas = moedas;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		if(pontuacao < 0) {
			throw new CampoInvalidoException("Pontuação");
		}
		this.pontuacao = pontuacao;
	}

	public int getPontosHabilidade() {
		return pontosHabilidade;
	}

	public void setPontosHabilidade(int pontosHabilidade) {
		if(pontosHabilidade < 0) {
			throw new CampoInvalidoException("Pontos de Habilidade");
		}
		this.pontosHabilidade = pontosHabilidade;
	}
}
