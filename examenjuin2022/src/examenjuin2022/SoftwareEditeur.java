package examenjuin2022;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import accessBD.*;


public class SoftwareEditeur extends JPanel {
	
	private JButton boutonAfficher;
	private Menu menu;
	private JComboBox designation;
	private Object[] coldesignation;
	private String SqlSelectFrom;
	private PDate panneauDateAquisition;
	private Connection connection;
	
	public  SoftwareEditeur(Connection connection, Menu menu) {
		
		this.menu = menu;
	
		
		panneauDateAquisition= new PDate();
		panneauDateAquisition.setBounds(180,50,175,40); 
		add(panneauDateAquisition);
		

		boutonAfficher = new JButton("Afficher");
		boutonAfficher.setBounds(600,50,80,30); 
		add(boutonAfficher);
		
		designation = new JComboBox();
		designation.setSelectedItem("");
        
        
        try
        {
        	 String query = "SELECT Designation From editeur;";
        	 PreparedStatement qdesignation = connection.prepareStatement(query);
        	 coldesignation = AccessBDGen.creerListe1Colonne(qdesignation);
        	 
        	 
        	 for (int i = 0; i < coldesignation.length; i++)
        	 {
        		 designation.addItem(coldesignation[i]);
        	 }
        	 add(designation);
        	 
        	 setVisible(true);
        }
        catch(SQLException e) 
        {
 			JOptionPane.showMessageDialog(null,e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
     	}
		
		ActionAfficherTable actionAfficherTable = new ActionAfficherTable();
		boutonAfficher.addActionListener(actionAfficherTable);

		setVisible(true);
	}
	
	private class ActionAfficherTable implements ActionListener{
		public void actionPerformed(ActionEvent a){
			SqlSelectFrom = "SELECT * FROM dbinstallations.software s, dbinstallations.editeur e  WHERE s.DateAcquisition > '"+panneauDateAquisition.getDateAcquis()+"' AND s.CodeEdit = e.CodeEdit AND e.Designation = \""+ designation.getSelectedItem()+"\"";
			AfficherUneTable afficherLaTable  = new AfficherUneTable(menu.getConnect(), SqlSelectFrom);
			
			removeAll();
			add(boutonAfficher);
			add(panneauDateAquisition);
			add(designation);
			afficherLaTable.setBounds(5, 100, 775, 400);
			add(afficherLaTable);
			validate();
		}
	}
}
