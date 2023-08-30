package gui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;


import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.print.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.border.TitledBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Invoice2   {
	//declare variables
		JPanel contentPane;
		final JPanel panel = new JPanel();
		JTextField txtName,txtAddress,txtArrivalDate,txtDepartureDate,txtInvoice,txtBilldate,dxtDate,txtDesc,txtRate,txtSC,txtVAt,txtTotal;
		JLabel lblProv;

	
	CheckOutPage out = new CheckOutPage();
	int in = CheckOutPage.roomNoValue;
	
	
	// To get instance date
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
	Date date = new Date();  
	String currentDate = dateFormat.format(date);
	
	
	// Default constructor to initialze the contents of the frame
	public Invoice2() {
		JFrame frame= new JFrame();
		frame.setBackground(Color.WHITE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 977, 612);
		frame.setUndecorated(true);
		// To set the center
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
		frame.getContentPane().setLayout(null);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 936, 582);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Luton Hotel");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel.setBounds(380, 38, 408, 47);
		panel.add(lblNewLabel);

		JSeparator separator = new JSeparator();
		separator.setBounds(30, 175, 916, 9);
		panel.add(separator);

		JLabel lblGuest = new JLabel("Guest Name: ");
		lblGuest.setFont(new Font("Arial", Font.PLAIN, 20));
		lblGuest.setBounds(30, 190, 123, 33);
		panel.add(lblGuest);

		JLabel lblAddress = new JLabel("Address: ");
		lblAddress.setFont(new Font("Arial", Font.PLAIN, 20));
		lblAddress.setBounds(30, 233, 103, 33);
		panel.add(lblAddress);

		JLabel lblArrivalDate = new JLabel("Arr date:");
		lblArrivalDate.setFont(new Font("Arial", Font.PLAIN, 20));
		lblArrivalDate.setBounds(30, 276, 103, 33);
		panel.add(lblArrivalDate);

		txtName = new JTextField("");
		txtName.setBorder(null);
		txtName.setFont(new Font("Arial", Font.PLAIN, 16));
		txtName.setBounds(163, 190, 185, 33);
		panel.add(txtName);
		txtName.setColumns(10);

		txtAddress = new JTextField();
		txtAddress.setBorder(null);
		txtAddress.setFont(new Font("Arial", Font.PLAIN, 16));
		txtAddress.setColumns(10);
		txtAddress.setBounds(163, 233, 185, 33);
		panel.add(txtAddress);

		txtArrivalDate = new JTextField();
		txtArrivalDate.setFont(new Font("Arial", Font.PLAIN, 16));
		txtArrivalDate.setColumns(10);
		txtArrivalDate.setBounds(163, 276, 185, 33);
		txtArrivalDate.setBorder(null);
		panel.add(txtArrivalDate);

		JTextArea txtArea = new JTextArea();
		txtArea.setBounds(22, 629, 70, 33);
		panel.add(txtArea);

		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String printData = txtArea.getText();
				PrinterJob job = PrinterJob.getPrinterJob();
				boolean doPrint = job.printDialog();
				if (doPrint) {
					try {
						job.print();
					} catch (PrinterException e1) {

					}
				}
				
			}
		});
		btnPrint.setBounds(848, 34, 85, 21);
		panel.add(btnPrint);

		JLabel lblDepartureDate = new JLabel("Dep date:");
		lblDepartureDate.setFont(new Font("Arial", Font.PLAIN, 20));
		lblDepartureDate.setBounds(516, 276, 103, 33);
		panel.add(lblDepartureDate);

		txtDepartureDate = new JTextField();
		txtDepartureDate.setBorder(null);
		txtDepartureDate.setFont(new Font("Arial", Font.PLAIN, 16));
		txtDepartureDate.setColumns(10);
		txtDepartureDate.setBounds(725, 276, 185, 33);
		panel.add(txtDepartureDate);
		txtDepartureDate.setText(currentDate);

		JLabel lblNewLabel_1_2 = new JLabel("Invoice No:");
		lblNewLabel_1_2.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(516, 190, 123, 33);
		panel.add(lblNewLabel_1_2);

		txtInvoice = new JTextField();
		txtInvoice.setBorder(null);
		txtInvoice.setFont(new Font("Arial", Font.PLAIN, 16));
		txtInvoice.setColumns(10);
		txtInvoice.setBounds(725, 190, 185, 33);
		panel.add(txtInvoice);

		JLabel lblNewLabel_1_2_1_1_1 = new JLabel("Payment Status:");
		lblNewLabel_1_2_1_1_1.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_1_2_1_1_1.setBounds(516, 233, 159, 33);
		panel.add(lblNewLabel_1_2_1_1_1);

		txtBilldate = new JTextField();
		txtBilldate.setBorder(null);
		txtBilldate.setFont(new Font("Arial", Font.PLAIN, 16));
		txtBilldate.setColumns(10);
		txtBilldate.setBounds(725, 233, 185, 33);
		panel.add(txtBilldate);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(20, 319, 916, 9);
		panel.add(separator_1);

		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Arial", Font.BOLD, 20));
		lblDate.setBounds(32, 338, 85, 33);
		panel.add(lblDate);

		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Arial", Font.BOLD, 20));
		lblDescription.setBounds(149, 339, 152, 33);
		panel.add(lblDescription);

		JLabel lblSC= new JLabel("SC (10%)");
		lblSC.setFont(new Font("Arial", Font.BOLD, 20));
		lblSC.setBounds(468, 338, 100, 33);
		panel.add(lblSC);

		JLabel lblVAT = new JLabel("VAT(13%)");
		lblVAT.setFont(new Font("Arial", Font.BOLD, 20));
		lblVAT.setBounds(622, 338, 110, 33);
		panel.add(lblVAT);

		JLabel lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("Arial", Font.BOLD, 20));
		lblTotal.setBounds(797, 338, 47, 33);
		panel.add(lblTotal);

		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Arial", Font.BOLD, 20));
		lblAmount.setBounds(324, 338, 90, 33);
		panel.add(lblAmount);

		dxtDate = new JTextField();
		dxtDate.setBorder(new EmptyBorder(0, 0, 1, 0));
		dxtDate.setFont(new Font("Arial", Font.PLAIN, 16));
		dxtDate.setColumns(10);
		dxtDate.setBounds(30, 400, 103, 33);
		panel.add(dxtDate);
		dxtDate.setText(currentDate);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(20, 381, 916, 9);
		panel.add(separator_1_1);

		txtDesc = new JTextField();
		txtDesc.setBorder(new EmptyBorder(0, 0, 1, 0));
		txtDesc.setFont(new Font("Arial", Font.PLAIN, 16));
		txtDesc.setColumns(10);
		txtDesc.setBounds(151, 400, 135, 33);
		panel.add(txtDesc);


		txtRate = new JTextField();
		txtRate.setBorder(new EmptyBorder(0, 0, 1, 0));
		txtRate.setFont(new Font("Arial", Font.PLAIN, 16));
		txtRate.setColumns(10);
		txtRate.setBounds(324, 400, 85, 33);
		panel.add(txtRate);
		//txtRate.setEditable(false);

		txtSC = new JTextField();
		txtSC.setBorder(new EmptyBorder(0, 0, 1, 0));
		txtSC.setFont(new Font("Arial", Font.PLAIN, 16));
		txtSC.setColumns(10);
		txtSC.setBounds(468, 400, 70, 33);
		panel.add(txtSC);

		txtVAt = new JTextField();
		txtVAt.setBorder(new EmptyBorder(0, 0, 1, 0));
		txtVAt.setFont(new Font("Arial", Font.PLAIN, 16));
		txtVAt.setColumns(10);
		txtVAt.setBounds(635, 400, 70, 33);
		panel.add(txtVAt);

		txtTotal = new JTextField();
		txtTotal.setBorder(new EmptyBorder(0, 0, 1, 0));
		txtTotal.setFont(new Font("Arial", Font.PLAIN, 16));
		txtTotal.setColumns(10);
		txtTotal.setBounds(798, 400, 85, 33);
		panel.add(txtTotal);

		JLabel lblLuton = new JLabel("Luton, United Kingdom");
		lblLuton.setFont(new Font("Arial", Font.BOLD, 20));
		lblLuton.setBounds(327, 76, 314, 33);
		panel.add(lblLuton);

		JLabel lblTelephone = new JLabel("Tel: +44 2452456");
		lblTelephone.setFont(new Font("Arial", Font.BOLD, 20));
		lblTelephone.setBounds(360, 95, 223, 33);
		panel.add(lblTelephone);
		
		JLabel lblEnd = new JLabel("Thank you visit again");
		lblEnd.setFont(new Font("Arial", Font.ITALIC, 15));
		lblEnd.setBounds(400, 519, 190, 33);
		panel.add(lblEnd);
		
		
		// try-catch block
		try {
			
			// Create connection Object
			Connection con = DatabaseConnectionUtil.ConnectDB();
			
			// Query to get room id 
			String query1 = "select roomID, roomType, roomPrice from room where roomNo='"+in+"'";
			PreparedStatement pst1 = con.prepareStatement(query1);
			ResultSet rst1 = pst1.executeQuery();
			
			int rid = 0;
			String roomType = "";
			int price = 0;
			
			while(rst1.next()) {
				rid = rst1.getInt(1);
				roomType = rst1.getString(2);
				price = rst1.getInt(3);
			}
			
			String add = roomType + " ( " + in + " )";
			txtDesc.setText(add);
			
			System.out.println("The extracted room id is: " + rid);
			
			
			// Query to get booking id and customer id 
			String query2 = "select bookingID, customerID from booking where roomID='"+rid+"'";
			PreparedStatement pst2 = con.prepareStatement(query2);
			ResultSet rst2 = pst2.executeQuery();
						
			int bid = 0;
			int cid = 0;
			
			while(rst2.next()) {
					bid = rst2.getInt(1);
					cid = rst2.getInt(2);
			}
			
			System.out.println("The extracted booking id is: " + bid);
			System.out.println("The extracted customer id is: " + cid);
			
			// Query to get customer name
			String query3 = "select fullName, address, userType from customer where customerID='"+cid+"'";
			PreparedStatement pst3 = con.prepareStatement(query3);
			ResultSet rst3 = pst3.executeQuery();
			
			String name = "";
			String address = "";
			String type = "";
			
			while(rst3.next()) {
				name = rst3.getString(1);
				address = rst3.getString(2);
				type = rst3.getString(3);
			}
			
			txtName.setText(name);
			txtAddress.setText(address);
			
			
			
			// Query to get arrival date 
			String query4 = "select arrivalDate, bookingStatus from booking where bookingID='"+bid+"'";
			PreparedStatement pst4 = con.prepareStatement(query4);
			ResultSet rst4 = pst4.executeQuery();
			
			String arrival = "";
			String stat = "";
			
			while(rst4.next()) {
				arrival = rst4.getString(1);
				stat = rst4.getString(2);
			}
			
			txtArrivalDate.setText(arrival);
			
			
			// Query to get amount from  table 
			String query5 = "select total from service where bookingID='"+bid+"'";
			PreparedStatement pst5 = con.prepareStatement(query5);
			ResultSet rst5 = pst5.executeQuery();
			
			float amount = 0.0f;
			float sum = 0.0f;
			
			while(rst5.next()) {
				amount = rst5.getFloat(1);
				sum = sum + amount;
			}
			
			
			System.out.println("The total amount is " + sum);
			System.out.println("The room price is " + price);
			
			float fPrice = price;
			
			// Service price plus room price
			float totalAmt = fPrice+sum;
			System.out.println("The total amount of customer " + totalAmt);
			String tot = String.format("%.2f", totalAmt);
			txtRate.setText(tot);
			
			
			// Calculate service charge
			float sc = (10 * totalAmt)/100;
			String sc1 = String.format("%.2f", sc);
			txtSC.setText(sc1);
			
			// Calculate vat 
			float vat = (13 * totalAmt)/100;
			String vat1 = String.format("%.2f", vat);
			txtVAt.setText(vat1);
			
			//Add all the amount
			float totalAmount = (totalAmt + sc + vat);
			String t22 = String.format("%.2f", totalAmount);
			txtTotal.setText(t22);
			
			JPanel pnl1 = new JPanel();
			pnl1.setLayout(null);
			pnl1.setBackground(new Color(0, 128, 255));
			pnl1.setBounds(-9, -1, 955, 34);
			panel.add(pnl1);
			
			JLabel lblPaymentReceipt = new JLabel("Bill Receipt");
			lblPaymentReceipt.setForeground(new Color(255, 255, 240));
			lblPaymentReceipt.setFont(new Font("Arial", Font.BOLD, 22));
			lblPaymentReceipt.setBounds(400, 0, 335, 34);
			pnl1.add(lblPaymentReceipt);
			
			JLabel lblexit = new JLabel("X");
			lblexit.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.setVisible(false);
					CheckOutPage check= new CheckOutPage();
					check.showTable();

				}
			});
			lblexit.setForeground(new Color(255, 255, 240));
			lblexit.setFont(new Font("Arial", Font.BOLD, 22));
			lblexit.setBounds(915, 0, 30, 34);
			pnl1.add(lblexit);
			
			JLabel lblMin = new JLabel("-");
			lblMin.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.setState(Frame.ICONIFIED);
				}
			});
			lblMin.setForeground(new Color(255, 255, 240));
			lblMin.setFont(new Font("Arial", Font.BOLD, 40));
			lblMin.setBounds(885, 4, 20, 21);
			pnl1.add(lblMin);
						
			if(stat.equals("Checked In")) {
				
				lblProv = new JLabel("Provisional");
				lblProv.setFont(new Font("Times New Roman", Font.BOLD, 20));
				lblProv.setBounds(415, 140, 129, 33);
				panel.add(lblProv);
				
				txtInvoice.setText("");
				txtBilldate.setText("Unclear");
				
			}else {
				
				lblProv = new JLabel("Tax Invoice");
				lblProv.setFont(new Font("Times New Roman", Font.BOLD, 20));
				lblProv.setBounds(415, 140, 129, 33);
				panel.add(lblProv);
				
				// Query to insert data into Receipt table;
				String insertQuery = "insert into receipt(bookingID, amount, serviceCharge, VATCharge, grandTotal) values(?,?,?,?,?)";
				PreparedStatement insertPst = con.prepareStatement(insertQuery);
				
				insertPst.setInt(1, bid);
				insertPst.setFloat(2, totalAmt);
				insertPst.setFloat(3, sc);
				insertPst.setFloat(4, vat);
				insertPst.setFloat(5, totalAmount);
				insertPst.execute();
				
				if(type.equals("Normal Customer")) {
					
					// Ouery to update payment status
					String q1 = "update receipt set paymentStatus=? where bookingID='"+bid+"'";
					PreparedStatement pst6 = con.prepareStatement(q1);
					pst6.setString(1, "Cleared");
					pst6.executeUpdate();
					txtBilldate.setText("Cleared");
				}
				
				else {
					// Ouery to update payment status
					String q1 = "update receipt set paymentStatus=? where bookingID='"+bid+"'";
					PreparedStatement pst6 = con.prepareStatement(q1);
					pst6.setString(1, "Credit");
					pst6.executeUpdate();
					txtBilldate.setText("Credit");
				}
				
				String s1 = Integer.toString(bid);
				txtInvoice.setText(s1);
			}
			
		}
		
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, "Error!!!");
		}
		
		
		frame.setVisible(true);
	}
	
	
	// Main method to run the application
		public static void main(String[] args) {
		      Invoice2 frame = new Invoice2();
		      
		}
}
