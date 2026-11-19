package modelo.itens;

import enumerations.CondicaoEnum;
import excecoes.CampoInvalidoException;
import modelo.Condicao;
import modelo.Dano;
import modelo.classes.Entidade;

public class BombaDeFogo extends Item{
	
	private Dano danoBomba;
	
	public BombaDeFogo() {
		super(40, "Bomba Flamejante - BOOOOOOOOOOOOOOMMMMMMMMM", false);
		setDanoBomba(new Dano(20, false, new Condicao(CondicaoEnum.EM_CHAMAS), "Bomba"));
	}

	public Dano getDanoBomba() {
		return danoBomba;
	}

	public void setDanoBomba(Dano danoBomba) {
		this.danoBomba = danoBomba;
	}

	public void usarItem(Entidade jogador, Entidade inimigo) {
		try {
			inimigo.getClasse().setVidaAtual(inimigo.getClasse().getVidaAtual() - danoBomba.getDano());
			inimigo.setCondicao(danoBomba.getCondicao());
		}catch(CampoInvalidoException e) {
			System.out.println(e.getMessage());
			inimigo.getClasse().setVidaAtual(0);
		}
	}
}
