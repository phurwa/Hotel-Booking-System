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
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

public class CheckOutList {
	//declare variables
	JFrame frame;
	JPanel pnl1, pnl2;
	JLabel lblCheckInList, lblCheckIn, lblMin, lblTitle, lblExit;
	JTextField txtCheckIn;
	JComboBox cmbRoomNo, cmbServiceType;
	JButton btnSave, btnClose;
	private JTable table;

	// To get instance date
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date();
	String currentDate = dateFormat.format(date);
	//create default constructor
	public CheckOutList() {
		//initialize and memory allocate
		frame = new JFrame();
		frame.setBounds(320, 100, 750, 430);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.WHITE);

		pnl1 = new JPanel();
		pnl1.setBounds(0, 0, 750, 40);
		pnl1.setBackground(new Color(0, 128, 255));
		pnl1.setLayout(null);
		frame.add(pnl1);

		pnl2 = new JPanel();
		pnl2.setBounds(0, 40, 750, 80);
		pnl2.setBackground(Color.lightGray);
		pnl2.setLayout(null);
		frame.add(pnl2);

		lblCheckInList = new JLabel("Check Out Guest List");
		lblCheckInList.setForeground(new Color(224, 222, 216));
		lblCheckInList.setFont(new Font("Arial", Font.BOLD, 20));
		lblCheckInList.setBounds(280, 5, 230, 25);
		pnl1.add(lblCheckInList);

		lblExit = new JLabel("X");
		lblExit.setForeground(new Color(224, 222, 216));
		lblExit.setFont(new Font("Arial", Font.BOLD, 20));
		lblExit.setBounds(720, 8, 40, 25);
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
		lblMin.setBounds(690, 0, 40, 35);
		pnl1.add(lblMin);
		lblMin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setState(Frame.ICONIFIED);
			}
		});

		lblCheckIn = new JLabel("CheckOut Date:");
		lblCheckIn.setBounds(400, 30, 140, 25);
		lblCheckIn.setForeground(Color.BLACK);
		lblCheckIn.setFont(new Font("Arial", Font.BOLD, 15));
		pnl2.add(lblCheckIn);

		txtCheckIn = new JTextField();
		txtCheckIn.setBounds(535, 30, 150, 25);
		txtCheckIn.setForeground(Color.CYAN);
		txtCheckIn.setBackground(new Color(99, 121, 133));
		txtCheckIn.setFont(new Font("Arial", Font.PLAIN, 16));
		pnl2.add(txtCheckIn);
		txtCheckIn.setText(currentDate);
		txtCheckIn.setEditable(false);
		
		btnClose = new JButton("Close");
		btnClose.setBounds(20,30,75,25);
		btnClose.setBackground(Color.RED);
		btnClose.setFocusable(false);
		pnl2.add(btnClose);
		//action event to close the frame
		btnClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//opens the checkOut page
				new CheckOutPage();
				frame.dispose(); //close the current page
				
			}
			
		});
		//initialize scrollPane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 130, 730, 300);
		frame.getContentPane().add(scrollPane);
		//initialize JTable
		table = new JTable();
		scrollPane.setViewportView(table);	//add JTable in scrollPane

		frame.setLayout(null);	//set the layout of frame to null	
		frame.setVisible(true);	//make the frame visible

	}

	public void showTable() {
		try {
			//establish connection
			Connection conn = DatabaseConnectionUtil.ConnectDB();
			//query to display data in table
			String query1 = "select c.fullname, c.emailID, c.address, b.arrivalDate, b.departureDate, r.roomType, r.roomNo ,\r\n"
					+ " sum(coalesce(s.Total, 0))+ r.roomPrice   AS TotalAmount, b.bookingStatus\r\n"
					+ "					from booking b\r\n"
					+ "					inner join customer c\r\n"
					+ "					on b.customerID=c.customerID\r\n"
					+ "					inner join room r\r\n"
					+ "					on r.roomID = b.roomID\r\n"
					+ "					left join service s\r\n"
					+ "					on s.bookingID = b.bookingID\r\n"
					+ "					where b.departureDate='"+currentDate+"' and b.bookingStatus='Checked-Out'"
					+ " group by c.emailID";
			
			PreparedStatement pst1 = conn.prepareStatement(query1);
			ResultSet rst1 = pst1.executeQuery();
			
			
			
			table.setModel(DbUtils.resultSetToTableModel(rst1));
			
			conn.close();
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex);
		}
	
	}
//main method to run the code
	public static void main(String[] args) {
		new CheckOutList();

	}

}
