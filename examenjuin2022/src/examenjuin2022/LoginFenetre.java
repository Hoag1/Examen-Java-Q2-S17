package examenjuin2022;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import accessBD.*; 

public class LoginFenetre extends JFrame {
	private Container cont;
	
	public LoginFenetre() {
		
	    cont = getContentPane();
	    Login login= new Login(this);
	    add(login);
	    setTitle("Bienvenue");
	    setBounds(500,200,500,450);
	    setResizable(false);

	    addWindowListener(new WindowAdapter() { 
	    	public void windowClosing(WindowEvent e) {
	    		System.exit(0);
	    	}
	    });

		setVisible(true);
	}
}