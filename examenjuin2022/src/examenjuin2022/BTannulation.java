package examenjuin2022;


import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import accessBD.*;

public class BTannulation extends JPanel {
	private JButton boutonAnnulation;
	private Installation nouvelleInstallation;
	private Connection connection;
	
	public BTannulation(Installation nouvelleInstallation,Connection connection) {
		
		this.nouvelleInstallation=nouvelleInstallation;
		this.connection=connection;
		
		//Bouton Annulation
		boutonAnnulation = new JButton("Annulation");
		add(boutonAnnulation);
		ActionAnnulation actionAnnulation = new ActionAnnulation();
		boutonAnnulation.addActionListener(actionAnnulation);
		
		setVisible(true);
	}
	
	private class ActionAnnulation implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==boutonAnnulation) {
				nouvelleInstallation.ActionAnnulation(connection);
			}
		}
	}
}	
