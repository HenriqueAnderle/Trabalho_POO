package modelo.itens;

import excecoes.CampoInvalidoException;
import excecoes.VidaEnergiaCheiaException;
import modelo.classes.Arqueiro;
import modelo.classes.Entidade;
import modelo.classes.Guerreiro;
import modelo.classes.Mago;

public class PocaoDeEnergia extends Item{

	private int energiaRecuperada;
	
	public PocaoDeEnergia() {
		super(15, "Poção de Mana - Aumenta em até 50 Pontos a Energia Atual", true);
	}

	public int getEnergiaRecuperada() {
		return energiaRecuperada;
	}

	public void setEnergiaRecuperada(int energiaRecuperada) {
		if(energiaRecuperada < 0) {
			throw new CampoInvalidoException("Energia Recuperada");
		}
		this.energiaRecuperada = energiaRecuperada;
	}

	//REVER ESSE MÉTODO AEJEAWLEAWealkdadawl
	@Override
	public void usarItem(Entidade jogador, Entidade inimigo) {
		
		int energiaAtual = jogador.getClasse().getEnergiaAtual();
		
		if(energiaAtual == jogador.getClasse().getEnergiaMax()) {
			throw new VidaEnergiaCheiaException("Energia");
		}
		
		if(jogador.getClasse() instanceof Mago) {
			
			setEnergiaRecuperada(50);
		
		}else if(jogador.getClasse() instanceof Guerreiro) {
			
			setEnergiaRecuperada(30);
		
		}else if(jogador.getClasse() instanceof Arqueiro) {
			
			setEnergiaRecuperada(5);
			
		}
		
		energiaAtual += energiaRecuperada;
		
		if(energiaAtual > jogador.getClasse().getEnergiaMax()) {
			
			energiaAtual = jogador.getClasse().getEnergiaMax();
		}
		jogador.getClasse().setEnergiaAtual(energiaAtual);
	}

}
