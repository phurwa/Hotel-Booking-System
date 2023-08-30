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
import java.sql.SQLException;

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

import net.proteanit.sql.DbUtils;

public class OtherService {
	//declare variable
	JFrame frame;
	JPanel pnl1, pnl2;
	JLabel lblRes, lblExit, lblMin, lblTitle, lblRoomNo, lblServiceType, lblQnt, lblTotal, lblGuest, lblRate;
	JTextField txtGuest, txtQnt, txtRate, txtTotal;
	JComboBox cmbRoomNo, cmbServiceType;
	JButton btnSave, btnExit;
	private JTable table;
	String items;
	//create default constructor
	public OtherService() {
		//initialize and memory allocate
		frame = new JFrame();
		frame.setBounds(320, 50, 680, 600);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(99, 121, 133));

		pnl1 = new JPanel();
		pnl1.setBounds(0, 0, 680, 40);
		pnl1.setBackground(new Color(0, 128, 255));
		pnl1.setLayout(null);
		frame.add(pnl1);

		pnl2 = new JPanel();
		pnl2.setBounds(0, 40, 680, 250);
		pnl2.setBackground(Color.lightGray);
		pnl2.setLayout(null);
		frame.add(pnl2);

		lblRes = new JLabel("External Services");
		lblRes.setForeground(new Color(224, 222, 216));
		lblRes.setFont(new Font("Arial", Font.BOLD, 20));
		lblRes.setBounds(5, 5, 200, 25);
		pnl1.add(lblRes);

		lblExit = new JLabel("X");
		lblExit.setForeground(new Color(224, 222, 216));
		lblExit.setFont(new Font("Arial", Font.BOLD, 20));
		lblExit.setBounds(650, 8, 40, 25);
		pnl1.add(lblExit);
		// action event to close the frame
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});

		lblMin = new JLabel("-");
		lblMin.setForeground(new Color(224, 222, 216));
		lblMin.setFont(new Font("Arial", Font.BOLD, 40));
		lblMin.setBounds(610, 0, 40, 35);
		pnl1.add(lblMin);
		// action event to minimize the frame
		lblMin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setState(Frame.ICONIFIED);
			}
		});

		lblTitle = new JLabel("Service to Guest");
		lblTitle.setBounds(240, 5, 250, 30);
		lblTitle.setForeground(Color.BLACK);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
		pnl2.add(lblTitle);

		lblRoomNo = new JLabel("RoomNo");
		lblRoomNo.setBounds(30, 45, 110, 25);
		lblRoomNo.setForeground(Color.BLACK);
		lblRoomNo.setFont(new Font("Arial", Font.BOLD, 15));
		pnl2.add(lblRoomNo);

		cmbRoomNo = new JComboBox();
		cmbRoomNo.setBounds(140, 45, 160, 25);
		cmbRoomNo.setForeground(Color.CYAN);
		cmbRoomNo.setBackground(new Color(99, 121, 133));
		cmbRoomNo.setFont(new Font("Arial", Font.PLAIN, 16));
		pnl2.add(cmbRoomNo);

		// Try catch block 
		try {
			//establish connection
			Connection con = DatabaseConnectionUtil.ConnectDB();

			// Query to display all room number whose status are checked in
			String query1 = "select r.roomNo from room r\r\n" + "inner join booking b\r\n" + "on r.roomID=b.roomID\r\n"
					+ "where r.roomStatus='Not Available' and b.bookingStatus='Checked In'";

			PreparedStatement pst1 = con.prepareStatement(query1);
			ResultSet rst1 = pst1.executeQuery();

			String roomNo = "";
			while (rst1.next()) {
				cmbRoomNo.addItem(rst1.getString("RoomNo"));
			}

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex);
		}

		// Action listener event for room number combo box
		cmbRoomNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String roomNumber = (String) cmbRoomNo.getSelectedItem();

				// try catch block
				try {
					//establish connection
					Connection con = DatabaseConnectionUtil.ConnectDB();

					// Query to select RoomType from Room Info table
					String query1 = "select roomID from room where roomNo='" + roomNumber
							+ "' and roomStatus='Not Available'";
					PreparedStatement pst1 = con.prepareStatement(query1);
					ResultSet rst1 = pst1.executeQuery();

					int rid = 0;
					while (rst1.next()) {
						rid = rst1.getInt(1);
						System.out.println(rid);
					}

					// Query to select Customer name
					String query2 = "select c.fullname \r\n" + "from customer c \r\n" + "inner join booking b \r\n"
							+ "on c.customerID = b.customerID \r\n" + "where b.roomID='" + rid
							+ "' and b.bookingStatus='Checked In'";

					PreparedStatement ps2 = con.prepareStatement(query2);
					ResultSet res2 = ps2.executeQuery();

					while (res2.next()) {
						String data = res2.getString(1);
						System.out.println("The room number is:" + data);
						txtGuest.setText(res2.getString("Fullname"));
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}

		});

		lblServiceType = new JLabel("ServiceType");
		lblServiceType.setBounds(30, 80, 110, 25);
		lblServiceType.setForeground(Color.BLACK);
		lblServiceType.setFont(new Font("Arial", Font.BOLD, 15));
		pnl2.add(lblServiceType);

		cmbServiceType = new JComboBox();
		cmbServiceType.setBounds(140, 80, 160, 25);
		cmbServiceType.setModel(new DefaultComboBoxModel(new String[] { "Telephone Service", "Laundary Service" }));
		cmbServiceType.setForeground(Color.CYAN);
		cmbServiceType.setBackground(new Color(99, 121, 133));
		cmbServiceType.setFont(new Font("Arial", Font.PLAIN, 16));
		pnl2.add(cmbServiceType);
		//action listener for comboBox
		cmbServiceType.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				items = (String) cmbServiceType.getSelectedItem();
				System.out.println("The selected item is: " + items);

			}

		});

		lblGuest = new JLabel("GuestName");
		lblGuest.setBounds(350, 45, 110, 25);
		lblGuest.setForeground(Color.BLACK);
		lblGuest.setFont(new Font("Arial", Font.BOLD, 15));
		pnl2.add(lblGuest);

		txtGuest = new JTextField();
		txtGuest.setBounds(470, 45, 160, 25);
		txtGuest.setEnabled(false);
		txtGuest.setForeground(Color.CYAN);
		txtGuest.setBackground(new Color(99, 121, 133));
		txtGuest.setFont(new Font("Arial", Font.PLAIN, 16));
		pnl2.add(txtGuest);

		lblQnt = new JLabel("Quantity");
		lblQnt.setBounds(350, 80, 110, 25);
		lblQnt.setForeground(Color.BLACK);
		lblQnt.setFont(new Font("Arial", Font.BOLD, 15));
		pnl2.add(lblQnt);

		txtQnt = new JTextField();
		txtQnt.setBounds(470, 80, 160, 25);
		txtQnt.setForeground(Color.CYAN);
		txtQnt.setBackground(new Color(99, 121, 133));
		txtQnt.setFont(new Font("Arial", Font.PLAIN, 16));
		pnl2.add(txtQnt);

		lblRate = new JLabel("Rate");
		lblRate.setBounds(350, 115, 110, 25);
		lblRate.setForeground(Color.BLACK);
		lblRate.setFont(new Font("Arial", Font.BOLD, 15));
		pnl2.add(lblRate);

		txtRate = new JTextField();
		txtRate.setBounds(470, 115, 160, 25);
		txtRate.setForeground(Color.CYAN);
		txtRate.setBackground(new Color(99, 121, 133));
		txtRate.setFont(new Font("Arial", Font.PLAIN, 16));
		pnl2.add(txtRate);

		lblTotal = new JLabel("Total");
		lblTotal.setBounds(30, 115, 110, 25);
		lblTotal.setForeground(Color.BLACK);
		lblTotal.setFont(new Font("Arial", Font.BOLD, 15));
		pnl2.add(lblTotal);

		txtTotal = new JTextField();
		txtTotal.setBounds(140, 115, 160, 25);
		txtTotal.setEnabled(false);
		txtTotal.setForeground(Color.CYAN);
		txtTotal.setBackground(new Color(99, 121, 133));
		txtTotal.setFont(new Font("Arial", Font.PLAIN, 16));
		pnl2.add(txtTotal);

		btnSave = new JButton("Save");
		btnSave.setBounds(250, 185, 80, 30);
		btnSave.setBackground(Color.orange);
		btnSave.setFocusable(false);
		pnl2.add(btnSave);

		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// Calculation part
				//convert to text
				String rate = txtRate.getText();
				String qnt = txtQnt.getText();
				String roomNo = (String) cmbRoomNo.getSelectedItem();
				String service = (String) cmbServiceType.getSelectedItem();

				System.out.println(roomNo);

				double rate1 = Double.parseDouble(rate);
				double qnt1 = Double.parseDouble(qnt);

				// to calculate total amount
				double rateQty = (rate1 * qnt1);



				// Total sum including vat
				double total = 0;
				total = (rateQty);

				String total1 = Double.toString(total);
				
				txtTotal.setText(total1);

				try {

					Connection con = DatabaseConnectionUtil.ConnectDB();

					// Query to retrieve room id
					String query1 = "select roomID from room where roomNo='" + roomNo
							+ "' and roomStatus='Not Available'";
					PreparedStatement pst1 = con.prepareStatement(query1);
					ResultSet rst1 = pst1.executeQuery();

					int rid = 0;
					while (rst1.next()) {
						rid = rst1.getInt(1);
						System.out.println("The room id is" + rid);
					}

					// Query to retrieve booking id
					String query2 = "select bookingID from booking where roomID='" + rid
							+ "' and bookingStatus='Checked In'";
					PreparedStatement pst2 = con.prepareStatement(query2);
					ResultSet rst2 = pst2.executeQuery();

					int bid = 0;
					while (rst2.next()) {
						bid = rst2.getInt(1);
						System.out.println("The booking id is " + bid);
					}

					// Query to insert data into other_service table;
					String query = "insert service(bookingID, serviceType, quantity, rate, total) values(?,?,?,?,?)";
					PreparedStatement pst = con.prepareStatement(query);

					pst.setInt(1, bid);
					pst.setString(2, service);
					pst.setString(3, qnt);
					pst.setString(4, rate);
					pst.setString(5, total1);

					pst.execute();

					startShowTable();
					JOptionPane.showMessageDialog(null, "Service added successfully");

					String def = "";

					
					txtTotal.setText(def);
					txtRate.setText(def);
					txtQnt.setText(def);
					cmbRoomNo.setSelectedItem(-1);

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}

			}

		});

		btnExit = new JButton("EXIT");
		btnExit.setBounds(350, 185, 80, 30);
		btnExit.setBackground(Color.RED);
		btnExit.setFocusable(false);
		pnl2.add(btnExit);

		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new StaffDasboard();
				frame.setVisible(false);

			}

		});
		//initialize JScrollPane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 300, 655, 300);
		frame.getContentPane().add(scrollPane);
		//initialize JTable
		table = new JTable();
		scrollPane.setViewportView(table);	//add table in scrollPane

		frame.setLayout(null);	//set the layout of frame to null
		frame.setVisible(true); //make frame visible

	}

	// Method to show data in jTable
	public void startShowTable() {
		//try catch block
		try {

			cmbRoomNo.setSelectedItem(-1);

			// To create connection object
			Connection con = DatabaseConnectionUtil.ConnectDB();
			String roomNo = (String) cmbRoomNo.getSelectedItem();

			// Query to retrieve room id
			String query1 = "select roomID from room where roomNo='" + roomNo + "' and roomStatus='Not Available'";
			PreparedStatement pst1 = con.prepareStatement(query1);
			ResultSet rst1 = pst1.executeQuery();

			int rid = 0;
			while (rst1.next()) {
				rid = rst1.getInt(1);
				System.out.println("The room id is" + rid);
			}

			// Query to retrieve booking id and customer id
			String query2 = "select bookingID, customerID from booking where roomID='" + rid
					+ "' and bookingStatus='Checked In'";
			PreparedStatement pst2 = con.prepareStatement(query2);
			ResultSet rst2 = pst2.executeQuery();

			int bid = 0;
			int cid = 0;

			while (rst2.next()) {
				bid = rst2.getInt(1);
				cid = rst2.getInt(2);
				System.out.println("The booking id is " + bid + " customer id is " + cid);
			}

			// Query to get customer email
			String query3 = "select emailID from Customer where customerID='" + cid + "'";
			PreparedStatement pst3 = con.prepareStatement(query3);
			ResultSet rst3 = pst3.executeQuery();

			String email = "";

			while (rst3.next()) {
				email = rst3.getString(1);
			}

			// Make prepared Statement
			String query = "select c.fullname, r.roomNo, s.serviceType, s.quantity, s.rate, s.total \r\n"
					+ "from booking b\r\n" + "left join customer c\r\n" + "on b.customerID = c.customerID\r\n"
					+ "left join room r\r\n" + "on b.roomID = r.roomID\r\n" + "left join service s\r\n"
					+ "on b.bookingID = s.bookingID\r\n"
					+ " where s.serviceType='Laundary Service' or s.serviceType='Telephone Service'";

			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			table.setModel(DbUtils.resultSetToTableModel(rs));

			pst.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//main method
	public static void main(String[] args) {
		OtherService window = new OtherService();
		window.startShowTable();
	}

}
