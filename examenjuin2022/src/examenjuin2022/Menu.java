package examenjuin2022;

import java.awt.*;

import java.awt.event.*;
import java.net.*;
import java.sql.*;
import javax.swing.*;
import accessBD.*;






public class Menu extends JFrame {
	

	private Connection connection;
    private JMenuBar barre;
    private JMenu menuAccueil, menuApplication;
    private JMenuItem menuItemDéconnection, menuItemQuitter, menuItemInstallation, menuItemLectureTable, menuItemDésinstallation, menuItemSoftwaresAcquisDate,menuItemSoftwareEditeur;
    
   
    
    public Menu(Login loginConnect) 
    {
        super("Examen.JS");
       this.setBounds(500,200,800,630);
       this.setResizable(false);
        
       	
        connection=loginConnect.getConnect();
        getContentPane();
        
        
     	
     	Image jPanelImage = new Image();
	    add(jPanelImage);
		

     	barre = new JMenuBar( );
        setJMenuBar(barre);
        
        menuAccueil = new JMenu("Accueil");
        barre.add(menuAccueil);
        menuApplication = new JMenu("Application");
		barre.add(menuApplication);

        
        
		menuItemDéconnection = new JMenuItem("Déconnection");
		menuAccueil.add(menuItemDéconnection);
		menuActionDéconnection actionDéconnection = new menuActionDéconnection();
		menuItemDéconnection.addActionListener(actionDéconnection);
		
		menuItemQuitter = new JMenuItem("Quitter");
		menuAccueil.add(menuItemQuitter);
		menuActionQuitter actionQuitter = new menuActionQuitter();
		menuItemQuitter.addActionListener(actionQuitter);
		
		
        menuItemInstallation = new JMenuItem("Installation");
        menuApplication.add(menuItemInstallation); 
        MenuActionInstallation actionInstallation = new MenuActionInstallation(); 
        menuItemInstallation.addActionListener(actionInstallation);
        
        menuItemLectureTable = new JMenuItem("ListerDonnées"); 
	    menuApplication.add(menuItemLectureTable);
		MenuActionLectureTable actionLectureTable = new MenuActionLectureTable();    
		menuItemLectureTable.addActionListener(actionLectureTable);
        

        menuItemDésinstallation = new JMenuItem("Désinstaler"); 
		menuApplication.add(menuItemDésinstallation);
		MenuActionDésinstallation actionDésinstallation = new MenuActionDésinstallation();    
		menuItemDésinstallation.addActionListener(actionDésinstallation);
		
		
		menuItemSoftwaresAcquisDate = new JMenuItem("Softwares Acquis Date"); 
		menuApplication.add(menuItemSoftwaresAcquisDate);
		MenuActionSoftwaresAcquisDate actionSoftwaresAcquisDate = new MenuActionSoftwaresAcquisDate();    
		menuItemSoftwaresAcquisDate.addActionListener(actionSoftwaresAcquisDate);
		
		
		menuItemSoftwareEditeur = new JMenuItem("Softwares Editeur"); 
		menuApplication.add(menuItemSoftwareEditeur);
		MenuActionSoftwareEditeur actionSoftwareEditeur = new MenuActionSoftwareEditeur();    
		menuItemSoftwareEditeur.addActionListener(actionSoftwareEditeur);
		
		
		addWindowListener(new WindowAdapter() { 
	    	public void windowClosing(WindowEvent e) {
				try {
					connection.close();
					System.exit(0);
				}
				catch(SQLException exit1) { 
					System.out.println(exit1.getMessage());
				}	
	    	}
	    });
    
        
        
       
        setVisible(true);
   	
   }
    
    private class menuActionDéconnection implements ActionListener
    {
    	public void actionPerformed(ActionEvent exit)
    	{
    		dispose();
    		try
    		{
    			connection.close();
    			LoginFenetre loginFenetre = new LoginFenetre();
    		}
    		catch(SQLException exit1) 
    		{ 
				System.out.println(exit1.getMessage());
			}
    	}
    	
    }
    
    
    private class menuActionQuitter implements ActionListener  { 
		public void actionPerformed(ActionEvent e)  {
			try {
				connection.close();
			}
			catch(SQLException exit1) { }
			System.exit(0);
		}
	}
    
    
    private class MenuActionInstallation implements ActionListener 
    {
        public void actionPerformed (ActionEvent a) {
        	getContentPane().removeAll();
			Installation installation = new Installation(connection, Menu.this);
			add(installation);
			installation.repaint();
        	setVisible(true);
        }
     }
    
    private class MenuActionLectureTable implements ActionListener {
		public void  actionPerformed(ActionEvent e)  {
			getContentPane().removeAll();
			AfficherLesTables afficherDonnées = new AfficherLesTables (connection,Menu.this);
			add(afficherDonnées);
			afficherDonnées.repaint();
			Menu.this.setVisible(true);
		}
	}
    
    
    private class MenuActionDésinstallation implements ActionListener {
		public void  actionPerformed(ActionEvent e)  {
			
			getContentPane().removeAll();
			DeleteInstall deleteInstall = new DeleteInstall(connection,Menu.this);
			add(deleteInstall);
			deleteInstall.repaint();
			setVisible(true);
		}
	}
    
    private class MenuActionSoftwaresAcquisDate implements ActionListener {
		public void  actionPerformed(ActionEvent e)  {
			getContentPane().removeAll();
			SoftwaresAcquisDate softwaresAcquisDate = new SoftwaresAcquisDate(connection,Menu.this);
			add(softwaresAcquisDate);
			softwaresAcquisDate.repaint();
			setVisible(true);
		}
	}
    
    private class MenuActionSoftwareEditeur implements ActionListener {
		public void  actionPerformed(ActionEvent e)  {
			getContentPane().removeAll();
			SoftwareEditeur softwareEditeur = new SoftwareEditeur(connection,Menu.this);
			add(softwareEditeur);
			softwareEditeur.repaint();
			setVisible(true);
		}
	}
  
    public Connection getConnect() {
		return connection;}
/*	public Container getContainer() { 
		return container; }*/

    
}