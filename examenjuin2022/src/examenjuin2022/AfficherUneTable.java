package examenjuin2022;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import accessBD.*;



public class AfficherUneTable extends JPanel {
	private static JTable jTable;
	JTable getTable() {return jTable;}
	
	public AfficherUneTable(Connection connection, String SqlSelectFrom) {
		try {
			PreparedStatement prepStat = connection.prepareStatement(SqlSelectFrom);
			TableModelGen table = AccessBDGen.creerTableModel(prepStat);
			System.out.println(table);
			jTable = new JTable(table);
			jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Obtenir un meilleur affiche des tableaux
			jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //Permettre à l'user de ne sélectionner qu'une seule ligne
			JScrollPane defilant = new JScrollPane (jTable) ; //Ajoute defilant au conteneur afin de l'afficher
		 	defilant.setPreferredSize(new Dimension(775, 400));
		 	this.add(defilant);
			}
		catch(SQLException e) {	}
		
		setVisible(true);
		}
	
}
