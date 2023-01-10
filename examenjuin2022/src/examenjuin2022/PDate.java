package examenjuin2022;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import accessBD.*;

public class PDate extends JPanel {
	private JComboBox<Integer> année,jour;
	private JComboBox<String>  mois;
	private String[] listeMois = {"January","February","March","April","May","June","July","August","September","October","November","December"};
	private java.util.Date dateJava;
	
	public PDate() {
		setLayout(new GridLayout(1,3,1,1));
		jour = new JComboBox<Integer>();
		mois = new JComboBox<String>(listeMois);
		année = new JComboBox<Integer>();
		
		for(int annéeI=2021;annéeI>=1995;annéeI--) {
			année.addItem(annéeI);
		}
		for(int jourI=1;jourI<=31;jourI++) {
			jour.addItem(jourI); 
		}
		
		add(année);
		add(mois);
		add(jour);
		
		setVisible(true);
		}
		
		public Date getDate() {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String dateText = année.getSelectedItem()+"0"+(mois.getSelectedIndex()+1)+jour.getSelectedItem();
			try {
				dateJava = sdf.parse(dateText);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			System.out.println(dateJava);

			return dateJava;
		}
		
		public String getDateAcquis() {
			String dateString = année.getSelectedItem()+"-0"+(mois.getSelectedIndex()+1)+"-"+jour.getSelectedItem();
			return dateString;
		}
	}

