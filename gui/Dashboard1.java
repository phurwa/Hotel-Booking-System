package gui;

//import all useful library
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Dashboard1 {
	// declare variables
	JFrame frame;
	JLabel lblTitle, lblMin, lblExit, lblRecp, lblService, lblPic, lblLog;
	JButton btnCheckIn, btnCheckout;
	JPanel pnl1, pnl2;

	// default constructor
	public Dashboard1() {
		// initialize and memory allocate
		frame = new JFrame();
		frame.setBounds(210, 70, 900, 540);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.WHITE);

		pnl1 = new JPanel();
		pnl1.setBounds(0, 0, 900, 40);
		pnl1.setBackground(new Color(0, 128, 255));
		pnl1.setLayout(null);
		frame.add(pnl1);

		pnl2 = new JPanel();
		pnl2.setBounds(0, 40, 900, 125);
		pnl2.setBackground(Color.lightGray);
		pnl2.setLayout(null);
		frame.add(pnl2);

		lblTitle = new JLabel("Receptionist Dashboard");
		lblTitle.setForeground(new Color(224, 222, 216));
		lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
		lblTitle.setBounds(375, 5, 230, 25);
		pnl1.add(lblTitle);

		lblExit = new JLabel("X");
		lblExit.setForeground(new Color(224, 222, 216));
		lblExit.setFont(new Font("Arial", Font.BOLD, 20));
		lblExit.setBounds(870, 8, 40, 25);
		pnl1.add(lblExit);
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});

		lblMin = new JLabel("-");
		lblMin.setForeground(new Color(224, 222, 216));
		lblMin.setFont(new Font("Arial", Font.BOLD, 40));
		lblMin.setBounds(830, 0, 40, 35);
		pnl1.add(lblMin);
		lblMin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setState(Frame.ICONIFIED);
			}
		});

		lblRecp = new JLabel("Recptionist");
		lblRecp.setForeground(Color.DARK_GRAY);
		lblRecp.setFont(new Font("Arial", Font.BOLD, 15));
		lblRecp.setBounds(380, 12, 120, 25);
		pnl2.add(lblRecp);

//		lblService = new JLabel("services for customer");
//		lblService.setForeground(Color.DARK_GRAY);
//		lblService.setFont(new Font("Arial", Font.BOLD, 15));
//		lblService.setBounds(480, 12, 200, 25);
//		pnl2.add(lblService);

		btnCheckIn = new JButton("Check In");
		btnCheckIn.setBounds(300, 50, 120, 30);
		btnCheckIn.setBackground(Color.ORANGE);
		btnCheckIn.setFocusable(false);
		pnl2.add(btnCheckIn);
		// action event to open check in Page
		btnCheckIn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// open the check in page
				CheckInPage checkin = new CheckInPage();
				checkin.startShowTable();
				frame.dispose(); // close the current page

			}

		});

		btnCheckout = new JButton("Check Out");
		btnCheckout.setBounds(440, 50, 120, 30);
		btnCheckout.setBackground(Color.ORANGE);
		btnCheckout.setFocusable(false);
		pnl2.add(btnCheckout);
		// action event to open check out page
		btnCheckout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// opens checkout page when btnCheckout is clicked
				CheckOutPage checkOut = new CheckOutPage();
				checkOut.showTable();
				frame.dispose(); // close the current page
			}

		});

		lblLog = new JLabel("Log Out");
		lblLog.setForeground(Color.BLACK);
		lblLog.setBounds(730, 50, 80, 30);
		lblLog.setFont(new Font("Arial", Font.BOLD, 16));
		pnl2.add(lblLog);
		// Action listener for lblLog
		lblLog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { // exit the page 
				frame.dispose();

			}

		});

		lblPic = new JLabel(); // initialize lblPic
		lblPic.setBounds(0, 165, 900, 375); // set bounds of lblPic
		lblPic.setIcon(new javax.swing.ImageIcon(getClass().getResource("po.jpg"))); // set image icon of lblPic
		frame.add(lblPic);

		frame.setLayout(null); // set the layout of frame to null
		frame.setVisible(true); // make the frame visible

	}

	// runs the code
	public static void main(String[] args) {
		new Dashboard1();
	}

}
