package gui;

import java.awt.Color;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WelcomePage2 {
	JFrame frame;		//declare JFrame
	JLabel lblTitle,lblExit, lblMin,lblPic, lblCopy,lblFollow,lblFac;		//declare labels
	JPanel pnl1,pnl2,pnl3;		//declare panels
	JButton btnLogin;		//declare buttons
	
	//default constructor 
	public WelcomePage2() {			
		frame= new JFrame();		//initialize JFrame
		frame.setExtendedState(frame.MAXIMIZED_BOTH);		//make Frame fullScreen
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);	//operation to close frame
		
		pnl1 = new JPanel();	//initialize pnl1
		pnl1.setBounds(0,0,1300,40);	
		pnl1.setBackground(new Color(0, 128, 255));	//set background color
		pnl1.setLayout(null);		//set  the layout of pnl1 to null	
		frame.add(pnl1);			// add pnl1 in frame
		
		pnl2 = new JPanel();	//initialize pnl2
		pnl2.setBounds(0,40,1300,620);		
		pnl2.setBackground(Color.RED);		//set Background color
		pnl2.setLayout(null);			//set the layout to null
		frame.add(pnl2);			//add pnl2 in frame
		
		//initialize label and set text
		lblCopy = new JLabel("Copyright © 2022 Hotel Luton. All Rights Reserved. Developed by Group4");
		lblCopy.setBounds(20,680,1000,30);
		lblCopy.setForeground(new Color(0,0,0));	//set Background color
		lblCopy.setFont(new Font("Arial", Font.PLAIN, 15));		//set size and style of font
		frame.add(lblCopy);		//add lblCopy in frame
		
		lblFollow= new JLabel("follow us In");				//initialize label and set text
		lblFollow.setBounds(1020,680,110,30);
		lblFollow.setForeground(new Color(0,0,0));	//set background color black
		lblFollow.setFont(new Font("Arial", Font.PLAIN, 15));	//set size and style of font
		frame.add(lblFollow);	//add lblFollow in frame
		
		lblFac= new JLabel();		//initialize lblFac
		lblFac.setBounds(1120,680,100,30);
		lblFac.setIcon(new javax.swing.ImageIcon(getClass().getResource("social.png")));	//set image in label
		frame.add(lblFac);		//add lblFac in JFrame
		
		
		lblTitle = new JLabel("Welcome to offical page of Hotel Luton");	//initialize and set text of label
		lblTitle.setBounds(500,5,500,30);
		lblTitle.setForeground(new Color(224, 222, 216));
		lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
		pnl1.add(lblTitle);			//add the label in Pnl1
		
		lblExit = new JLabel("X");		//initialize and set text of label
		lblExit.setForeground(new Color(224, 222, 216));
		lblExit.setFont(new Font("Arial", Font.BOLD, 20));	//set the style and size of label 
		lblExit.setBounds(1250, 8, 40, 25);
		pnl1.add(lblExit);	//add lblExit to pnl1
		
		// Action listener for  lblExit
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);		//close the frame
			}
		});

		lblMin = new JLabel("-");
		lblMin.setForeground(new Color(224, 222, 216));
		lblMin.setFont(new Font("Arial", Font.BOLD, 40));
		lblMin.setBounds(1220, 0, 40, 35);
		pnl1.add(lblMin);
		
		// Action listener for lblMin  
		lblMin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setState(Frame.ICONIFIED);	//minimize the frame
			}
		});

		btnLogin = new JButton("Login In"); //initialize and set Text of button
		btnLogin.setBounds(20,20,86,25);
		btnLogin.setFocusable(false);		//remove focus from the button
		btnLogin.setBackground(Color.WHITE);	//set Background color of button to White  
			// Action listener for button login
		btnLogin.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				new LoginPage1();	//call loginPage 
				
//				frame.dispose();	//close the welcome frame
				
			}

		});
		
		lblPic = new JLabel();		//initialize lblPic
		lblPic.setBounds(0,0,1300,620);	//set bounds of lblPic
		lblPic.setIcon(new javax.swing.ImageIcon(getClass().getResource("00.jpg")));	//set image icon of lblPic

		pnl2.add(lblPic); //add lblPic in pnl2
		lblPic.add(btnLogin);	//add button login in lblPic
		

		frame.setUndecorated(true);			//set the frame undecorated
		frame.setLayout(null);				//set the layout of frame to null
		frame.setVisible(true);				//make the frame visible
		
		
	}
//	main method to run the code
	public static void main(String[] args) {
		new WelcomePage2();		//allocate memory to 
	}

}
