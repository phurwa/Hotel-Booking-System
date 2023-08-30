package gui;
//import all useful library
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
import java.text.SimpleDateFormat;
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
import javax.swing.ScrollPaneConstants;

import com.toedter.calendar.JDateChooser;

import net.proteanit.sql.DbUtils;

public class CheckInPage {
	//declare variables
	JFrame frame;
	JPanel pnl1, pnl2;
	JLabel lblRes, lblExit, lblMin, lblTitle, lblRoomNo,lblRoomType,lblCardNo,lblTcheck;
	JButton  btnCheckedIn, btnView, btnclose;
	JTable table;
	JComboBox cmbRoomNo;
	JTextField txtRoomType,txtCardNo,txtTcheck;
	String email;
	String chooseRoomNo1;
	String room;
	
	// To get instance date
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
	Date date = new Date();  
	String currentDate = dateFormat.format(date);
    
	// Get customer id from email
	int id = DatabaseConnectionUtil.getCustomerID(Global.current_email);
	
	//default constructor
	public CheckInPage() {
		frame = new JFrame();
		frame.setBounds(210, 70, 900, 540);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(99, 121, 133));
		
		pnl1 = new JPanel();
		pnl1.setBounds(0, 0, 900, 40);
		pnl1.setBackground(new Color(0, 128, 255));
		pnl1.setLayout(null);
		frame.add(pnl1);

		pnl2 = new JPanel();
		pnl2.setBounds(0, 40, 900, 200);
		pnl2.setBackground(Color.lightGray);
		pnl2.setLayout(null);
		frame.add(pnl2);

		lblRes = new JLabel("Check In Page");
		lblRes.setForeground(new Color(224, 222, 216));
		lblRes.setFont(new Font("Arial", Font.BOLD, 20));
		lblRes.setBounds(380, 5, 200, 25);
		pnl1.add(lblRes);

		lblExit = new JLabel("X");
		lblExit.setForeground(new Color(224, 222, 216));
		lblExit.setFont(new Font("Arial", Font.BOLD, 20));
		lblExit.setBounds(870, 8, 40, 25);
		pnl1.add(lblExit);
		//action event to close the frame
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
		//action event to minimize tht frame
		lblMin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setState(Frame.ICONIFIED);
			}
		});

		lblTitle = new JLabel("Allocate Room");
		lblTitle.setBounds(380, 5, 250, 20);
		lblTitle.setForeground(Color.BLACK);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 15));
		pnl2.add(lblTitle);
		
		lblRoomNo= new JLabel("Room No:");
		lblRoomNo.setBounds(60, 45, 110, 25);
		lblRoomNo.setForeground(Color.BLACK);
		lblRoomNo.setFont(new Font("Arial", Font.BOLD, 15));
		pnl2.add(lblRoomNo);
		
		cmbRoomNo = new JComboBox();
		cmbRoomNo.setBounds(175, 45, 150, 25);
		cmbRoomNo.setForeground(Color.CYAN);
		cmbRoomNo.setBackground(new Color(99, 121, 133));
		cmbRoomNo.setFont(new Font("Arial", Font.PLAIN, 16));
		pnl2.add(cmbRoomNo);
		
		// combo box listener
		cmbRoomNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//try catch block
				try {
					//connection establish 
					Connection con = DatabaseConnectionUtil.ConnectDB();
					
					if (cmbRoomNo.getSelectedIndex() != -1) {
						chooseRoomNo1 = cmbRoomNo.getSelectedItem().toString();
						
						//Query to get data from booking and customer table
						String roomType = "select b.bookingID, c.customerID, c.cardNo, b.bookingStatus\r\n"
								+ "from booking b inner join customer c\r\n"
								+ "on b.customerID = c.customerID\r\n"
								+ "where c.emailID='"+email+"' and  b.bookingStatus='Pending'";
						
						PreparedStatement pst2 = con.prepareStatement(roomType);
						ResultSet rst2 = pst2.executeQuery();
						
						int bid = 0;
						int cid = 0;
						String stat = "";
						
						while(rst2.next()) {
							bid = rst2.getInt(1);
							cid = rst2.getInt(2);
							stat = rst2.getString(3);
						}
						
					
						// query to get data from room table 
						String idQuery = "select roomID from room where roomNo='"+chooseRoomNo1+"' and roomStatus='Vacant'";
						PreparedStatement pst4 = con.prepareStatement(idQuery);
						ResultSet rst4 = pst4.executeQuery();
						
						int rid2 = 0;
						while(rst4.next()) {
							rid2 = rst4.getInt(1);
						}
						System.out.println("The selected room id for check in is " + rid2);
							
						
						//Query to update room id from room table
						String roomIDUpdate = "Update booking set roomID=? where bookingID='"+bid+"' and customerID='"+cid+"'";
						PreparedStatement pst5 = con.prepareStatement(roomIDUpdate);
						pst5.setInt(1, rid2);
						pst5.execute();	
						
						
					}
					}
				catch(Exception ex) {
					ex.printStackTrace();
				}		
			}			
		});
		
		lblRoomType = new JLabel("room Type:");
		lblRoomType.setBounds(60, 90, 110, 25);
		lblRoomType.setForeground(Color.BLACK);
		lblRoomType.setFont(new Font("Arial", Font.BOLD, 15));
		pnl2.add(lblRoomType);
		
		txtRoomType = new JTextField();
		txtRoomType.setBounds(175, 90, 150, 25);
		txtRoomType.setEnabled(false);
		txtRoomType.setForeground(Color.CYAN);
		txtRoomType.setBackground(new Color(99, 121, 133));
		txtRoomType.setFont(new Font("Arial", Font.PLAIN, 16));
		pnl2.add(txtRoomType);
		
		lblTcheck = new JLabel("Today CheckIn:");
		lblTcheck.setBounds(540, 45, 140, 25);
		lblTcheck.setForeground(Color.BLACK);
		lblTcheck.setFont(new Font("Arial", Font.BOLD, 15));
		pnl2.add(lblTcheck);
		
		txtTcheck = new JTextField();
		txtTcheck.setBounds(685, 45, 140, 25);
		txtTcheck.setForeground(Color.CYAN);
		txtTcheck.setBackground(new Color(99, 121, 133));
		txtTcheck.setFont(new Font("Arial", Font.PLAIN, 16));
		pnl2.add(txtTcheck);
		txtTcheck.setText(currentDate);
		txtTcheck.setEditable(false);
		
		lblCardNo = new JLabel("Card No:");
		lblCardNo.setBounds(540, 90, 140, 25);
		lblCardNo.setForeground(Color.BLACK);
		lblCardNo.setFont(new Font("Arial", Font.BOLD, 15));
		pnl2.add(lblCardNo);
		
		txtCardNo = new JTextField();
		txtCardNo.setBounds(685, 90, 140, 25);
		txtCardNo.setEnabled(false);
		txtCardNo.setForeground(Color.CYAN);
		txtCardNo.setBackground(new Color(99, 121, 133));
		txtCardNo.setFont(new Font("Arial", Font.PLAIN, 16));
		pnl2.add(txtCardNo);
		
		btnCheckedIn = new JButton("CheckIn");
		btnCheckedIn.setBounds(280, 140, 90, 30);
		btnCheckedIn.setBackground(Color.ORANGE);
		btnCheckedIn.setFocusable(false);
		pnl2.add(btnCheckedIn);
		//action event for check in button	
		btnCheckedIn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String getCardNumber = txtCardNo.getText();
					//try catch block
					try {
						
						// establish connection
						Connection con = DatabaseConnectionUtil.ConnectDB();
						
						System.out.println("The email is " + email); //print the email in console
						
						
						// Query to retrieve data from customer table 
						String queryID = "select customerID, cardNo, userType from customer where emailID='"+email+"'";
						PreparedStatement ps = con.prepareStatement(queryID);
						ResultSet rs = ps.executeQuery();
						
						int cid = 0;
						String card = "";
						String type = "";
						
						while(rs.next()) {
							cid = rs.getInt(1);
							card = rs.getString(2);
							type = rs.getString(3);
						}
						
						
						
						if(card.equals("") && type.equals("Normal Customer")) {
							JOptionPane.showMessageDialog(null, "Booking confirm not successfull!!!");
							
							// Query to insert card number in customer detail
							String queryCard = "update customer set cardNo=? where emailID='"+email+"'";
							PreparedStatement pst5 = con.prepareStatement(queryCard);
							pst5.setString(1, getCardNumber);
							pst5.executeUpdate();
							
						}
						else {
							// Query to create update booking status
							String query = "Update booking set bookingStatus=? where arrivalDate='"+currentDate+"' and bookingStatus='Pending' and customerID='"+cid+"'";
							PreparedStatement pst1 = con.prepareStatement(query);
							pst1.setString(1, "Checked In");
							pst1.execute();
							
							
							// Query to select booking id
							String bookingID = "select bookingID, roomID from booking where customerID='"+cid+"' and bookingStatus='Checked In'";
							PreparedStatement pst2 = con.prepareStatement(bookingID);
							ResultSet rst2 = pst2.executeQuery();
							
							int bid = 0;
							int rid = 0;
							
							while(rst2.next()) {
								bid = rst2.getInt(1);
								rid = rst2.getInt(2);
							}
							
							
							//Query to update room status  in room table
							String roomStatus = "update room set roomStatus='Not Available' where roomID='"+rid+"' and roomStatus='Vacant'";
							PreparedStatement pst3 = con.prepareStatement(roomStatus);
							pst3.execute();
							
							JOptionPane.showMessageDialog(null, "Customer Checked In");
							cmbRoomNo.removeAllItems();
							startShowTable();
						}
						
					}
					catch(Exception ex) {
						ex.printStackTrace();
					}
				
			}
			
		});
		
		btnView = new JButton("View");
		btnView.setBounds(390, 140, 80, 30);
		btnView.setBackground(Color.orange);
		btnView.setFocusable(false);
		pnl2.add(btnView);
		//action event for view button
		btnView.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//open the checkIn list page 
				CheckInList checkin = new CheckInList();
				checkin.startShowTable();
				frame.dispose();

			}

		});
		
		btnclose = new JButton("Close");
		btnclose.setBounds(490,140,80,30);
		btnclose.setBackground(Color.RED);
		btnclose.setFocusable(false);
		pnl2.add(btnclose);
		//action event to close the frame
		btnclose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//opens the staff dashboard1 page
				new Dashboard1();
				frame.dispose(); //close the current page
				
			}
			
		});
	
		//initialize JScrollPane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 250, 880, 280);
		frame.getContentPane().add(scrollPane);
		//initialize JTable
		table = new JTable();
		scrollPane.setViewportView(table); //add table in scrollPane
		// action listener for table
		table.addMouseListener(new MouseAdapter() {

	       	@Override
				public void mouseClicked(MouseEvent e) {
	        	
	        	int index = table.getSelectedRow();
	      
				// try-catch block             
	            try {
	            	
	            	// Get email from the table
				    email = table.getModel().getValueAt(index, 1).toString();
				    
				    room = table.getModel().getValueAt(index, 5).toString();
				    
	            	txtRoomType.setText(room);
	            	
	            	
	            	// To create Connection object
					Connection con = DatabaseConnectionUtil.ConnectDB();
					
				    System.out.println("The selected room type is: " + room);
				    
					    
					// Query to find room type 
					String roomType = "select b.bookingID, c.customerID, c.cardNo, b.bookingStatus\r\n"
							+ "from booking b inner join customer c\r\n"
							+ "on b.customerID = c.customerID\r\n"
							+ "where c.emailID='"+email+"' and  b.bookingStatus='Pending'";
					
					PreparedStatement pst2 = con.prepareStatement(roomType);
					ResultSet rst2 = pst2.executeQuery();
					
					int bid = 0;
					int cid = 0;
					String card = "";
					String stat = "";
					
					while(rst2.next()) {
						bid = rst2.getInt(1);
						cid = rst2.getInt(2);
						card = rst2.getString(3);
						stat = rst2.getString(4);
					}
					
					// Card detail display in text
					txtCardNo.setText(card);
					
					// Query to find room number
					String roomNos = "select roomNo from room where roomType='"+room+"' and roomStatus='Vacant'";
					PreparedStatement pst3 = con.prepareStatement(roomNos);
					ResultSet rst3 = pst3.executeQuery();
					
					
					while(rst3.next()) {
						cmbRoomNo.addItem(rst3.getString(1));
					}	
	        				
	            }catch(Exception ex) {
	        			JOptionPane.showMessageDialog(null, ex);
	            }
	        	
	        	}			
	        });

		
		
		frame.setLayout(null);  //set the layout of frame to null
		frame.setVisible(true);	//display the frame
	}
	
	// Method to show the table
	public void startShowTable() {
		//try catch block
		try {
			
			//establish connection
			Connection conn = DatabaseConnectionUtil.ConnectDB();
			
			// Make prepared Statement
			String query = "select c.fullname, c.emailID, c.address, b.arrivalDate, departureDate, b.roomType, r.roomNo, bookingStatus\r\n"
					+ "from booking b inner join customer c\r\n"
					+ "on b.customerID = c.customerID\r\n"
					+ "left join room r\r\n"
					+ "on b.roomID = r.roomID\r\n"
					+ "where b.arrivalDate='"+currentDate+"' and bookingStatus='Pending'";
			
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			pst.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

//main method to run the code
	public static void main(String[] args) {
		new CheckInPage();
	}

}
