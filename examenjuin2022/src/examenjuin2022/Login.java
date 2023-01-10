package examenjuin2022;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import accessBD.*;

public class Login extends JPanel {
	
	private JLabel labelTitre, labelDemandeConnexion, labelLogin, labelPassword;
	private JButton boutonLogin;  
	private JTextField zoneTextelogin;
	private JPasswordField zoneTextePassword;
	private Connection connection;
	private LoginFenetre loginFenetre;
	private Login loginConnect;
	
	public Login(LoginFenetre loginFenetre) {
		
		this.setLayout(null);
		
		labelTitre = new JLabel("Bienvenue"); 
		labelTitre.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,25));
		add(labelTitre); 
		labelTitre.setBounds(100,20,255,50); 
		labelDemandeConnexion = new JLabel("Merci de vous connecter pour accéder à  l'application"); 
		add(labelDemandeConnexion); 
		labelDemandeConnexion.setBounds(100,50,400,50); 
		
		labelLogin = new JLabel("Login :");
		add(labelLogin); 
		labelLogin.setBounds(100,100,150,50); 
		zoneTextelogin = new  JTextField();
		add(zoneTextelogin); 
		zoneTextelogin.setBounds(100,150,300,30); 
		
		labelPassword = new JLabel("Mot de passe :");
		add(labelPassword); 
		labelPassword.setBounds(100,200,500,50); 
		zoneTextePassword = new JPasswordField();
		add(zoneTextePassword); 
		zoneTextePassword.setBounds(100,250,300,30); 
		
		boutonLogin = new JButton(" Login "); 
		add(boutonLogin); 
		boutonLogin.setBounds(100,320,120,40);
		boutonLogin.setBackground(Color.LIGHT_GRAY);
		ActionBoutonLogin actionBoutonLogin = new ActionBoutonLogin();
		boutonLogin.addActionListener(actionBoutonLogin);
		
		
		this.loginFenetre=loginFenetre;
		loginConnect=this;
		setVisible(true);	
	}
	
	public class ActionBoutonLogin implements ActionListener{
		public void actionPerformed( ActionEvent a){
			if(a.getSource()==boutonLogin) {
				try {
					connection = AccessBDGen.connecter("DbInstallations", zoneTextelogin.getText(), zoneTextePassword.getText());
					JOptionPane.showMessageDialog(null,"Connexion réussie", "Connecter", JOptionPane.INFORMATION_MESSAGE);
					Menu fenetrePrincipal = new Menu(loginConnect);
					loginFenetre.dispose();
				}
				catch(SQLException e) {
					JOptionPane.showMessageDialog(null,e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	public Connection getConnect() { 
		return connection;
	}
}
