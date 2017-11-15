package br.edu.univas.curvaz.controller;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.edu.univas.curvaz.dao.CurvaDAO;

public class Controller {

	private CurvaDAO curva;

	public Controller() {
		curva = new CurvaDAO();
	}

	public String localizaZ(String str) {
		String valorZ = null;
		try {
			valorZ = curva.valorZ(str);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao localizar o valor de Z\n" + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return valorZ;
	}
}
