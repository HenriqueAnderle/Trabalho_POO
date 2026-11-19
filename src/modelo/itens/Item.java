package modelo.itens;

import excecoes.CampoInvalidoException;
import excecoes.SemDinheiroException;
import modelo.Dano;
import modelo.classes.Entidade;
import modelo.classes.Jogador;
import modelo.inimigos.Inimigo;

public abstract class Item {
	
	private int preco;
	private String descricao;
	private boolean usavelIntervalo;
	
	public Item(int preco, String descricao, boolean usavelIntervalo) {
		setPreco(preco);
		setDescricao(descricao);
		setUsavelIntervalo(usavelIntervalo);
	}
	
	public int getPreco() {
		return preco;
	}
	public void setPreco(int preco) {
		if(preco < 0) {
			throw new CampoInvalidoException("Preço");
		}
		this.preco = preco;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		if(descricao == null || descricao.isBlank() || descricao.length() < 3) {
			throw new CampoInvalidoException("Descrição");
		}
		this.descricao = descricao;
	}
	public boolean isUsavelIntervalo() {
		return usavelIntervalo;
	}
	public void setUsavelIntervalo(boolean usavelIntervalo) {
		this.usavelIntervalo = usavelIntervalo;
	}

	public abstract void usarItem(Entidade jogador, Entidade inimigo);
			
	public boolean verificarDinheiroSuficiente(Jogador jogador, int preco) {
		if(jogador.getMoedas() >= preco) {
			return true;
		}else {
			return false;
		}
	}
	
	public void comprarItem(Jogador jogador, Item item) {
		if(verificarDinheiroSuficiente(jogador, item.getPreco())) {
			jogador.getClasse().adicionarItem(item);
			
			jogador.setMoedas(jogador.getMoedas() - item.getPreco());
		}else {
			throw new SemDinheiroException(item.getPreco());
		}
	}
}
