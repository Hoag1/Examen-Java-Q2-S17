package examenjuin2022;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;


import accessBD.*;



public class DeleteInstall extends JPanel
{
	private Menu menu;
	private JLabel labelAnneeEtude;
	private JComboBox tableCombox;
	private Object[] table1, table2;
	private JButton boutonAfficher, boutonSupprimer;
	private String SqlSelectFrom;
	private JScrollPane scroll;
	private JTable table;
	private AfficherUneTable afficherLaTable;
	
   
    public DeleteInstall(Connection connection,Menu menu) 
    {
    	
    	setBounds(540,140,660,310);
    	setLayout(new FlowLayout());
        this.menu = menu;
        

		
    	boutonAfficher = new JButton("Afficher");
        ActionBoutonAfficher a = new ActionBoutonAfficher();
        boutonAfficher.addActionListener(a);	
        this.add(boutonAfficher);
        
        boutonSupprimer = new JButton("Supprimer");
        ActionBoutonSupprimer b = new ActionBoutonSupprimer();
        boutonSupprimer.addActionListener(b);	
        this.add(boutonAfficher);
         
        tableCombox = new JComboBox();
        tableCombox.setSelectedItem("");
        add(tableCombox);
         
         
         
        try
        {
        	 String query1 = "SELECT annee FROM AnneeEtude;";
        	 String query2 = "SELECT codeSection From AnneeEtude;";
        	 PreparedStatement annee = connection.prepareStatement(query1);
        	 PreparedStatement section = connection.prepareStatement(query2);
        	 table1 = AccessBDGen.creerListe1Colonne(annee);
        	 table2 = AccessBDGen.creerListe1Colonne(section);
        	 
        	 
        	 for (int i = 0; i < table1.length; i++)
        	 {
        		 tableCombox.addItem(table1[i] + " " + table2[i]);
        	 }
        	 this.add(tableCombox);
        }
        catch(SQLException e) 
        {
 			JOptionPane.showMessageDialog(null,e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
     	}
      	
         
        
    }
    
	public class ActionBoutonAfficher implements ActionListener
	{
	public void actionPerformed(ActionEvent a) 
	{
		List<String> anneeType = Arrays.asList(((String)tableCombox.getSelectedItem()).split(" "));
		SqlSelectFrom = "SELECT * FROM dbinstallations.anneeEtude a, dbinstallations.installation i, dbinstallations.utilisationsoftware u WHERE a.Annee = "+ anneeType.get(0) +" AND a.CodeSection = \"" + anneeType.get(1) +"\" AND u.IdAnneeEtude = a.IdAnneeEtude AND i.CodeSoftware = u.CodeSoftware;";
		afficherLaTable = new AfficherUneTable(menu.getConnect(), SqlSelectFrom);
		removeAll();
		add(tableCombox);
		add(boutonAfficher);
		add(boutonSupprimer);
		afficherLaTable.setBounds(5, 100, 775, 400);
		add(afficherLaTable);
		validate();
	}
	}
	
	public class ActionBoutonSupprimer implements ActionListener
	{
		public void actionPerformed(ActionEvent b)
		{
			int choix=0;
			System.out.println();
			try {
				choix = JOptionPane.showConfirmDialog(null, "Confirmer ?", "CONFIRMATION", JOptionPane.YES_NO_OPTION);

				if(choix == 0) {
					int[] SelectedRows=afficherLaTable.getTable().getSelectedRows();
					if(SelectedRows.length > 0 ) {
						for(int i=0;i<SelectedRows.length;i++) {
							int idInstallation = ((Integer)afficherLaTable.getTable().getModel().getValueAt(SelectedRows[i],3)).intValue();	
				            		String deleteInstallaion = "delete from installation i where i.IdInstallation = "+idInstallation+";";
							Statement stmt=menu.getConnect().createStatement();
							stmt.executeUpdate(deleteInstallaion);
						}
			
						removeAll();
						
						add(tableCombox);
						add(boutonAfficher);
						add(boutonSupprimer);
						
						validate();
					}
					else 
					{
					JOptionPane.showMessageDialog(null,"Veillez sélectionner une installation",null, JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	
	
	
	
}
    
    
    
       
}
    
    

    
  
	


