package modelo.itens;

import excecoes.CampoInvalidoException;
import modelo.Dano;
import modelo.classes.Entidade;

public class Bomba extends Item{

	private Dano danoBomba;
	
	public Bomba() {
		super(20, "Bomba - KABUUUMMM", false);
		setDanoBomba(new Dano(20, false, "Bomba"));
	}

	public Dano getDanoBomba() {
		return danoBomba;
	}

	public void setDanoBomba(Dano danoBomba) {
		this.danoBomba = danoBomba;
	}

	@Override
	public void usarItem(Entidade jogador, Entidade inimigo) {
		try {
			inimigo.getClasse().setVidaAtual(inimigo.getClasse().getVidaAtual() - danoBomba.getDano());
		}catch(CampoInvalidoException e) {
			System.out.println(e.getMessage());
			inimigo.getClasse().setVidaAtual(0);
		}
	}
}
