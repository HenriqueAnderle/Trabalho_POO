package controle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import enumerations.EstadoJogo;
import excecoes.HabilidadeMaxException;
import excecoes.InventarioVazioException;
import excecoes.SemDinheiroException;
import excecoes.SemPontosHabilidadeException;
import excecoes.VidaEnergiaCheiaException;
import modelo.classes.Jogador;
import modelo.itens.Bomba;
import modelo.itens.BombaDeFogo;
import modelo.itens.Item;
import modelo.itens.PocaoDeCura;
import modelo.itens.PocaoDeEnergia;

public class Intervalo {

	private List<Item> loja;
	private List<Item> itens;
	
	public Intervalo() {
		loja = new ArrayList<Item>();
		itens = new ArrayList<Item>();
		
		itens.add(new PocaoDeCura());
		itens.add(new PocaoDeEnergia());
		itens.add(new Bomba());
		itens.add(new BombaDeFogo());
		//COLOCAR TODOS OS ITENS AQUI
		
	}

	public List<Item> getLoja() {
		return loja;
	}

	public void setLoja(List<Item> loja) {
		this.loja = loja;
	}
	
	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}
	
	public EstadoJogo jogadorAgirCompra(int item, Partida partida) {
		if(comprarItens(item, partida) == false) {
			return EstadoJogo.ACAO_INVALIDA;
		}
		return EstadoJogo.ACAO_REALIZADA;
	}
	
	public boolean comprarItens(int item, Partida partida) {
		
		try {
			switch(item) {
				case 1:
					loja.get(0).comprarItem(partida.getJogador(), loja.get(0));
					break;
					
				case 2:
					System.out.println(loja.get(0).getClass().getName());
					loja.get(1).comprarItem(partida.getJogador(), loja.get(1));
					break;
					
				case 3:
					System.out.println(loja.get(0).getClass().getName());
					loja.get(2).comprarItem(partida.getJogador(), loja.get(2));
					break;
					
				case 4:
					System.out.println(loja.get(0).getClass().getName());
					loja.get(3).comprarItem(partida.getJogador(), loja.get(3));
					break;
			}
		}catch(SemDinheiroException semDinheiroException) {
			return false;
		}
		return true;
	}
	
	public int calcularQuantidadeInventario(Partida partida, Item item) {
		int quantidade = 0;
		
		System.out.println(item.getClass());
		
		for(int i = 0; i < partida.getJogador().getClasse().getInventario().size(); i++) {
			if( partida.getJogador().getClasse().getInventario().get(i).getClass().equals(item.getClass())) {
				quantidade++;
			}
		}
		
		return quantidade;
	}
	
	public void iniciarIntervalo(Jogador jogador) {
		
		Scanner scanner = new Scanner(System.in);
		
		iniciarLoja();
		System.out.println(loja);
		
		try {
			comprarItens(jogador);
		}catch(SemDinheiroException semDinheiroException) {
			System.out.println(semDinheiroException.getMessage());
			comprarItens(jogador);
		}
		
		int escolha = 0;
		
		while(escolha != 4) {
			System.out.println("1 - Usar Item, 2 - Melhorar Habilidade, 3 - Sair");
			escolha = scanner.nextInt();
			
			switch(escolha) {
				
				case 1:
					try {
						usarItem(jogador);
					}catch(InventarioVazioException inventarioVazioException) {
						System.out.println(inventarioVazioException.getMessage());
					}
					break;
					
				case 2:
					try {
						escolherHabilidade(jogador, scanner);
					}catch(HabilidadeMaxException habilidadeMaxException) {
						System.out.println(habilidadeMaxException.getMessage());
						escolherHabilidade(jogador, scanner);
					}catch(SemPontosHabilidadeException semPontosHabilidadeException) {
						System.out.println(semPontosHabilidadeException.getMessage());
					}
					break;
					
				case 3:
					escolha = 4;
					break;
			}
		}
	}
	
	public void iniciarLoja() {
		
		Random random = new Random();
		
		for(int i = 0; i < 4; i++) {
			Item itemAleatorio = itens.get(random.nextInt(itens.size()));
			
			if(!loja.contains(itemAleatorio)) {
				loja.add(itemAleatorio);
			}else {
				i--;
			}
		}
	}
	
	//Testar essa classe
	public void escolherHabilidade(Jogador jogador, Scanner scanner ) {
		
		if(jogador.getPontosHabilidade() > 0) {
			
			System.out.println("1 - Vida Max, 2 - Precisão, 3 - Velocidade, 4 - Energia");
			int escolha = scanner.nextInt();
			
			switch(escolha) {
				
				case 1:
					System.out.println(jogador.getClasse().getVidaMax());
					jogador.getClasse().setVidaMax(jogador.getClasse().getVidaMax() + 10);
					System.out.println(jogador.getClasse().getVidaMax());
					break;
	
				case 2:
					System.out.println(jogador.getClasse().getPrecisao());
					if(jogador.getClasse().getPrecisao() <= 100) {
						jogador.getClasse().setPrecisao(jogador.getClasse().getPrecisao() + 10);
						System.out.println(jogador.getClasse().getPrecisao());
					}else {
						throw new HabilidadeMaxException("Precisão");
					}
					break;
					
				case 3:
					System.out.println(jogador.getClasse().getVelocidade());
					if(jogador.getClasse().getVelocidade() <= 6) {
						jogador.getClasse().setVelocidade(jogador.getClasse().getVelocidade() + 1);
						System.out.println(jogador.getClasse().getVelocidade());
					}else {
						throw new HabilidadeMaxException("Velocidade");
					}
					break;
					
				case 4:
					System.out.println(jogador.getClasse().getEnergiaMax());
					jogador.getClasse().setEnergiaMax(jogador.getClasse().getEnergiaMax() + 10);
					System.out.println(jogador.getClasse().getEnergiaMax());
					break;
			}
						
			jogador.setPontosHabilidade(jogador.getPontosHabilidade() - 1);
		}else {
			throw new SemPontosHabilidadeException();
		}
	}
	
	public void comprarItens(Jogador jogador) {
		
		Scanner scanner = new Scanner(System.in);
		int escolha = 0;
		
		try {
			while(escolha != 5) {
				System.out.println(jogador.getMoedas());
				
				for(int i = 0; i < loja.size(); i++) {
					
					int quantidade = 0;
										
					for(int j = 0; j < jogador.getClasse().getInventario().size(); j++) {
												
						if(jogador.getClasse().getInventario().get(j).getClass().equals((loja.get(i).getClass()))){
							quantidade ++;
						}
					}
					
					System.out.println(loja.get(i).getClass().getSimpleName() + ", Preço : " + loja.get(i).getPreco() + ", Quantidade no Inventário : "  + quantidade);
				}
				
				escolha = scanner.nextInt();
				
				switch(escolha) {
					case 1:
						System.out.println(loja.get(0).getClass().getName());
						loja.get(0).comprarItem(jogador, loja.get(0));
						break;
						
					case 2:
						System.out.println(loja.get(0).getClass().getName());
						loja.get(1).comprarItem(jogador, loja.get(1));
						break;
						
					case 3:
						System.out.println(loja.get(0).getClass().getName());
						loja.get(2).comprarItem(jogador, loja.get(2));
						break;
						
					case 4:
						System.out.println(loja.get(0).getClass().getName());
						loja.get(3).comprarItem(jogador, loja.get(3));
						break;
					
					case 5:
						escolha = 5;
				}
			}
		}catch(SemDinheiroException semDinheiroException) {
			comprarItens(jogador);
		}
	}
	
	public void usarItem(Jogador jogador) {
		
		Scanner scanner = new Scanner(System.in);
		
		int escolha = 0;
		
		System.out.println(jogador.getClasse().getInventario());
		
		while(escolha != -1) {
			String textoEscolha = "";
			
			if(!jogador.getClasse().getInventario().isEmpty()) {
			
				for(int i = 0; i < jogador.getClasse().getInventario().size(); i++) {
					if(jogador.getClasse().getInventario().get(i).isUsavelIntervalo()) {
						textoEscolha += i + " - "+ jogador.getClasse().getInventario().get(i).getClass().getSimpleName() + ", ";
					}else {
						
					}
				}
				
				textoEscolha += "-1 Para Sair";
				
				System.out.println(textoEscolha);
				escolha = scanner.nextInt();
				if(escolha >= 0) {
					
					try {
						jogador.getClasse().getInventario().get(escolha).usarItem(jogador, jogador);
						jogador.getClasse().removerItem(jogador.getClasse().getInventario().get(escolha));
					}catch(VidaEnergiaCheiaException vidaEnergiaCheiaException) {
						System.out.println(vidaEnergiaCheiaException.getMessage());
					}
				}
			}else {
				throw new InventarioVazioException();
			}
		}
	}
}
