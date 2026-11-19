package modelo.itens;

import excecoes.CampoInvalidoException;
import excecoes.VidaEnergiaCheiaException;
import modelo.classes.Entidade;

public class PocaoDeCura extends Item{

	private int vidaRecuperada;
	
	public PocaoDeCura() {
		super(20, "Poção de Cura - Aumenta em até 30 Pontos a Vida Atual", true);
		setVidaRecuperada(30);
	}

	public int getVidaRecuperada() {
		return vidaRecuperada;
	}

	public void setVidaRecuperada(int vidaRecuperada) {
		if(vidaRecuperada < 0) {
			throw new CampoInvalidoException("Vida Recuperada");
		}
		this.vidaRecuperada = vidaRecuperada;
	}

	@Override
	public void usarItem(Entidade jogador, Entidade inimigo) {

		double vidaAtual = jogador.getClasse().getVidaAtual();

		if(vidaAtual == jogador.getClasse().getVidaMax()) {
			throw new VidaEnergiaCheiaException("Vida");
		}
		
		vidaAtual += vidaRecuperada;
		
		if(vidaAtual > jogador.getClasse().getVidaMax()) {
			
			vidaAtual = jogador.getClasse().getVidaMax();
		}
		
		jogador.getClasse().setVidaAtual(vidaAtual);
	}
}
