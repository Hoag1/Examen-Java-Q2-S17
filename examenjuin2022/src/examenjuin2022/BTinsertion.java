package examenjuin2022;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import accessBD.*;

public class BTinsertion extends JPanel{
	private JButton boutonInsertion;
	private Installation nouvelleInstallation;
	private Connection connection;
	
	public BTinsertion(Installation nouvelleInstallation,Connection connection) {
		
		this.nouvelleInstallation=nouvelleInstallation;
		this.connection=connection;
		
		//Bouton insertion
		boutonInsertion = new JButton("Insertion");
		add(boutonInsertion);
		
		ActionInsertion actionInsertion= new ActionInsertion();
		boutonInsertion.addActionListener(actionInsertion);
		
		setVisible(true);
	}
	
	private class ActionInsertion implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==boutonInsertion) {
				nouvelleInstallation.AjoutNouvelleInstallation(connection);
			}
		}
	}
}
