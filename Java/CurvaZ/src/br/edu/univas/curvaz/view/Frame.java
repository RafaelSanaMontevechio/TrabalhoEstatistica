package br.edu.univas.curvaz.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame {

	private static final long serialVersionUID = 8581471314241635487L;

	private JPanel contentPanel;

	public Frame() {
		setTitle("Curva Z resumida");
		setSize(400, 100);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		initialize();
		//pack();

	}

	public void initialize() {

		getContentPanel().add(new PanelInfo(), BorderLayout.CENTER);

		setContentPane(getContentPanel());
		setVisible(true);

	}

	public JPanel getContentPanel() {
		if (contentPanel == null) {
			contentPanel = new JPanel();
			contentPanel.setLayout(new BorderLayout());
		}
		return contentPanel;
	}
}
