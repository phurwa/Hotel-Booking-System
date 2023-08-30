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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class MonthlyPay {
	//declare variables
	JFrame frame;
	JPanel pnl1, pnl2;
	JLabel lblRes, lblTitle, lblExit, lblMin, lblFrom, lblTo, lblTotal;
	JTextField txtFrom, txtTo, txtTotal;
	JButton btnPay, btnExit;

	int bid;

	// Get customerID from email
	int id = DatabaseConnectionUtil.getCustomerID(Global.current_email);
	
	//create default constructor
	public MonthlyPay() {
		//initialize and memory allocate
		frame = new JFrame();
		frame.setBounds(400, 160, 400, 260);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.WHITE);

		pnl1 = new JPanel();
		pnl1.setBounds(0, 0, 400, 40);
		pnl1.setBackground(new Color(0, 128, 255));
		pnl1.setLayout(null);
		frame.add(pnl1);

		pnl2 = new JPanel();
		pnl2.setBounds(0, 40, 400, 220);
		pnl2.setBackground(Color.lightGray);
		pnl2.setLayout(null);
		frame.add(pnl2);

		lblRes = new JLabel("Monthly Pay");
		lblRes.setForeground(new Color(224, 222, 216));
		lblRes.setFont(new Font("Arial", Font.BOLD, 20));
		lblRes.setBounds(5, 5, 160, 25);
		pnl1.add(lblRes);

		lblExit = new JLabel("X");
		lblExit.setForeground(new Color(224, 222, 216));
		lblExit.setFont(new Font("Arial", Font.BOLD, 20));
		lblExit.setBounds(375, 8, 40, 25);
		pnl1.add(lblExit);
		//action event to close the frame
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		//action event to minimize the frame
		lblMin = new JLabel("-");
		lblMin.setForeground(new Color(224, 222, 216));
		lblMin.setFont(new Font("Arial", Font.BOLD, 40));
		lblMin.setBounds(350, 0, 40, 35);
		pnl1.add(lblMin);
		lblMin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setState(Frame.ICONIFIED);
			}
		});

		lblTitle = new JLabel("Filter");
		lblTitle.setBounds(170, 5, 60, 30);
		lblTitle.setForeground(Color.BLACK);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
		pnl2.add(lblTitle);

		lblFrom = new JLabel("From");
		lblFrom.setBounds(10, 55, 170, 25);
		lblFrom.setForeground(Color.BLACK);
		lblFrom.setFont(new Font("Arial", Font.BOLD, 15));
		pnl2.add(lblFrom);

		txtFrom = new JTextField();
		txtFrom.setBounds(175, 55, 150, 25);
		txtFrom.setForeground(Color.CYAN);
		txtFrom.setBackground(new Color(99, 121, 133));
		txtFrom.setFont(new Font("Arial", Font.PLAIN, 16));
		pnl2.add(txtFrom);

		lblTo = new JLabel("To");
		lblTo.setBounds(10, 90, 170, 25);
		lblTo.setForeground(Color.BLACK);
		lblTo.setFont(new Font("Arial", Font.BOLD, 15));
		pnl2.add(lblTo);

		txtTo = new JTextField();
		txtTo.setBounds(175, 90, 150, 25);
		txtTo.setForeground(Color.CYAN);
		txtTo.setBackground(new Color(99, 121, 133));
		txtTo.setFont(new Font("Arial", Font.PLAIN, 16));
		pnl2.add(txtTo);

		lblTotal = new JLabel("Total credit Amount");
		lblTotal.setBounds(10, 120, 170, 25);
		lblTotal.setForeground(Color.BLACK);
		lblTotal.setFont(new Font("Arial", Font.BOLD, 15));
		pnl2.add(lblTotal);

		txtTotal = new JTextField();
		txtTotal.setBounds(175, 120, 150, 25);
		txtTotal.setEditable(false);
		txtTotal.setForeground(Color.CYAN);
		txtTotal.setBackground(new Color(99, 121, 133));
		txtTotal.setFont(new Font("Arial", Font.PLAIN, 16));
		pnl2.add(txtTotal);
		
		btnExit = new JButton("Exit");
		btnExit.setBounds(270, 175, 80, 30);
		btnExit.setBackground(Color.ORANGE);
		btnExit.setFocusable(false);
		pnl2.add(btnExit);
		//action event to close the current page 
		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//opens booking Page when btnExit is clicked
				new BookingPage2();
				frame.dispose();
				
			}
			
		});

		btnPay = new JButton("Pay");
		btnPay.setBounds(170, 175, 80, 30);
		btnPay.setBackground(Color.GREEN);
		btnPay.setFocusable(false);
		pnl2.add(btnPay);
		btnPay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					// establish connection object
					Connection con = DatabaseConnectionUtil.ConnectDB();

					// query to update paymentStatus from receipt table
					String q1 = "update receipt set paymentStatus=? where bookingID='" + bid + "'";
					PreparedStatement pst4 = con.prepareStatement(q1);
					pst4.setString(1, "Cleared");
					pst4.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Payment clear successfully");

					txtTotal.setText("");
					txtFrom.setText("");
					txtTo.setText("");

				} catch (Exception ex) {
					System.out.println(ex);
				}

			}

		});

		// try catch block
		try {
			//print customerID in console
			System.out.println("The customer id is: " + id);

			// establish connection
			Connection con = DatabaseConnectionUtil.ConnectDB();

			// Query to retrieve data from booking table 
			String queryArrival = "select bookingID, arrivalDate from booking where customerID='" + id + "'";
			PreparedStatement pst1 = con.prepareStatement(queryArrival);
			ResultSet rst1 = pst1.executeQuery();

			String arrival = "";

			while (rst1.next()) {
				bid = rst1.getInt(1);
				arrival = rst1.getString(2);
			}

			System.out.println("The booking id is: " + bid);

			System.out.println("The arrival date is: " + arrival);

			// Date and Time format
			DateTime dt = new DateTime(arrival);

			DateTime dt1 = dt.plusDays(30);

			DateTimeFormatter dateformat = DateTimeFormat.forPattern("yyyy-MM-dd");
			System.out.println("Today date: " + dt1.toString(dateformat));

			txtFrom.setText(arrival);
			txtTo.setText(dt1.toString(dateformat));

			// Query to get credit amount
			String query2 = "select grandTotal \r\n"
					+ "from booking b\r\n"
					+ "inner join customer c\r\n"
					+ "on b.customerID=c.customerID\r\n"
					+ "inner join receipt r\r\n"
					+ "on r.bookingID=b.bookingID\r\n"
					+ "where c.customerID='"+id+"' and r.paymentStatus='Credit'";
			PreparedStatement pst2 = con.prepareStatement(query2);
			ResultSet rst2 = pst2.executeQuery();

			float amt = 0.0f;

			while (rst2.next()) {
				amt = rst2.getFloat(1);
			}

			System.out.println("The amount is: " + amt);

			String at = Float.toString(amt);
			txtTotal.setText(at);

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error");
		}
		frame.setLayout(null);	//set the layout of frame to null	
		frame.setVisible(true);	//make the frame visible
			
	}
	//main method to run the code
	public static void main(String[] args) {
		new MonthlyPay();
	}

}
