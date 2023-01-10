package examenjuin2022;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import accessBD.*;


public class AfficherLesTables extends JPanel{
	private JLabel labelTitre;
	private JButton boutonAfficher;
	private Menu menu;
	private String SqlSelectFrom;
	private JComboBox<String> comboChoix;
	
	public AfficherLesTables(Connection connection, Menu menu) {
		
		setLayout(null);
		this.menu = menu;
		
		labelTitre = new JLabel("Afficher tableau "); 
		labelTitre.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,25));
		labelTitre.setBounds(300,10,450,30); 
		add(labelTitre); 

		comboChoix = new JComboBox<String>();
		comboChoix.setBounds(270,50,150,30); 
		add(comboChoix);
		
		RécupérerNomsTableau(connection);
		
		boutonAfficher = new JButton("Afficher");
		boutonAfficher.setBounds(420,50,80,30); 
		add(boutonAfficher);
		
		ActionAfficherTable actionAfficherTable = new ActionAfficherTable();
		boutonAfficher.addActionListener(actionAfficherTable);
		

		setVisible(true);
	}
	
	private void RécupérerNomsTableau(Connection connect) {
		try {
			PreparedStatement prepStat = connect.prepareStatement("show tables");
			TableModelGen table = AccessBDGen.creerTableModel(prepStat);
			for(int i=0; i <= table.getRowCount()-1; i++) { 	
				comboChoix.addItem((String) table.getValueAt(i, 0));
				}
			}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null,e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private class ActionAfficherTable implements ActionListener{
		public void actionPerformed(ActionEvent a){
			SqlSelectFrom = "select * from "+(String)comboChoix.getSelectedItem()+";";
			AfficherUneTable afficherLaTable  = new AfficherUneTable(menu.getConnect(), SqlSelectFrom);

			removeAll();
			add(labelTitre);
			add(comboChoix);
			add(boutonAfficher);
			afficherLaTable.setBounds(5,100,775,400); 
			add(afficherLaTable);
			validate();
		}
	}
}
