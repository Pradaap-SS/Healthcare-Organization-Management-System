import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class Doctor extends JFrame {

	private JPanel contentPane;
	private JTextField t1;
	private JComboBox Box1,Box2;
	private JButton btnSubmit,btnReset;
	private JTextArea a1, a2;
	private JLabel l1,l2,l3,lblPatientSymptoms,lblPrescription,lblRoom,lblAllotSupport;
	private DefaultComboBoxModel model = new DefaultComboBoxModel();
	private JScrollPane sp1;
	private JScrollPane sp2;
	private JLabel label;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Doctor frame = new Doctor();
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
	public Doctor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 650, 600);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setSize(650, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDoctor = new JLabel("Doctor");
		lblDoctor.setHorizontalAlignment(SwingConstants.CENTER);
		lblDoctor.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lblDoctor.setBounds(633, 11, 96, 33);
		contentPane.add(lblDoctor);
		
		JLabel l12 = new JLabel("Appointment Number");
		l12.setBounds(412, 86, 172, 20);
		contentPane.add(l12);
		
		t1 = new JTextField();
		t1.setBounds(594, 86, 139, 20);
		contentPane.add(t1);
		t1.setColumns(10);
		
		lblPatientSymptoms = new JLabel("Patient Symptoms");
		lblPatientSymptoms.setHorizontalAlignment(SwingConstants.CENTER);
		lblPatientSymptoms.setBounds(553, 255, 139, 33);
		contentPane.add(lblPatientSymptoms);
		lblPatientSymptoms.setVisible(false);
		
		lblPrescription = new JLabel("Prescription");
		lblPrescription.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrescription.setBounds(553, 385, 139, 33);
		contentPane.add(lblPrescription);
		lblPrescription.setVisible(false);
		
		lblRoom = new JLabel("Room");
		lblRoom.setBounds(682, 533, 70, 17);
		contentPane.add(lblRoom);
		lblRoom.setVisible(false);
		
		String [] s= {"Yes","No"};
		Box1 = new JComboBox(s);
		Box1.setBounds(801, 531, 60, 20);
		contentPane.add(Box1);
		Box1.setVisible(false);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnReset.setBounds(822, 650, 107, 23);
		contentPane.add(btnReset);
		btnReset.setVisible(false);
		
		l1 = new JLabel("");
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		l1.setBounds(578, 139, 210, 14);
		contentPane.add(l1);
		
		l2 = new JLabel("");
		l2.setHorizontalAlignment(SwingConstants.CENTER);
		l2.setBounds(578, 172, 210, 14);
		contentPane.add(l2);
		
		l3 = new JLabel("");
		l3.setHorizontalAlignment(SwingConstants.CENTER);
		l3.setBounds(578, 203, 210, 14);
		contentPane.add(l3);
		
		Box2 = new JComboBox(model);
		Box2.setBounds(787, 576, 89, 20);
		contentPane.add(Box2);
		Box2.setVisible(false);
		
		lblAllotSupport = new JLabel("Allot Support ");
		lblAllotSupport.setHorizontalAlignment(SwingConstants.CENTER);
		lblAllotSupport.setBounds(644, 579, 89, 17);
		contentPane.add(lblAllotSupport);
		lblAllotSupport.setVisible(false);
		
		sp1 = new JScrollPane();
		sp1.setBounds(718, 228, 254, 112);
		contentPane.add(sp1);
		
		a1 = new JTextArea();
		sp1.setViewportView(a1);
		a1.setRows(5);
		sp1.setVisible(false);
		
		sp2 = new JScrollPane();
		sp2.setBounds(718, 371, 254, 112);
		contentPane.add(sp2);
		
		a2  = new JTextArea();
		a2.setRows(5);
		sp2.setViewportView(a2);
		sp2.setVisible(false);
		
		
		JButton btnGetDetails = new JButton("Get Details");
		btnGetDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				int ano = Integer.parseInt(t1.getText());
				try{
				Connection con = connect();
				Statement smt = con.createStatement();
				String query = "SELECT astat FROM appoint WHERE ano = '" + ano + "'";
				ResultSet rs = smt.executeQuery(query);
				if(rs.next())
				{
					String astat= rs.getString("astat");
				if(astat.equals("Done"))
				{
					l1.setText("Appointment Already Completed");
					l1.setVisible(true);
				}
				else{
				smt = con.createStatement();
				query = "SELECT pno FROM appoint WHERE ano = '" + ano + "'";
				rs = smt.executeQuery(query);
				if(rs.next())
				{
					int pno= rs.getInt("PNO");
					query = "SELECT name,age,sex FROM patient WHERE pno = '" + pno + "'";
					
					rs=smt.executeQuery(query);
					if(rs.next())
					{
						String name = rs.getString("name");
						l1.setText(" Name " + name);
						int age = rs.getInt("age");
						l2.setText(" Age " + age);
						String sex = rs.getString("sex");
						l3.setText(" Gender " + sex);
						
						model.addElement("None");
						query = "Select name from support";
						rs = smt.executeQuery(query);
						while(rs.next())
						{
							String sup = rs.getString("name");
							model.addElement(sup);
						}	
							lblPatientSymptoms.setVisible(true);
							lblPrescription.setVisible(true);
							lblRoom.setVisible(true);
							Box1.setVisible(true);
							btnSubmit.setVisible(true);
							btnReset.setVisible(true);
							sp1.setVisible(true);
							sp2.setVisible(true);
							lblAllotSupport.setVisible(true);
							Box2.setVisible(true);
							

						
					}

					
				}
				}
			}
				else
				{
					l1.setText("Invalid Appointment Number");
				}
				}
				catch(SQLException e)
				{
					System.out.println(e);
				}
			}
		});
		btnGetDetails.setBounds(762, 85, 107, 23);
		contentPane.add(btnGetDetails);
		

		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				int ano = Integer.parseInt(t1.getText());
				String sym = a1.getText();
				String pres = a2.getText();
				String rmyn = Box1.getSelectedItem().toString();
				String supt = Box2.getSelectedItem().toString();
				try{
				Connection con = connect();
				Statement smt = con.createStatement();
				String query = "SELECT pno,did,adate FROM appoint WHERE ano = '" + ano + "'";
				ResultSet rs = smt.executeQuery(query);
				if(rs.next())
				{
					int pno= rs.getInt("PNO");
					int did=rs.getInt("did");
					Date dt=rs.getDate("adate");
					
					query = "insert into diagnosis(pno,did,symptoms,prescription,rmreq,rmallot,paystat,digdate,ano) values (?,?,?,?,?,'Not done','Not Paid',?,?)";
					PreparedStatement ps1 = con.prepareStatement(query);
					ps1.setInt(1, pno);
					ps1.setInt(2, did);
					ps1.setString(3, sym);
					ps1.setString(4, pres);
					ps1.setString(5, rmyn);
					ps1.setDate(6, dt);
					ps1.setInt(7, ano);
					ps1.execute();
					ps1.close();
					
					query = "update appoint set astat ='Done' where ano='" + ano + "'";
					smt.execute(query);
					if(!supt.equals("None"))
					{
					query = "SELECT sid FROM support WHERE name = '" + supt + "'";
					rs = smt.executeQuery(query);
					if(rs.next())
					{
						int sid= rs.getInt("sid");
						
						query = "insert into supalloc(pno,sid,sadate) values (?,?,?)";
						ps1 = con.prepareStatement(query);
						ps1.setInt(1, pno);
						ps1.setInt(2, sid);
						ps1.setDate(3, dt);
						ps1.execute();
						ps1.close();
					}
					}
						
						lblPatientSymptoms.setVisible(false);
						lblPrescription.setVisible(false);
						lblRoom.setVisible(false);
						Box1.setVisible(false);
						btnSubmit.setVisible(false);
						btnReset.setVisible(false);
						sp1.setVisible(false);
						sp2.setVisible(false);
						lblAllotSupport.setVisible(false);
						Box2.setVisible(false);
						
						Box1.setSelectedIndex(-1);
						a1.setText("");
						a2.setText("");
						Box2.setSelectedIndex(-1);
						t1.setText("");
						l1.setVisible(false);
						l2.setVisible(false);
						l3.setVisible(false);
						//l4.setVisible(false);
					}
				}
			

				catch(SQLException e)
				{
					e.printStackTrace();	
				}
				}
		});
		btnSubmit.setBounds(628, 650, 107, 23);
		contentPane.add(btnSubmit);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
			
				setVisible(false);
				YouAre ya = new YouAre();
				ya.setVisible(true);
			}
		});
		btnExit.setBounds(918, 85, 107, 23);
		contentPane.add(btnExit);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon("D:\\PBL\\medical-background-103.jpg"));
		label.setBounds(-299, 0, 1661, 705);
		contentPane.add(label);
		btnSubmit.setVisible(false);
		
	}
}