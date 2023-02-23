import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;


public class Staff extends JFrame {

	private JPanel contentPane;
	private JButton btnRoomAllotment;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Staff frame = new Staff();
					frame.rmBtn();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection connect(){
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con =  DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "shivani", "bmsit");
			return con;
		} 
		catch ( SQLException e1)
		{
			e1.printStackTrace();
			return null;
		}
		catch (ClassNotFoundException e2) {
			e2.printStackTrace();
			return null;
		}
	}

	/**
	 * Create the frame.
	 */
	public Staff() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setSize(650, 600);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("STAFF");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 21));
		lblNewLabel.setBounds(516, 193, 330, 50);
		contentPane.add(lblNewLabel);
		
		JButton btnBookAppointment = new JButton("BOOK APPOINTMENT");
		btnBookAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Appointment appointment = new Appointment();
				appointment.setVisible(true);
				setVisible(false);
			}
		});
		btnBookAppointment.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnBookAppointment.setBounds(516, 348, 330, 50);
		contentPane.add(btnBookAppointment);
		
		
		btnRoomAllotment = new JButton("ROOM ALLOTMENT");
		btnRoomAllotment.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnRoomAllotment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				Room rm = new Room();
				rm.tabVal();
				rm.setVisible(true);
				setVisible(false);
			}
		});
		btnRoomAllotment.setBounds(516, 439, 330, 50);
		contentPane.add(btnRoomAllotment);
	
		
		JButton btnRegisterPatient = new JButton("REGISTER PATIENT");
		btnRegisterPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Register register= new Register();
				register.setVisible(true);
				setVisible(false);
			}
		});
		btnRegisterPatient.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnRegisterPatient.setBounds(516, 271, 330, 50);
		contentPane.add(btnRegisterPatient);
		
		JButton button = new JButton("<<<BACK ");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				setVisible(false);
				YouAre yr = new YouAre();
				yr.setVisible(true);
			}
		});
		button.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		button.setBounds(516, 517, 330, 50);
		contentPane.add(button);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("D:\\PBL\\medical-background-103.jpg"));
		lblNewLabel_1.setBounds(-281, 0, 1643, 694);
		contentPane.add(lblNewLabel_1);
		
	}
	void rmBtn()
	{
		try
		{
			Connection con = connect();
		Statement smt = con.createStatement();
		String query = "SELECT * FROM diagnosis WHERE rmallot = 'Not done' and rmreq ='Yes'";
		ResultSet rs = smt.executeQuery(query);
		if(rs.next())
		{
			btnRoomAllotment.setVisible(true);
		}
		
		}
		catch(SQLException e)
		{
			System.out.println(e);
			
		}
	}
}
