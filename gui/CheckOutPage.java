package gui;

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
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import net.proteanit.sql.DbUtils;

public class CheckOutPage {
	// declare variables
	JFrame frame;
	JPanel pnl1, pnl2;
	JLabel lblRes, lblExit, lblMin, lblTitle, lblCheckOut;
	JTextField txtCheckOut;
	JButton btnProvisional, btnGenerateBill, btnCheckOut, btnView, btnclose;
	private JTable table;
	String email;

	// Instance variable
	String roomNo;
	static int roomNoValue;

	// To get instance date
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date();
	String currentDate = dateFormat.format(date);

	// Get customer id from email
	int id = DatabaseConnectionUtil.getCustomerID(Global.current_email);

	// Default constructor to initialze the contents of the frame
	public CheckOutPage() {
		// initialize and memory allocate
		frame = new JFrame();
		frame.setBounds(210, 70, 900, 540);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.WHITE);

		pnl1 = new JPanel();
		pnl1.setBounds(0, 0, 910, 40);
		pnl1.setBackground(new Color(0, 128, 255));
		pnl1.setLayout(null);
		frame.add(pnl1);

		pnl2 = new JPanel();
		pnl2.setBounds(0, 40, 910, 125);
		pnl2.setBackground(Color.lightGray);
		pnl2.setLayout(null);
		frame.add(pnl2);

		lblRes = new JLabel("Customer Checkout");
		lblRes.setForeground(new Color(224, 222, 216));
		lblRes.setFont(new Font("Arial", Font.BOLD, 20));
		lblRes.setBounds(360, 5, 200, 25);
		pnl1.add(lblRes);

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

		lblTitle = new JLabel("Service to Guest");
		lblTitle.setBounds(320, 10, 250, 20);
		lblTitle.setForeground(Color.BLACK);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 15));
		pnl2.add(lblTitle);

		lblCheckOut = new JLabel("Today Check Out:");
		lblCheckOut.setBounds(575, 50, 170, 25);
		lblCheckOut.setForeground(Color.BLACK);
		lblCheckOut.setFont(new Font("Arial", Font.PLAIN, 15));
		pnl2.add(lblCheckOut);

		txtCheckOut = new JTextField();
		txtCheckOut.setBounds(710, 50, 160, 25);
		txtCheckOut.setEditable(false);
		txtCheckOut.setForeground(Color.CYAN);
		txtCheckOut.setBackground(new Color(99, 121, 133));
		txtCheckOut.setFont(new Font("Arial", Font.PLAIN, 16));

		pnl2.add(txtCheckOut);
		txtCheckOut.setText(currentDate);



		btnGenerateBill = new JButton("Generate");
		btnGenerateBill.setBounds(155, 50, 100, 30);
		btnGenerateBill.setBackground(Color.ORANGE);
		btnGenerateBill.setFocusable(false);
		pnl2.add(btnGenerateBill);
		// action listener to open bill
		btnGenerateBill.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Invoice2 invoice = new Invoice2();
				//frame.setVisible(false);
			}

		});

		btnCheckOut = new JButton("CheckOut");
		btnCheckOut.setBounds(265, 50, 100, 30);
		btnCheckOut.setBackground(Color.ORANGE);
		btnCheckOut.setFocusable(false);
		pnl2.add(btnCheckOut);
		// action listener to checkOut selected customer
		btnCheckOut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					
					// Create connection object
					Connection con = DatabaseConnectionUtil.ConnectDB();

					String emailQuery = "select customerID from customer where emailID='" + email + "'";
					PreparedStatement psEmail = con.prepareStatement(emailQuery);
					ResultSet rsEmail = psEmail.executeQuery();

					int cid = 0;
					while (rsEmail.next()) {
						cid = rsEmail.getInt(1);
					}

					System.out.println("The customer id is: " + cid);

					// Query to get RoomID
					String query1 = "select bookingID, roomID from booking where customerID='" + cid
							+ "' and bookingStatus='Checked In'";

					PreparedStatement pst1 = con.prepareStatement(query1);
					ResultSet rst1 = pst1.executeQuery();

					int bid = 0;
					int rid = 0;

					while (rst1.next()) {
						bid = rst1.getInt(1);
						rid = rst1.getInt(2);
					}
					System.out.println("The room id is: " + rid + " and booking id is: " + bid);

					// Query to create update booking status
					String query3 = "Update booking set bookingStatus=? where departureDate='" + currentDate
							+ "' and bookingID='" + bid + "' and customerID='" + cid + "'";
					PreparedStatement pst3 = con.prepareStatement(query3);
					pst3.setString(1, "Checked-Out");
					pst3.execute();

					JOptionPane.showMessageDialog(null, "Customer Check-Out Successfully");

					// Query to update room status
					String roomStatus = "update room set roomStatus=? where roomID='" + rid
							+ "' and roomStatus='Not Available'";
					PreparedStatement pst4 = con.prepareStatement(roomStatus);
					pst4.setString(1, "Vacant");
					pst4.execute();
					showTable();
					con.close();
				} catch (Exception ex) {
					System.out.println(ex);
				}

			}
		});

		btnView = new JButton("View");
		btnView.setBounds(375, 50, 80, 30);
		btnView.setBackground(Color.orange);
		btnView.setFocusable(false);
		pnl2.add(btnView);
		// action listener to open checkOutList
		btnView.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CheckOutList out = new CheckOutList();
				out.showTable();
				frame.dispose();

			}

		});


		// initialize scrollPane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 185, 880, 330);
		frame.getContentPane().add(scrollPane);
		// initialize JTable
		table = new JTable();
		scrollPane.setViewportView(table);
		// table action listener part
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				int index = table.getSelectedRow();
				System.out.println("The selected row for checked out table is: " + index);

				email = (String) table.getValueAt(index, 1);
				System.out.println("The customer email is: " + email);

				roomNoValue = (int) table.getValueAt(index, 6);
				System.out.println("The room number is " + roomNoValue);

			}
		});
		btnProvisional = new JButton("Provisional");
		btnProvisional.setBounds(30, 50, 110, 30);
		btnProvisional.setBackground(Color.ORANGE);
		btnProvisional.setFocusable(false);
		pnl2.add(btnProvisional);
		// action listener to open provisional bill
		btnProvisional.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Invoice2 invoi = new Invoice2();
				frame.dispose();
				//frame.setVisible(false);
				//showTable();
			}
		});
		
		btnclose = new JButton("EXIT");
		btnclose.setBounds(465, 50, 80, 30);
		btnclose.setBackground(Color.RED);
		btnclose.setFocusable(false);
		pnl2.add(btnclose);
		// action listener close the frame
		btnclose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Dashboard1();
				frame.setVisible(false);
			}
		});

		frame.setLayout(null); // set the layout of the frame to null
		frame.setVisible(true); // make the frame visible

	}

	public void showTable() {
		// try catch block
		try {
			// To create connection object
			Connection con = DatabaseConnectionUtil.ConnectDB();

			// Make prepared Statement
			String query1 = "select c.fullName, c.emailID, c.address, b.arrivalDate, b.departureDate, r.roomType, r.roomNo ,\r\n"
					+ " sum(coalesce(s.total, 0))+ r.roomPrice   AS TotalAmount, b.bookingStatus\r\n"
					+ "	from booking b\r\n" + "	inner join customer c\r\n" + "	on b.customerID=c.customerID\r\n"
					+ "	inner join room r\r\n" + "	on r.roomID = b.roomID\r\n" + "	left join service s\r\n"
					+ "	on s.bookingID = b.bookingID\r\n" + "	where b.departureDate='" + currentDate
					+ "' and b.bookingStatus='Checked In'\r\n" + " group by c.fullName";

			PreparedStatement pst1 = con.prepareStatement(query1);
			ResultSet rst1 = pst1.executeQuery();

			table.setModel(DbUtils.resultSetToTableModel(rst1)); // to show the JTable in scrollPane
			con.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex);
		}

	}

//main method
	public static void main(String[] args) {
		new CheckOutPage();
	}

}
