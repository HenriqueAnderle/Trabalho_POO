package swing.panels;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controle.Intervalo;
import controle.Partida;
import enumerations.EstadoJogo;

public class IntervaloPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	//LojaPanel
	private JLabel lojaLabel;
	private JButton[] lojaButtons;
	private JLabel[] itensLojaLabel;
	private JLabel moedasLabel;
	
	//Inventario Panel
	//TODO
	//Vai ter que trocar esses dois arrays pra list porque o tamanho deles vai ficar mudando
	private JLabel inventarioLabel;
	private List<JLabel> itensInventarioLabel;
	private List<JButton> usarItemButton;
	private JLabel vidaLabel;
	private JLabel energiaLabel;
	
	
	
	private JPanel principalPanel;
	
	private JPanel lojaPanel;
	private JPanel inventarioPanel;
	
	private JButton continuarButton;
	
	private Intervalo intervalo;
	private Partida partida;
	
	public IntervaloPanel(JPanel cardPanel, CardLayout cardLayout, Partida partida) {
		
		setPartida(partida);
		this.setLayout(new BorderLayout());
		
		intervalo = new Intervalo();
		intervalo.iniciarLoja();
		
		CardLayout principalLayout = new CardLayout();
		principalPanel = new JPanel(principalLayout);
		
		itensLojaLabel = new JLabel[intervalo.getLoja().size()];
		lojaButtons = new JButton[intervalo.getLoja().size()];
		
		lojaPanel = new JPanel();
		inventarioPanel = new JPanel();
		
		moedasLabel = new JLabel("Moedas : " + partida.getJogador().getMoedas());
		lojaPanel.add(moedasLabel);
		
		for(int i = 0; i < itensLojaLabel.length; i++) {
			int item = i + 1;
			
			itensLojaLabel[i] = new JLabel(intervalo.getLoja().get(i).getDescricao() + ", Preço : " + intervalo.getLoja().get(i).getPreco() + ", Quantidade No Inventário : " + 
			intervalo.calcularQuantidadeInventario(partida, intervalo.getLoja().get(i)));
			lojaPanel.add(itensLojaLabel[i]);
			lojaButtons[i] = new JButton("Comprar");
			lojaPanel.add(lojaButtons[i]);
			lojaButtons[i].addActionListener(e -> jogadorEscolheuItemComprar(item));
		}
		
		principalPanel.add(lojaPanel, "LojaPanel");
						
		continuarButton = new JButton("Continuar");
		continuarButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event) {
				
				principalPanel.add(inventarioPanel, "InventarioPanel");
				
				principalLayout.show(principalPanel, "InventarioPanel");
				
				iniciarUsarItensInventario();
			}
		});
		
		lojaPanel.add(continuarButton);
		
		add(principalPanel);
	}

	private void jogadorEscolheuItemComprar(int item) {
		EstadoJogo estado = intervalo.jogadorAgirCompra(item, partida);
		
		if(estado == EstadoJogo.ACAO_INVALIDA) {
			JOptionPane.showMessageDialog(this, intervalo.getMensagem());
			return;
		}
		
		atualizarLoja();
	}
	
	private void atualizarLoja() {
		moedasLabel.setText("Moedas : " + partida.getJogador().getMoedas());
		
		for(int i = 0; i < itensLojaLabel.length; i++) {			
			itensLojaLabel[i].setText(intervalo.getLoja().get(i).getDescricao() + ", Preço : " + intervalo.getLoja().get(i).getPreco() + ", Quantidade No Inventário : " + 
			intervalo.calcularQuantidadeInventario(partida, intervalo.getLoja().get(i)));
		}
	}

	private void iniciarUsarItensInventario() {
		
		inventarioLabel = new JLabel("ITENS INVENTÁRIO");
		vidaLabel = new JLabel("Vida : " + partida.getJogador().getClasse().getVidaAtual());
		energiaLabel = new JLabel("Energia : " + partida.getJogador().getClasse().getEnergiaAtual());
		
		inventarioPanel.add(inventarioLabel);
		inventarioPanel.add(vidaLabel);
		inventarioPanel.add(energiaLabel);
		
		itensInventarioLabel = new ArrayList<JLabel>();
		usarItemButton = new ArrayList<JButton>();
		
		for(int i = 0; i < itensInventarioLabel.size(); i++) {
			int item = i;
			
			itensInventarioLabel.add(new JLabel(partida.getJogador().getClasse().getInventario().get(i).getDescricao()));
			inventarioPanel.add(itensInventarioLabel.get(i));
			
			usarItemButton.add(new JButton("Usar"));
			inventarioPanel.add(usarItemButton.get(i));
			usarItemButton.get(i).addActionListener(e -> jogadorEscolheuItemUsar(item));
		}
	}
	
	private void jogadorEscolheuItemUsar(int item) {
		EstadoJogo estado = intervalo.jogadorAgirUsar(item, partida);
		
		if(estado == EstadoJogo.ACAO_INVALIDA) {
			JOptionPane.showMessageDialog(this, intervalo.getMensagem());
			return;
		}
		
		atualizarInventario();
	}

	private void atualizarInventario() {
		
		itensInventarioLabel.clear();
		usarItemButton.clear();
		
		for(int i = 0; i < itensInventarioLabel.size(); i++) {
			int item = i;
			
			itensInventarioLabel.add(new JLabel(partida.getJogador().getClasse().getInventario().get(i).getDescricao()));
			inventarioPanel.add(itensInventarioLabel.get(i));
			
			usarItemButton.add(new JButton("Usar"));
			inventarioPanel.add(usarItemButton.get(i));
			usarItemButton.get(i).addActionListener(e -> jogadorEscolheuItemUsar(item));
		}
		
		vidaLabel.setText("VIDA : " + (int) partida.getJogador().getClasse().getVidaAtual());
		energiaLabel.setText("ENERGIA : " + (int) partida.getJogador().getClasse().getEnergiaAtual());
	}

	public Intervalo getIntervalo() {
		return intervalo;
	}

	public void setIntervalo(Intervalo intervalo) {
		this.intervalo = intervalo;
	}

	public Partida getPartida() {
		return partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}
}
