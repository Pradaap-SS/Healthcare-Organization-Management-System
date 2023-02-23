import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.ImageIcon;


public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField tr1;
	private JTextField tr2;
	private JTextField tr3;
	private JTextField tr4;
	private JTextField tr6;
	private JTextField tr7;
	private JTextField tr8;
	private JTextField tr5;
	private JLabel cmt;
	private JButton btnAppoint, btnCancel;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
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
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "shivani", "bmsit");
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
	public Register() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setSize(650, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRegisterPatient = new JLabel("REGISTER  PATIENT");
		lblRegisterPatient.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 24));
		lblRegisterPatient.setBounds(531, 109, 300, 25);
		contentPane.add(lblRegisterPatient);
		
		JLabel lblEmContact = new JLabel("Emergency Contact Details");
		lblEmContact.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 24));
		lblEmContact.setBounds(471, 343, 419, 52);
		contentPane.add(lblEmContact);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 24));
		lblName.setBounds(468, 150, 153, 25);
		contentPane.add(lblName);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 24));
		lblGender.setBounds(468, 185, 153, 25);
		contentPane.add(lblGender);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 24));
		lblAge.setBounds(468, 218, 153, 25);
		contentPane.add(lblAge);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 24));
		lblPhone.setBounds(468, 254, 153, 25);
		contentPane.add(lblPhone);
		
		JLabel lblAdharNo = new JLabel("Adhar No");
		lblAdharNo.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 24));
		lblAdharNo.setBounds(468, 290, 153, 25);
		contentPane.add(lblAdharNo);
		
		JLabel lblEmName = new JLabel("Name");
		lblEmName.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 24));
		lblEmName.setBounds(468, 406, 153, 25);
		contentPane.add(lblEmName);
		
		JLabel lblEmRel = new JLabel("Relation");
		lblEmRel.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 24));
		lblEmRel.setBounds(468, 442, 153, 25);
		contentPane.add(lblEmRel);
		
		JLabel lblEmPhNo = new JLabel("Phone No");
		lblEmPhNo.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 24));
		lblEmPhNo.setBounds(471, 478, 153, 25);
		contentPane.add(lblEmPhNo);
		
		tr1 = new JTextField();
		tr1.setBounds(627, 150, 204, 25);
		contentPane.add(tr1);
		tr1.setColumns(10);
		
		tr2 = new JTextField();
		tr2.setBounds(627, 185, 203, 25);
		contentPane.add(tr2);
		tr2.setColumns(10);
		
		tr3 = new JTextField();
		tr3.setBounds(626, 218, 203, 25);
		contentPane.add(tr3);
		tr3.setColumns(10);
		
		tr4 = new JTextField();
		tr4.setBounds(626, 254, 203, 25);
		contentPane.add(tr4);
		tr4.setColumns(10);
		
		tr5 = new JTextField();
		tr5.setBounds(626, 290, 204, 25);
		contentPane.add(tr5);
		tr5.setColumns(10);
		
		tr6 = new JTextField();
		tr6.setBounds(627, 406, 203, 25);
		contentPane.add(tr6);
		tr6.setColumns(10);
		
		tr7 = new JTextField();
		tr7.setBounds(627, 442, 203, 25);
		contentPane.add(tr7);
		tr7.setColumns(10);
		
		tr8 = new JTextField();
		tr8.setBounds(628, 478, 203, 25);
		contentPane.add(tr8);
		tr8.setColumns(10);
		
		JButton btnSumit = new JButton("Submit");
		btnSumit.setBounds(516, 540, 131, 25);
		contentPane.add(btnSumit);
		
		cmt = new JLabel("");
		cmt.setBounds(471, 577, 419, 52);
		contentPane.add(cmt);
		cmt.setVisible(false);
		setVisible(false);
		setVisible(false);
		
		btnAppoint = new JButton("BOOK APPOINTMENT");
		btnAppoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Appointment frm = new Appointment();
				frm.setVisible(true);
				setVisible(false);
			}
		});
		btnAppoint.setBounds(604, 644, 153, 25);
		contentPane.add(btnAppoint);
		btnAppoint.setVisible(false);
		
		btnSumit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = tr1.getText();
				String gender = tr2.getText();
				int age = Integer.parseInt(tr3.getText());
				long phone = Long.parseLong(tr4.getText());
				long adhar = Long.parseLong(tr5.getText());
				String eName = tr6.getText();
				String eRelation = tr7.getText();
				long ePhone = Long.parseLong(tr8.getText());
				try
				{
					Connection con = connect();
					String query = "insert into patient(name,sex,age,phno,adhar) values (?,?,?,?,?)";
					PreparedStatement ps1 = con.prepareStatement(query);
					ps1.setString(1, name);
					ps1.setString(2, gender);
					ps1.setInt(3, age);
					ps1.setLong(4, phone);
					ps1.setLong(5, adhar);
					ps1.execute();
					ps1.close();
					
					Statement smt = con.createStatement();
					query = "SELECT pno FROM patient WHERE adhar = '" + adhar + "'";
					ResultSet rs = smt.executeQuery(query);
					if(rs.next())
					{
			            int pno= rs.getInt("pno");
			            query = "insert into emercont(pno,name,relation,phno) values(?,?,?,?)";
						PreparedStatement ps2 = con.prepareStatement(query);
						ps2.setInt(1, pno);
						ps2.setString(2, eName);
						ps2.setString(3, eRelation);
						ps2.setLong(4, ePhone);
						ps2.execute();
						ps2.close();
						cmt.setText("Patient ID " + pno + "has been generated for " + name);
						cmt.setVisible(true);
						btnAppoint.setVisible(true);
			        }
				}
				catch (SQLException e1)
				{
					e1.printStackTrace();
				}
			}
		});
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(675, 540, 131, 25);
		contentPane.add(btnCancel);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("D:\\PBL\\medical-background-103.jpg"));
		lblNewLabel.setBounds(-313, 0, 1675, 694);
		contentPane.add(lblNewLabel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Staff frm = new Staff();
				frm.setVisible(true);
				setVisible(false);
			}
		});
	}
}
