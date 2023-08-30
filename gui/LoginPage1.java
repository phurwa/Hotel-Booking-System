package gui;
//import useful library
import java.awt.Color;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import library1.MyJDBC;
import library1.MyLibs;

public class LoginPage1 implements ActionListener {
	// declare variables
	JFrame frame;
	JLabel lblTitle, lblExit, lblMin, lblPic, lblEmail, lblPass, lblReg;
	JTextField txtEmail;
	JPasswordField txtPass;
	JButton btnLogin, btnExit;
	JPanel pnl1, pnl2, pnl3;

	// create default constructor
	public LoginPage1() {
		// initialize and memory allocate
		frame = new JFrame(); 
		frame.setBounds(300, 140, 730, 360);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE); // close the frame

		pnl1 = new JPanel();
		pnl1.setBounds(0, 0, 730, 40);
		pnl1.setBackground(new Color(0, 128, 255));
		pnl1.setLayout(null);
		frame.add(pnl1);

		pnl2 = new JPanel();
		pnl2.setBounds(0, 40, 730, 360);
		pnl2.setBackground(Color.lightGray);
		pnl2.setLayout(null);
		frame.add(pnl2);

		lblTitle = new JLabel("Login Page");
		lblTitle.setForeground(new Color(224, 222, 216));
		lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
		lblTitle.setBounds(310, 5, 110, 25);
		pnl1.add(lblTitle);

		lblExit = new JLabel("X");
		lblExit.setForeground(new Color(224, 222, 216));
		lblExit.setFont(new Font("Arial", Font.BOLD, 20));
		lblExit.setBounds(695, 8, 40, 25);
		pnl1.add(lblExit);
		// Action listener for lblExit
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { // exit the frame
				System.exit(0);
			}
		});

		lblMin = new JLabel("-");
		lblMin.setForeground(new Color(224, 222, 216));
		lblMin.setFont(new Font("Arial", Font.BOLD, 40));
		lblMin.setBounds(660, 0, 40, 35);
		pnl1.add(lblMin);
		// Action listener for lblMin
		lblMin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setState(Frame.ICONIFIED); // minimize the frame
			}
		});

		lblPic = new JLabel();
		lblPic = new JLabel();
		lblPic.setBounds(0, 0, 400, 360);
		lblPic.setIcon(new javax.swing.ImageIcon(getClass().getResource("log.png"))); // set image in lblPic
		pnl2.add(lblPic);

		lblEmail = new JLabel("Email ID:");
		lblEmail.setBounds(410, 55, 120, 25);
		lblEmail.setForeground(new Color(0, 0, 0));
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 20));
		pnl2.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setBounds(540, 55, 170, 25);
		txtEmail.setForeground(Color.CYAN);
		txtEmail.setBackground(new Color(99, 121, 133));
		txtEmail.setFont(new Font("Arial", Font.PLAIN, 16));
		pnl2.add(txtEmail);
		// Placeholder for email text field
		txtEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtEmail.getText().equals("Enter your Email")) {
					txtEmail.setText("");
				}
				txtEmail.setForeground(Color.CYAN);
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtEmail.getText().equals("")) {
					txtEmail.setText("Enter your Email");
				}
				txtEmail.setForeground(Color.CYAN);
			}
		});

		lblPass = new JLabel("Password:");
		lblPass.setBounds(410, 90, 120, 25);
		lblPass.setForeground(new Color(0, 0, 0));
		lblPass.setFont(new Font("Arial", Font.PLAIN, 20));
		pnl2.add(lblPass);

		txtPass = new JPasswordField();
		txtPass.setBounds(540, 90, 170, 25);
		txtPass.setForeground(Color.CYAN);
		txtPass.setBackground(new Color(99, 121, 133));
		txtPass.setFont(new Font("Arial", Font.PLAIN, 16));
		pnl2.add(txtPass);
		// Placeholder for password field
		txtPass.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				String password = String.valueOf(txtPass.getPassword());

				if (password.toLowerCase().equals(" Enter Password")) {
					txtPass.setText("");
					txtPass.setForeground(Color.CYAN);

				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				String password = String.valueOf(txtPass.getPassword());

				if (password.toLowerCase().equals("Enter Password") || password.toLowerCase().equals("")) {
					txtPass.setText(" Enter your Password");
					txtPass.setEchoChar((char) 0);
					txtPass.setForeground(Color.CYAN);
				}
			}
		});

		btnLogin = new JButton("login");
		btnLogin.setBounds(440, 145, 80, 25);
		btnLogin.setBackground(Color.GREEN);
		btnLogin.setFocusable(false);
		btnLogin.addActionListener(this);
		pnl2.add(btnLogin);

		btnExit = new JButton("Close");
		btnExit.setBounds(555, 145, 80, 25);
		btnExit.setBackground(Color.RED);
		btnExit.setFocusable(false);
		btnExit.addActionListener(this);
		pnl2.add(btnExit);
		//action event to close the frame
		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();

			}

		});

		lblReg = new JLabel(" No Account? Create one!");
		lblReg.setForeground(Color.BLACK);
		lblReg.setBounds(440, 185, 300, 25);
		lblReg.setFont(new Font("Arial", Font.PLAIN, 16));
		pnl2.add(lblReg);
		// Action listener for lblReg
		lblReg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { // exit the page and back to registration Page
				new Registration1();

				frame.dispose();

			}

		});

		frame.setLayout(null); // set layout of frame null
		frame.setVisible(true); // make the frame visible

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// convert to text
		String emailID = txtEmail.getText();
		String pass = txtPass.getText();

		if (e.getSource() == btnLogin) {
			MyLibs user = new MyLibs();
			user.setEmailID(emailID);
			user.setPassword(txtPass.getText());
			MyJDBC jdbc = new MyJDBC();
			user = jdbc.login(user);
			if (emailID.length() == 0 || pass.length() == 0) { // show dialog box if fields are empty
				JOptionPane.showMessageDialog(null, "Fields are requried to fill");
			}

			if (emailID.equals("admin1@gmail.com") && pass.equals("admin1")) {
				JOptionPane.showMessageDialog(null, "Welcome Admin1");
				Dashboard1 dash = new Dashboard1(); // exit and open Dashboard1
				frame.setVisible(false);
			} else if (emailID.equals("admin2@gmail.com") && pass.equals("admin2")) {
				JOptionPane.showMessageDialog(null, "Welcome Admin2");
				StaffDasboard dash = new StaffDasboard(); // exit and open StaffDasboard
				frame.setVisible(false);
			} else if (user.getCustomerID() > 0) {
				JOptionPane.showMessageDialog(null, "Login Successfull");
				Global.current_email = emailID; // exit and open bookingPage2
				BookingPage2 booking = new BookingPage2();
				booking.table_load();
				frame.dispose();

			}

			else {
				JOptionPane.showMessageDialog(null, "Invalid Username & Password");
			}
		}

	}

//main method
	public static void main(String[] args) {
		new LoginPage1();
	}

}
