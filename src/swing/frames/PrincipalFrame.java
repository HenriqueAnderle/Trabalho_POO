package swing.frames;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import swing.panels.InicioPanel;

public class PrincipalFrame extends JFrame{

	private static final long serialVersionUID = 1L;

	public PrincipalFrame() {
		
		CardLayout cardLayout = new CardLayout();
		JPanel cardPanel = new JPanel();
				
		cardPanel.setLayout(cardLayout);
		
		InicioPanel inicioPanel = new InicioPanel(cardPanel, cardLayout);
		
		cardPanel.add(inicioPanel, "InicioPanel");
		
		cardLayout.show(cardPanel, "InicioPanel");
		
		add(cardPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		setVisible(true);
		setLocationRelativeTo(null);
	}
}
