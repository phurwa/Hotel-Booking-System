package gui;
//import useful library
import java.awt.Color;


import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import library1.MyJDBC;
import library1.MyLibs;

public class Registration1 implements ActionListener{
	//declare variables
	JFrame frame;
	JPanel pnl1, pnl2;
	JLabel lblExit, lblMin, lblTitle,lblFullName,lblAddress,lblEmailID,lblPhoneNo,lblCustomerType,lblCardNo,lblPass,lblCompanyName;
	JPasswordField txtPass;
	JTextField txtFullName,txtAddress,txtEmailID,txtPhoneNo,txtCardNo,txtCompanyName;
	JComboBox cmbCustomerType;
	JButton btnSave, btnClose;
	
	//create default constructor 
	public Registration1() {
		frame = new JFrame();		//initialize JFrame
		frame.setBounds(360,130,650,320);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);	//operation to close frame
		
		pnl1 = new JPanel();		//initialize pnl1
		pnl1.setBounds(0, 0, 650, 40);
		pnl1.setBackground(new Color(0, 128, 255));
		pnl1.setLayout(null);
		frame.add(pnl1);		//add pnl1 in Frame

		pnl2 = new JPanel();			//initialize pnl2
		pnl2.setBounds(0, 40, 650, 280);
		pnl2.setBackground(Color.lightGray);
		pnl2.setLayout(null);
		frame.add(pnl2);			//add pnl2 in frame

		lblTitle = new JLabel("Customer Registration Form");	//initialize label and set text
		lblTitle.setForeground(new Color(224, 222, 216));
		lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
		lblTitle.setBounds(180, 5, 300, 25);
		pnl1.add(lblTitle);		//add lblTitle in pnl2

		lblExit = new JLabel("X");		//initialize and set text
		lblExit.setForeground(new Color(224, 222, 216));
		lblExit.setFont(new Font("Arial", Font.BOLD, 20));
		lblExit.setBounds(625, 8, 40, 25);
		pnl1.add(lblExit);	//add lblExit in pnl1
		//Action Listener for lblExit
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);			//close the frame
			}
		});

		lblMin = new JLabel("-");		//initialize and set text
		lblMin.setForeground(new Color(224, 222, 216));
		lblMin.setFont(new Font("Arial", Font.BOLD, 40));
		lblMin.setBounds(595, 0, 40, 35);	
		pnl1.add(lblMin);	//add lblMin in pnl1
		
		//Action Listener for lblMin
		lblMin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setState(Frame.ICONIFIED);	//minimize the frame
			}
		});
		
		lblFullName= new JLabel("Full Name:");	//initialize
		lblFullName.setBounds(30,20,90,25);
		lblFullName.setForeground(new Color(0,0,0));
		lblFullName.setFont(new Font("Arial", Font.PLAIN, 15));
		pnl2.add(lblFullName);
		
		txtFullName = new JTextField();		//initialize
		txtFullName.setBounds(125, 20, 175, 25);
		txtFullName.setForeground(Color.CYAN);
		txtFullName.setBackground(new Color(99, 121, 133));
		txtFullName.setFont(new Font("Arial", Font.PLAIN, 15));
		pnl2.add(txtFullName);
		
		lblAddress= new JLabel("Address:");		//initialize
		lblAddress.setBounds(340,20,90,25);
		lblAddress.setForeground(new Color(0,0,0));
		lblAddress.setFont(new Font("Arial", Font.PLAIN, 15));
		pnl2.add(lblAddress);
		
		txtAddress = new JTextField();		//initialize
		txtAddress.setBounds(425, 20, 175, 25);
		txtAddress.setForeground(Color.CYAN);
		txtAddress.setBackground(new Color(99, 121, 133));
		txtAddress.setFont(new Font("Arial", Font.PLAIN, 15));
		pnl2.add(txtAddress);
		
		lblEmailID= new JLabel("Email ID:");		//initialize
		lblEmailID.setBounds(30,60,90,25);
		lblEmailID.setForeground(new Color(0,0,0));
		lblEmailID.setFont(new Font("Arial", Font.PLAIN, 15));
		pnl2.add(lblEmailID);
		
		txtEmailID = new JTextField();		//initialize
		txtEmailID.setBounds(125, 60, 175, 25);
		txtEmailID.setForeground(Color.CYAN);
		txtEmailID.setBackground(new Color(99, 121, 133));
		txtEmailID.setFont(new Font("Arial", Font.PLAIN, 15));
		pnl2.add(txtEmailID);
		
		lblPhoneNo= new JLabel("Phone No:");	//initialize
		lblPhoneNo.setBounds(340,60,90,25);
		lblPhoneNo.setForeground(new Color(0,0,0));
		lblPhoneNo.setFont(new Font("Arial", Font.PLAIN, 15));
		pnl2.add(lblPhoneNo);
		
		txtPhoneNo = new JTextField();		//initialize
		txtPhoneNo.setBounds(425, 60, 175, 25);
		txtPhoneNo.setForeground(Color.CYAN);
		txtPhoneNo.setBackground(new Color(99, 121, 133));
		txtPhoneNo.setFont(new Font("Arial", Font.PLAIN, 15));
		pnl2.add(txtPhoneNo);
		
		lblCustomerType= new JLabel("UserType:");	//initialize
		lblCustomerType.setBounds(30,100,90,25);
		lblCustomerType.setForeground(new Color(0,0,0));
		lblCustomerType.setFont(new Font("Arial", Font.PLAIN, 15));
		pnl2.add(lblCustomerType);
		
		cmbCustomerType = new JComboBox();	//initialize
		cmbCustomerType.setBounds(125, 100, 175, 25);
		//create array for cmbUserType
		cmbCustomerType.setModel(new DefaultComboBoxModel(new String[] {"Normal Customer", "Corporate Customer"}));
		cmbCustomerType.setForeground(Color.CYAN);
		cmbCustomerType.setBackground(new Color(99, 121, 133));
		cmbCustomerType.setFont(new Font("Arial", Font.PLAIN, 15));
		pnl2.add(cmbCustomerType);
		
		lblCardNo= new JLabel("Card No:");	//initialize
		lblCardNo.setBounds(340,100,90,25);
		lblCardNo.setForeground(new Color(0,0,0));
		lblCardNo.setFont(new Font("Arial", Font.PLAIN, 15));
		pnl2.add(lblCardNo);
		
		txtCardNo = new JTextField();	//initialize
		txtCardNo.setBounds(425, 100, 175, 25);
		txtCardNo.setForeground(Color.CYAN);
		txtCardNo.setBackground(new Color(99, 121, 133));
		txtCardNo.setFont(new Font("Arial", Font.PLAIN, 15));
		pnl2.add(txtCardNo);
		
		lblPass= new JLabel("Password:");	//initialize
		lblPass.setBounds(30,140,90,25);
		lblPass.setForeground(new Color(0,0,0));
		lblPass.setFont(new Font("Arial", Font.PLAIN, 15));
		pnl2.add(lblPass);
		
		txtPass = new JPasswordField();		//initialize
		txtPass.setBounds(125, 140, 175, 25);
		txtPass.setForeground(Color.CYAN);
		txtPass.setBackground(new Color(99, 121, 133));
		txtPass.setFont(new Font("Arial", Font.PLAIN, 15));
		pnl2.add(txtPass);
		
		lblCompanyName= new JLabel("Company:");	//initialize
		lblCompanyName.setBounds(340,140,90,25);
		lblCompanyName.setForeground(new Color(0,0,0));
		lblCompanyName.setFont(new Font("Arial", Font.PLAIN, 15));
		pnl2.add(lblCompanyName);
		
		txtCompanyName = new JTextField();	//initialize
		txtCompanyName.setBounds(425, 140, 175, 25);
		txtCompanyName.setForeground(Color.CYAN);
		txtCompanyName.setBackground(new Color(99, 121, 133));
		txtCompanyName.setFont(new Font("Arial", Font.PLAIN, 15));
		pnl2.add(txtCompanyName);
		
		
		btnSave = new JButton("Save");	//initialize
		btnSave.setBounds(215,195,80,25);
		btnSave.setBackground(Color.GREEN);
		btnSave.setFocusable(false);
		btnSave.addActionListener(this);
		pnl2.add(btnSave);
		
		btnClose = new JButton("Close");	//initialize 
		btnClose.setBounds(335,195,80,25);
		btnClose.setBackground(Color.red);
		btnClose.setFocusable(false);
		btnClose.addActionListener(this);
		pnl2.add(btnClose);
		
		btnClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new LoginPage1();
				frame.setVisible(false);

			}

		});
		
		frame.setUndecorated(true);		//make frame unable to decorate
		frame.setLayout(null);		//set layout null
		frame.setVisible(true);		//make the frame visible
	}
	
	// method actionPerformed()
	// to get the action performed
	// by the user and act accordingly
//	@Override
	public void actionPerformed(ActionEvent e)
	{	
		// convert to text
		 String def = "";
		String fullName = txtFullName.getText();
		String address = txtAddress.getText();
		String phoneNo = txtPhoneNo.getText();
		String emailID = txtEmailID.getText();
		String customerType = cmbCustomerType.getSelectedItem().toString();
		String cardNo = txtCardNo.getText();
		String password = txtPass.getText();
		String companyName = txtCompanyName.getText();
		
		if (e.getSource() == btnSave) {
			MyLibs user = new MyLibs();
			user.setFullName(fullName);
			user.setAddress(address);
			user.setPhoneNo(phoneNo);
			user.setEmailID(emailID);
			user.setCustomerType(customerType);
			user.setCardNo(cardNo);
			user.setPassword(password);
			user.setCompanyName(companyName);
			// Try- catch block
			try {
				// Regex to check email validation
				String emailRegex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
				
				// Regex to check password validation
				String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{5,15}$";
				
				// Check some validation
				if(fullName.length()==0 || address.length()==0 || emailID.length()==0 ||
						phoneNo.length()==0 || customerType.length()==0 || cardNo.length()==0||password.length()==0) {
					
					JOptionPane.showMessageDialog(null, "Provide all information except company name");
				}

				else if (!emailID.matches(emailRegex)) {
					JOptionPane.showMessageDialog(null, "Email not valid");
				}
				else if (!password.matches(passwordRegex)) {
					JOptionPane.showMessageDialog(null, "Password must contain at least 5 character, one uppercase, one lowercase, one digit, one special character");
				}
				else {
					
					MyJDBC jdbc = new MyJDBC();
					boolean result = jdbc.save(user);
					if(result ==true) {
						JOptionPane.showMessageDialog(null, "user added successfully");					
							// Clearing fields
						    txtFullName.setText(def);
						    txtAddress.setText(def);
						    txtPhoneNo.setText(def);
							txtEmailID.setText(def);
							cmbCustomerType.setSelectedIndex(-1);
							txtCardNo.setText(def);
							txtPass.setText(def);
							txtCompanyName.setText(def);
			            
					}	
					else {
						JOptionPane.showMessageDialog(null, "Error Please Try again");

					}
				}
					}
			catch(Exception ex) {
				JOptionPane.showMessageDialog(null, ex);
			}
		}

			}


	//main method to run the code
	public static void main(String[] args) {
		new Registration1();
	}
}
