package br.edu.univas.curvaz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.edu.univas.curvaz.to.CurvaTO;

public class CurvaDAO {

	private CurvaTO curva;

	public CurvaDAO() {

	}

	public ArrayList<CurvaTO> selectZ(String str) throws SQLException {
		Connection conn = null;
		ArrayList<CurvaTO> curvas = null;
		try {
			String conf = cutString(converteConfianca(str));
			curvas = new ArrayList<CurvaTO>();
			conn = DBUtil.openConnection();
			String sql = " SELECT valor, nivel_confianca FROM curva WHERE confianca LIKE ? ORDER BY valor ";

			PreparedStatement prep = conn.prepareStatement(sql);
			prep.setString(1, '%' + conf + '%');
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				curva = new CurvaTO();
				curva.setValor(rs.getString(1));
				curva.setNivelConfianca(rs.getDouble(2));
				curvas.add(curva);
				System.out.println(curva.getValor() + " - " + curva.getNivelConfianca());
			}
			conn.close();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Inserir apenas números!\n " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return curvas;
	}

	// Converte para double para executar o calculo e novamente par String para
	// select
	private String converteConfianca(String str) throws NumberFormatException {
		double nivelConfianca, aux = 0;

		if (verifica(nivelConfianca = Double.parseDouble(str))) {
			if (nivelConfianca < 50.99) {
				aux = (nivelConfianca / 100);
			} else {
				aux = (nivelConfianca / 2) / 100;
			}
		}
		String result = String.valueOf(aux);
		return result;
	}

	// Verifica se numero e valido
	private boolean verifica(double num) {
		if (num < 0 || num > 100) {
			JOptionPane.showMessageDialog(null, "Valor informado incorreto!\n ", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			return true;
		}
	}

	// Pega apenas os 4 primeiros caracteres
	private String cutString(String str) {
		if (str.length() < 4) {
			return str;
		} else {
			return str.substring(0, 4);
		}
	}

	private double converteDouble(String str) {
		double n = Double.parseDouble(str);
		return n;
	}

	public String findZ(String str) throws SQLException {
		String valorZ = null;
		double result = 0;
		double confiancaInformada = converteDouble(converteConfianca(str));
		ArrayList<CurvaTO> curvas = new ArrayList<CurvaTO>();
		curvas = selectZ(str);

		for (CurvaTO curvaTO : curvas) {
			double nivelConfianca = curvaTO.getNivelConfianca();
			if (confiancaInformada <= nivelConfianca) {
				return curvaTO.getValor();
			} else if ((confiancaInformada > nivelConfianca)) {
				result = confiancaInformada - nivelConfianca;
			} else if ((confiancaInformada < nivelConfianca)) {
				result = nivelConfianca - confiancaInformada;
			}

		}
		System.out.println(result);
		return valorZ;
	}
}
