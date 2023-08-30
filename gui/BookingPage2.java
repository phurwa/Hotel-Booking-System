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
import com.toedter.calendar.JDateChooser;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.util.ArrayList;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

public class BookingPage2 {

	//initialization
	JFrame frame;
	JPanel pnl1, pnl2;
	JLabel lblTitle, lblExit, lblMin, lblDeparture, lblArrival, lblRoomType, lblRoomPrice;
	JTextField txtRoomPrice;
	JDateChooser arrivalDate, departureDate;
	JTable table;
	JScrollPane scrollPane;
	JButton btnSave, btnUpdate, btnPay, btnClose, btnDelete;
	JComboBox cmbRoomType;

	// Values for comboBox 
	String room[] = { "Single", "Double", "Twin" };

	// Create connection object
	Connection conn = DatabaseConnectionUtil.ConnectDB();

	// Get customer id from email
	int id = DatabaseConnectionUtil.getCustomerID(Global.current_email);

	// default constructor 
	public BookingPage2() {
		frame = new JFrame();	//initialize and memory allocate
		frame.setBounds(255, 140, 750, 400);
		frame.setUndecorated(true);			//set the frame undecorated
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.WHITE);		//change the frame background color to white

		pnl1 = new JPanel();		//initialize and memory allocate
		pnl1.setBounds(0, 0, 750, 40);
		pnl1.setBackground(new Color(0, 128, 255));
		pnl1.setLayout(null);
		frame.add(pnl1);

		pnl2 = new JPanel();		//initialize and memory allocate
		pnl2.setBounds(0, 40, 750, 180);
		pnl2.setBackground(Color.lightGray);
		pnl2.setLayout(null);
		frame.add(pnl2);

		lblTitle = new JLabel("Booking Page");		//initialize, memory allocate and set the text
		lblTitle.setForeground(new Color(224, 222, 216));
		lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
		lblTitle.setBounds(325, 5, 220, 25);
		pnl1.add(lblTitle);

		lblExit = new JLabel("X");		//initialize, memory allocate and set the text
		lblExit.setForeground(new Color(224, 222, 216));
		lblExit.setFont(new Font("Arial", Font.BOLD, 20));
		lblExit.setBounds(725, 8, 40, 25);
		pnl1.add(lblExit);
		//mouse listener to close the JFrame
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});

		lblMin = new JLabel("-");			//initialize, memory allocate and set the text
		lblMin.setForeground(new Color(224, 222, 216));
		lblMin.setFont(new Font("Arial", Font.BOLD, 40));
		lblMin.setBounds(690, 0, 40, 35);
		pnl1.add(lblMin);
		//mouse listener to minimize the JFrame
		lblMin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setState(Frame.ICONIFIED);
			}
		});

		lblArrival = new JLabel("Arrival Date:");		//initialize, memory allocate and set the text
		lblArrival.setBounds(40, 20, 120, 30);
		lblArrival.setForeground(Color.BLACK);
		lblArrival.setFont(new Font("Arial", Font.PLAIN, 15));
		pnl2.add(lblArrival);

		arrivalDate = new JDateChooser();		//initialize and memory allocate 
		arrivalDate.setBounds(140, 20, 180, 30);
		arrivalDate.setFont(new Font("Arial", Font.PLAIN, 15));
		pnl2.add(arrivalDate);

		lblDeparture = new JLabel("Departure Date:");	//initialize, memory allocate and set the text
		lblDeparture.setBounds(380, 20, 120, 30);
		lblDeparture.setForeground(Color.BLACK);
		lblDeparture.setFont(new Font("Arial", Font.PLAIN, 15));
		pnl2.add(lblDeparture);

		departureDate = new JDateChooser();		//initialize and memory allocate
		departureDate.setBounds(505, 20, 186, 30);
		departureDate.setFont(new Font("Arial", Font.PLAIN, 15));
		pnl2.add(departureDate);

		lblRoomType = new JLabel("Room Type:");		//initialize, memory allocate and set the text
		lblRoomType.setBounds(40, 80, 120, 30);
		lblRoomType.setForeground(Color.BLACK);
		lblRoomType.setFont(new Font("Arial", Font.PLAIN, 15));
		pnl2.add(lblRoomType);

		lblRoomPrice = new JLabel("Room Price:");		//initialize, memory allocate and set the text
		lblRoomPrice.setBounds(380, 80, 120, 30);
		lblRoomPrice.setForeground(Color.BLACK);
		lblRoomPrice.setFont(new Font("Arial", Font.PLAIN, 15));
		pnl2.add(lblRoomPrice);

		txtRoomPrice = new JTextField();		//initialize, memory allocate and set the text
		txtRoomPrice.setBounds(505, 80, 186, 30);
		txtRoomPrice.setForeground(Color.BLACK);
		txtRoomPrice.setBackground(new Color(255, 255, 255));
		txtRoomPrice.setFont(new Font("Arial", Font.PLAIN, 16));
		txtRoomPrice.setEditable(false);	//unable to edit the textField
		pnl2.add(txtRoomPrice);

		btnUpdate = new JButton("Update");		//initialize, memory allocate and set the text
		btnUpdate.setBounds(250, 140, 80, 30);
		btnUpdate.setBackground(Color.ORANGE);
		btnUpdate.setFocusable(false);		
		pnl2.add(btnUpdate);

		// Action listener event for update button
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//try catch block
				try {

					// Store the value of room type choose by the customer
					String roomType = (String) cmbRoomType.getSelectedItem();
					
					//print the selected room type
					System.out.println("The selected room type is: " + roomType);

					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					String arrival = format.format(arrivalDate.getDate());
					String departure = format.format(departureDate.getDate());

					// Query to update booking table
					String query1 = "update booking set roomType=?, arrivalDate=?, departureDate=? where customerID='"
							+ id + "' and bookingStatus='Pending'";
					PreparedStatement pst1 = conn.prepareStatement(query1);
					pst1.setString(1, roomType);
					pst1.setString(2, arrival);
					pst1.setString(3, departure);

					pst1.execute();

					table_load();	//display data in table 
					JOptionPane.showMessageDialog(null, "Update Successfull");		//show message

				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}

		});

		btnDelete = new JButton("Delete");
		btnDelete.setBounds(350, 140, 80, 30);
		btnDelete.setBackground(Color.orange);
		btnDelete.setFocusable(false);
		pnl2.add(btnDelete);
		// Action listener event for delete button
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// Try catch block
				try {

					// To retrieve Booking Status from the booking table
					String query2 = "select bookingStatus from booking where customerID='" + id + "'";
					PreparedStatement pst2 = conn.prepareStatement(query2);
					ResultSet rst2 = pst2.executeQuery();

					String bStatus = "";
					while (rst2.next()) {
						bStatus = rst2.getString("bookingStatus");
					}

					System.out.println("The booking status is " + bStatus);

					if (bStatus.equals("Pending")) {
						// create connection object
						conn = DatabaseConnectionUtil.ConnectDB();

						// mysql insert statement
						String query1 = "delete from Booking where customerID=?";

						// create the mysql insert prepared statement
						PreparedStatement pstat = conn.prepareStatement(query1);
						pstat.setInt(1, id);
						pstat.execute();

						table_load();
						JOptionPane.showMessageDialog(null, "Deleted Successfully");

					} else {
						btnDelete.setEnabled(false);
					}

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnPay = new JButton("Pay");
		btnPay.setBounds(450, 140, 80, 30);
		btnPay.setBackground(Color.ORANGE);
		btnPay.setFocusable(false);
		pnl2.add(btnPay);
		// Action listener event for Pay button
		btnPay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// try-catch block
				try {

					// Query to get customer type
					String query1 = "select userType from customer where customerID='" + id + "'";
					PreparedStatement pst1 = conn.prepareStatement(query1);
					ResultSet rst1 = pst1.executeQuery();

					String type = "";
					while (rst1.next()) {
						type = rst1.getString(1);
					}

					System.out.println("The customer type is: " + type);

					if (type.equals("Normal Customer")) {
						JOptionPane.showMessageDialog(null, "Action denied");
					} else {
						MonthlyPay pay = new MonthlyPay();
						// frame.setVisible(false);
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Invalid");
				}

			}

		});

		btnClose = new JButton("Close");
		btnClose.setBounds(550, 140, 80, 30);
		btnClose.setBackground(Color.RED);
		btnClose.setFocusable(false);
		pnl2.add(btnClose);
		
		// Action listener event for Close button
		btnClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					// Close current frame
					new LoginPage1(); //open login Page
					frame.setVisible(false);
					conn.close();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}

			}

		});

		// initialize scrollPane
		scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});

		scrollPane.setBounds(10, 235, 728, 150);
		frame.getContentPane().add(scrollPane);
		table = new JTable();
		table.setEnabled(true);

		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				int index = table.getSelectedRow();
				System.out.println("The index is: " + index);

				try {
					Date format = new SimpleDateFormat("yyyy-MM-dd")
							.parse((String) table.getModel().getValueAt(index, 2));
					arrivalDate.setDate(format);

					Date format1 = new SimpleDateFormat("yyyy-MM-dd")
							.parse((String) table.getModel().getValueAt(index, 3));
					departureDate.setDate(format1);

					String rooms = table.getModel().getValueAt(index, 4).toString();
					switch (rooms) {
					case "Single":
						cmbRoomType.setSelectedIndex(0);
						break;

					case "Double":
						cmbRoomType.setSelectedIndex(1);
						break;

					case "Twin":
						cmbRoomType.setSelectedIndex(2);
						break;
					}

				} catch (ParseException e1) {

					e1.printStackTrace();
				}
			}

		});
		scrollPane.setViewportView(table);		//add table in scrollPane

		cmbRoomType = new JComboBox(room);
		cmbRoomType.setBounds(140, 80, 180, 30);
		cmbRoomType.setForeground(Color.black);
		cmbRoomType.setBackground(new Color(255, 255, 255));
		cmbRoomType.setFont(new Font("Arial", Font.PLAIN, 16));
		pnl2.add(cmbRoomType);

		// JDateChooser convert to string
		String arrivalDateTxt = ((JTextField) arrivalDate.getDateEditor().getUiComponent()).getText();
		String departureDateTxt = ((JTextField) departureDate.getDateEditor().getUiComponent()).getText();

		// comboBox action performed
		cmbRoomType.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String roomType = (String) cmbRoomType.getSelectedItem();
				System.out.println(roomType);
				//try catch block
				try {
					//establish connection
					Connection con = DatabaseConnectionUtil.ConnectDB();
					//query to retrieve data from room table
					String query = "select roomPrice from room where roomStatus='Vacant' and roomType='" + roomType + "'";
					PreparedStatement pstat = con.prepareStatement(query);
					ResultSet res = pstat.executeQuery();

					while (res.next()) {
						String roomPrice = res.getString(1);
						System.out.println("The room Price is:" + roomPrice); //print roomPrice in console
						txtRoomPrice.setText(res.getString(1));
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, e);
				}

			}

		});

		btnSave = new JButton("Save");
		btnSave.setBounds(150, 140, 80, 30);
		btnSave.setBackground(Color.ORANGE);
		btnSave.setFocusable(false);
		pnl2.add(btnSave);
		// Save button Action listener
		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// try-catch block
				try {

					table_load();

					// To store room type values
					String roomType = (String) cmbRoomType.getSelectedItem();

					// query to select roomtype and id from the room info table
					String roomQuery = "select roomID from room where roomStatus='Vacant' and roomType='" + roomType
							+ "'";
					PreparedStatement pst2 = conn.prepareStatement(roomQuery);
					ResultSet rst2 = pst2.executeQuery();

					int roomId = 0;
					while (rst2.next()) {
						roomId = rst2.getInt(1);
					}
					System.out.println("The Room id is: " + roomId);

					// query to insert data into the booking table
					String query = "insert into Booking(customerID, arrivalDate, departureDate, roomType) values(?,?,?,?)";

					// create a prepared statement
					PreparedStatement ps1 = conn.prepareStatement(query);

					// Set the values in database
					ps1.setInt(1, id);

					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					String arrival = format.format(arrivalDate.getDate());
					String departure = format.format(departureDate.getDate());

					System.out.println("The arrival date is: " + arrival);
					System.out.println("The departure date is: " + departure);

					ps1.setString(2, arrival);
					ps1.setString(3, departure);
					ps1.setString(4, roomType);

					if (ps1.executeUpdate() > 0) {
						table_load();
						JOptionPane.showMessageDialog(null, "Booking Successfull");

					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		
		frame.setLayout(null);		//set the layout to null
		frame.setVisible(true);		//make the frame visible

	}

	public void table_load() {
		try {
			//establish connection
			Connection con = DatabaseConnectionUtil.ConnectDB();
			
			//query to retrieve data from booking table
			String stat = "Select bookingStatus from booking where customerID='" + id + "'";
			PreparedStatement pst1 = con.prepareStatement(stat);
			ResultSet rst1 = pst1.executeQuery();

			String bookingStatus = "";
			while (rst1.next()) {
				rst1.getString("bookingStatus");
			}
			//query to display data in table
			String query = "select c.fullName, c.address, b.arrivalDate, b.departureDate, b.roomType,roomNo,bookingStatus\r\n"
					+ "from booking b inner join customer c\r\n" + "on b.customerID = c.customerID\r\n"
					+ "left join room r\r\n" + "on b.roomID = r.roomID\r\n" + "where c.customerID='" + id
					+ "' and b.bookingStatus='Pending'";
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rst = pst.executeQuery();

			table.setModel(DbUtils.resultSetToTableModel(rst));	//show table in scrollPane

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// To run the application
	public static void main(String[] args) {
		new BookingPage2();
	}

}
