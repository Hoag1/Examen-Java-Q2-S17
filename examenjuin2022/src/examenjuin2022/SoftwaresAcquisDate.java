package examenjuin2022;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import accessBD.*;


public class SoftwaresAcquisDate extends JPanel {
	private JLabel labelTitre, labelDateDébut, labelDateFin;
	private JButton boutonAfficher;
	private Menu menu;
	private JComboBox libele;
	private Object[] colLibele;
	private String SqlSelectFrom;
	private PDate panneauDateDébut,panneauDateAFin;
	private Connection connection;
	
	public  SoftwaresAcquisDate(Connection connection, Menu menu) {
		
		this.menu = menu;
		

		labelDateDébut = new JLabel("Date début : "); 
		labelDateDébut.setBounds(100,50,100,40); 
		add(labelDateDébut); 
		
		panneauDateDébut = new PDate();
		panneauDateDébut.setBounds(180,50,175,40); 
		add(panneauDateDébut);
		
		labelDateFin = new JLabel("Date Fin : "); 
		labelDateFin.setBounds(360,50,100,40); 
		add(labelDateFin); 
		
		panneauDateAFin = new PDate();
		panneauDateAFin.setBounds(420,50,175,40); 
		add(panneauDateAFin);
		
		boutonAfficher = new JButton("Afficher");
		boutonAfficher.setBounds(600,50,80,30); 
		add(boutonAfficher);
		
		libele = new JComboBox();
		libele.setSelectedItem("");
        
        
        try
        {
        	 String query = "SELECT libelle From FamilleSoftware;";
        	 PreparedStatement qlibele = connection.prepareStatement(query);
        	 colLibele = AccessBDGen.creerListe1Colonne(qlibele);
        	 
        	 
        	 for (int i = 0; i < colLibele.length; i++)
        	 {
        		 libele.addItem(colLibele[i]);
        	 }
        	 add(libele);
        	 
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
			SqlSelectFrom = "SELECT * FROM dbinstallations.installation i, dbinstallations.software s, dbinstallations.famillesoftware f WHERE i.DateInstallation BETWEEN '"+panneauDateDébut.getDateAcquis()+"' and '"+panneauDateAFin.getDateAcquis()+"'AND s.CodeSoftware = i.CodeSoftware AND f.IdFamSoft = s.IdFamSoft AND f.Libelle = \""+ libele.getSelectedItem() +"\"";
			AfficherUneTable afficherLaTable  = new AfficherUneTable(menu.getConnect(), SqlSelectFrom);
			
			removeAll();
			add(boutonAfficher);
			add(panneauDateDébut);
			add(panneauDateAFin);
			add(libele);
			afficherLaTable.setBounds(5, 100, 775, 400);
			add(afficherLaTable);
			validate();
		}
	}
}