package swing.panels;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controle.Partida;
import enumerations.ClasseEnum;

public class PartidaPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JLabel classeLabel;
	private JComboBox<String> classesComboBox;
	private JLabel nomeLabel;
	private JTextField nomeTextField;
	private JButton avancarButton;
	private JButton voltarButton;

	public PartidaPanel(JPanel cardPanel, CardLayout cardLayout) {
		
		Partida partida = new Partida();
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		classeLabel = new JLabel("Classes : ");
		classeLabel.setVisible(true);
		classeLabel.setPreferredSize(new Dimension(200, 50));
		classeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		classeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		classeLabel.setVerticalAlignment(SwingConstants.CENTER);
		
		add(classeLabel);
		
		String arrayString[] = new String[ClasseEnum.values().length];
		int index = 0;
		for(ClasseEnum classe : ClasseEnum.values()) {
			arrayString[index] = classe.toString();
			index++;
		}
		classesComboBox = new JComboBox<String>(arrayString);
		classesComboBox.setPreferredSize(new Dimension(200, 50));
		classesComboBox.setMaximumSize(new Dimension(200, 50));
		classesComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		add(Box.createRigidArea(new Dimension(0, 20)));
		add(classesComboBox);
		
		nomeLabel = new JLabel("Nome : ");
		nomeLabel.setVisible(true);
		nomeLabel.setPreferredSize(new Dimension(200, 50));
		nomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		nomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nomeLabel.setVerticalAlignment(SwingConstants.CENTER);
		
		add(Box.createRigidArea(new Dimension(0, 20)));
		add(nomeLabel);
		
		nomeTextField = new JTextField("", 20);
		nomeTextField.setPreferredSize(new Dimension(200, 50));
		nomeTextField.setMaximumSize(new Dimension(200, 50));
		
		add(Box.createRigidArea(new Dimension(0, 20)));
		add(nomeTextField);
		
		avancarButton = new JButton("Avançar");
		avancarButton.setPreferredSize(new Dimension(200, 50));
		avancarButton.setMaximumSize(new Dimension(200, 50));
		avancarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		add(Box.createRigidArea(new Dimension(0, 20)));
		add(avancarButton);
		
		voltarButton = new JButton("Voltar");
		voltarButton.setPreferredSize(new Dimension(200, 50));
		voltarButton.setMaximumSize(new Dimension(200, 50));
		voltarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		add(Box.createRigidArea(new Dimension(0, 20)));
		add(voltarButton);
		
		RodadaPanel rodadaPanel = new RodadaPanel(cardPanel, cardLayout, this);
		cardPanel.add(rodadaPanel, "RodadaPanel");
		
		avancarButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event) {
				
				String classe = classesComboBox.getItemAt(classesComboBox.getSelectedIndex());
				
				partida.iniciarJogador(classe, nomeTextField.getText());
				partida.iniciarJogo();

				rodadaPanel.setPartida(partida);
				cardLayout.show(cardPanel, "RodadaPanel");
				rodadaPanel.iniciarRodadaTela();
				
				cardLayout.show(cardPanel, "RodadaPanel");
				
				resetarForm();
			}
		});
		
		voltarButton.addActionListener(e -> cardLayout.show(cardPanel, "InicioPanel"));
	}
	
	public void resetarForm() {
		nomeTextField.setText(null);
		
		revalidate();
        repaint();
	}
}
