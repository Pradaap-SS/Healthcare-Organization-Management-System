import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;

import net.proteanit.sql.DbUtils;

import java.awt.event.*;
import java.sql.*;
import java.time.LocalDate;


public class Appointment extends JFrame {

	private JPanel contentPane;
	private JTextField pid;
	private JLabel lblDepartmentList,lblSlotBooking,l1,l2,l3,l4,l5;
	private JComboBox comboBox;
	private JRadioButton r1,r2,r3,r4,r5,r6,r7,r8;
	private JButton btnSubmit,btnCancel ;
	private JTable table;
	private JScrollPane scrollPane;
	private ButtonGroup bg;
	private JRadioButton rn;
	private JLabel cmt;
	private JLabel lblNewLabel;
	private JButton button;
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Appointment frame = new Appointment();
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
	public Appointment() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setSize(650, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cmt = new JLabel("");
		cmt.setBounds(168, 573, 200, 50);
		contentPane.add(cmt);
		
		JLabel lblBookAppointment = new JLabel("Book Appointment");
		lblBookAppointment.setFont(new Font("Times New Roman", Font.PLAIN, 23));
		lblBookAppointment.setBounds(581, 11, 200, 40);
		contentPane.add(lblBookAppointment);
		
		JLabel lblPatientId = new JLabel("Patient ID");
		lblPatientId.setBounds(479, 58, 113, 40);
		contentPane.add(lblPatientId);
		
		pid = new JTextField();
		pid.setBounds(610, 59, 113, 40);
		contentPane.add(pid);
		pid.setColumns(10);
		

		
		lblDepartmentList = new JLabel("Department List");
		lblDepartmentList.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblDepartmentList.setHorizontalAlignment(SwingConstants.CENTER);
		lblDepartmentList.setBounds(488, 287, 113, 40);
		contentPane.add(lblDepartmentList);
		lblDepartmentList.setVisible(false);
		
		String[] str = {"Allergist",			"Cardiologist",		"Dermatologist",
						"Emergency Medicine",	"General Medicine",	"Gynecologist",
						"Hematologist",			"Nephrologist",		"Neuroogist",
						"Oral Surgeon",			"Ophthalmologist",	"Orthopedic Surgeon",
						"Pathlogist",			"Pediatrician",		"Physician"};
		
		comboBox = new JComboBox(str);
		comboBox.setBounds(680, 299, 144, 20);
		contentPane.add(comboBox);
		comboBox.setVisible(false);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				try
				{
				String value = comboBox.getSelectedItem().toString();
				Connection con = connect();
				Statement smt = con.createStatement();
				String query = "SELECT DId,Name,Sex,Age,Dept FROM doctor WHERE dept = '" + value + "'";
				ResultSet rs = smt.executeQuery(query);
				table.setModel(DbUtils.resultSetToTableModel(rs));
				
				scrollPane.setVisible(true);
				table.setVisible(true);
				lblSlotBooking.setVisible(false);
				r1.setVisible(false);
				r2.setVisible(false);
				r3.setVisible(false);
				r4.setVisible(false);
				r5.setVisible(false);
				r6.setVisible(false);
				r7.setVisible(false);
				r8.setVisible(false);
				btnSubmit.setVisible(false);
				btnCancel.setVisible(false);
			}
			catch(Exception e)
			{
				System.out.println(e);
				
			}
			
			}});
		
		table = new JTable(){
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        }
	    };
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(288, 368, 782, 80);
		contentPane.add(scrollPane);
		scrollPane.setVisible(false);
		//table.setVisible(false);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	lblSlotBooking.setVisible(true);
				r1.setVisible(true);
				r2.setVisible(true);
				r3.setVisible(true);
				r4.setVisible(true);
				r5.setVisible(true);
				r6.setVisible(true);
				r7.setVisible(true);
				r8.setVisible(true);
				btnSubmit.setVisible(false);
				btnCancel.setVisible(false);
	        }
	    });
		
		lblSlotBooking = new JLabel("Slot Booking");
		lblSlotBooking.setHorizontalAlignment(SwingConstants.CENTER);
		lblSlotBooking.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblSlotBooking.setBounds(619, 455, 115, 40);
		contentPane.add(lblSlotBooking);
		lblSlotBooking.setVisible(false);
		
		rn = new JRadioButton("None");
		rn.setBounds(410, 523, 87, 50);
		contentPane.add(rn);
		rn.setVisible(false);
		
		bg = new ButtonGroup();
		bg.add(rn);
		rn.setSelected(true);
		bg.getSelection().addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0)
			{
				try
				{
				scrollPane.setVisible(true);
				table.setVisible(true);
				lblSlotBooking.setVisible(true);
				r1.setVisible(true);
				r2.setVisible(true);
				r3.setVisible(true);
				r4.setVisible(true);
				r5.setVisible(true);
				r6.setVisible(true);
				r7.setVisible(true);
				r8.setVisible(true);
				btnSubmit.setVisible(true);
				btnCancel.setVisible(true);
			}
			catch(Exception e)
			{
				System.out.println(e);
				
			}
			
			}});
		
		r1 = new JRadioButton("10am-11:30am");
		r1.setActionCommand("10am-11:30am");
		r1.setBounds(499, 497, 109, 50);
		contentPane.add(r1);
		r1.setVisible(false);
		bg.add(r1);
		
		r2 = new JRadioButton("11:30am-12pm");
		r2.setActionCommand("11:30am-12pm");
		r2.setBounds(610, 497, 109, 50);
		contentPane.add(r2);
		r2.setVisible(false);
		bg.add(r2);
		
		r3 = new JRadioButton("12pm-12:30pm");
		r3.setActionCommand("12pm-12:30pm");
		r3.setBounds(721, 497, 109, 50);
		contentPane.add(r3);
		r3.setVisible(false);
		bg.add(r3);
		
		r4 = new JRadioButton("12:30pm-1pm");
		r4.setActionCommand("12:30pm-1pm");
		r4.setBounds(832, 497, 109, 50);
		contentPane.add(r4);
		r4.setVisible(false);
		bg.add(r4);
		
		r5 = new JRadioButton("5pm-5:30pm");
		r5.setActionCommand("5pm-5:30pm");
		r5.setBounds(499, 539, 109, 50);
		contentPane.add(r5);
		r5.setVisible(false);
		bg.add(r5);
		
		r6 = new JRadioButton("5:30pm-6pm");
		r6.setActionCommand("5:30pm-6pm");
		r6.setBounds(610, 539, 109, 50);
		contentPane.add(r6);
		r6.setVisible(false);
		bg.add(r6);
		
		r7 = new JRadioButton("6pm-6:30pm");
		r7.setActionCommand("6pm-6:30pm");
		r7.setBounds(721, 539, 109, 50);
		contentPane.add(r7);
		r7.setVisible(false);
		bg.add(r7);
		
		r8 = new JRadioButton("6:30pm-7pm");
		r8.setActionCommand("6:30pm-7pm");
		r8.setBounds(832, 539, 109, 50);
		contentPane.add(r8);
		r8.setVisible(false);
		bg.add(r8);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setVisible(false);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				Staff staff = new Staff();
				staff.setVisible(true);
				setVisible(false);
			}
		});
		btnCancel.setBounds(634, 608, 144, 23);
		contentPane.add(btnCancel);
		
		l1 = new JLabel("");
		l1.setBounds(539, 96, 285, 40);
		contentPane.add(l1);
		
		l2 = new JLabel("");
		l2.setBounds(539, 131, 285, 40);
		contentPane.add(l2);
		
		l3 = new JLabel("");
		l3.setBounds(539, 165, 285, 40);
		contentPane.add(l3);
		
		l4 = new JLabel("");
		l4.setBounds(539, 200, 285, 40);
		contentPane.add(l4);
		

		l5 = new JLabel("");
		l5.setBounds(539, 234, 285, 40);
		contentPane.add(l5);
		
		
		JButton btnGetDetails = new JButton("Get Details");
		btnGetDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				int pno = Integer.parseInt(pid.getText());
				try{
				Connection con = connect();
				Statement smt = con.createStatement();
				String query = "SELECT * FROM patient WHERE pno = '" + pno + "'";
				ResultSet rs = smt.executeQuery(query);
				if(rs.next())
				{
					int pno1= rs.getInt("PNO");
					l1.setText(" Number " + pno1);
					String name = rs.getString("NAME");
					l2.setText(" Name " + name);
					int age = rs.getInt("AGE");
					l3.setText(" Age " + age);
					String sex = rs.getString("SEX");
					l4.setText(" Gender " + sex);
					long phone = rs.getLong("PHNO");
					l5.setText(" Phone " + phone);
					
					lblDepartmentList.setVisible(true);
					comboBox.setVisible(true);
					
				}
				}
				catch(SQLException e)
				{
					System.out.println(e);
				}
				
				}
			
		});
		btnGetDetails.setBounds(772, 58, 113, 40);
		contentPane.add(btnGetDetails);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int pno = Integer.parseInt(pid.getText());
				int did = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
				Date adate = Date.valueOf(LocalDate.now());
				String slot = bg.getSelection().getActionCommand();
				try{
				Connection con = connect();
				String query = "insert into appoint(pno,did,adate,slot,astat) values (?,?,?,?,'Not Done')";
				PreparedStatement ps1 = con.prepareStatement(query);
				ps1.setInt(1, pno);
				ps1.setInt(2, did);
				ps1.setDate(3, adate);
				ps1.setString(4, slot);
				ps1.execute();
				ps1.close();
				Statement smt = con.createStatement();
				query = "SELECT ano FROM appoint WHERE pno = '" + pno + "' AND did = '" + did + "'";
				ResultSet rs = smt.executeQuery(query);
				while(rs.next())
				{
					int ano = rs.getInt("ano");
					cmt.setText("Appointment " + ano + "has been created for Patient Number" + pno);
		        }
				}
				catch(SQLException e)
				{
					System.out.println(e);
					
				}
			}
		});
		btnSubmit.setBounds(440, 608, 144, 23);
		contentPane.add(btnSubmit);
		
		button = new JButton("<<< Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Staff sf = new Staff();
				sf.setVisible(true);
				setVisible(false);
			}
		});
		button.setBounds(903, 58, 120, 40);
		contentPane.add(button);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("D:\\PBL\\medical-background-103.jpg"));
		lblNewLabel.setBounds(-324, 0, 1686, 705);
		contentPane.add(lblNewLabel);
		
		btnSubmit.setVisible(false);
	}
}