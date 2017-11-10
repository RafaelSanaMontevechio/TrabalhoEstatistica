package br.edu.univas.curvaz.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.edu.univas.curvaz.controller.Controller;

public class PanelInfo extends JPanel {

	private static final long serialVersionUID = -7588082429297198704L;

	private JLabel label;
	private JTextField jt;
	private JButton bt;
	private JLabel labelInfo;
	private JLabel labelResult;

	private Controller controll;

	public PanelInfo() {
		controll = new Controller();

		addComponents();
	}

	private void addComponents() {
		add(getLabel());
		add(getJt());
		add(getBt());
		add(getLabelInfo());
		add(getLabelResult());

	}

	public JLabel getLabel() {
		if (label == null) {
			label = new JLabel();
			label.setText("Informe o nivel de confiança desejado:");
		}
		return label;
	}

	public JTextField getJt() {
		if (jt == null) {
			jt = new JTextField();
			jt.setColumns(3);
		}
		return jt;
	}

	public JButton getBt() {
		if (bt == null) {
			bt = new JButton();
			bt.setText("Localiza Z");
			bt.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					btClicked();
				}
			});

			bt.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						btClicked();
					}
				}
			});
		}
		return bt;
	}

	public JLabel getLabelInfo() {
		if (labelInfo == null) {
			labelInfo = new JLabel();
			labelInfo.setText("Valor de Z:");
			labelInfo.setFont(new Font("SansSerif", Font.PLAIN, 25));
		}
		return labelInfo;
	}

	public JLabel getLabelResult() {
		if (labelResult == null) {
			labelResult = new JLabel();
			labelResult.setFont(new Font("SansSerif", Font.PLAIN, 25));
			labelResult.setForeground(Color.blue);
		}
		return labelResult;
	}

	private void btClicked() {
		if (getJt().getText().equals("50")) {
			setResult("0");
		} else if (verifyEmptyString(getJt().getText())) {
			System.out.println(getJt().getText());
			String valorZ = controll.localizaZ(getJt().getText());
			setResult(valorZ);
		} else {
			JOptionPane.showMessageDialog(null, "Campo nivel de confiça não pode ser vazio!", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	// Verifica String vazia
	public boolean verifyEmptyString(String str) {
		if (str.trim().equals("")) {
			return false;
		}
		return true;
	}

	private void setResult(String str) {
		getLabelResult().setText(str);
		getJt().setText("");
	}
}
