import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.proteanit.sql.DbUtils;

import java.sql.*;
import java.time.LocalDate;



public class Room extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextPane txtpnCosts ;
	private JScrollPane scrollPane,scrollPane_1,scrollPane_2;
	private JComboBox tcb;
	private JLabel lblRoomType;
	private JLabel lblNoD;
	private JTextField textField;
	private JButton btnSubmit, btnCancel;
	private int rno;
	private String type;
	private JTable table_1;
	private JButton button;
	private JLabel label;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Room frame = new Room();
					frame.tabVal();
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
	public Room() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setSize(650, 600);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRoomAllotment = new JLabel("Room Allotment");
		lblRoomAllotment.setFont(new Font("Times New Roman", Font.PLAIN, 23));
		lblRoomAllotment.setHorizontalAlignment(SwingConstants.CENTER);
		lblRoomAllotment.setBounds(581, 53, 200, 42);
		contentPane.add(lblRoomAllotment);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(517, 120, 319, 100);
		contentPane.add(scrollPane);
		
		table = new JTable(){
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        }
	    };
	    

		scrollPane.setViewportView(table);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	scrollPane_1.setVisible(true);
	        	txtpnCosts.setVisible(true);
				tcb.setVisible(true);
				lblRoomType.setVisible(true);
				lblNoD.setVisible(false);
				textField.setVisible(false);
				btnSubmit.setVisible(false);
				btnCancel.setVisible(false);
				scrollPane_2.setVisible(false);
				table_1.setVisible(false);
	        }
	    });
		
		
		
		
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(890, 384, 166, 111);
		contentPane.add(scrollPane_1);
		scrollPane_1.setVisible(false);
		
		txtpnCosts = new JTextPane();
		txtpnCosts.setEditable(false);
		txtpnCosts.setText("Costs:-\r\rGeneral Ward: Rs 500/-\rPrivate Ward:   Rs 1000/-\rA/C Ward:\t   Rs 1500/-\rICU\t   Rs 2000/- ");
		scrollPane_1.setViewportView(txtpnCosts);
		
		String str[] = {"General Ward","Private Ward","A/C Ward","ICU"};
		tcb = new JComboBox(str);
		tcb.setBounds(385, 438, 120, 23);
		contentPane.add(tcb);
		tcb.setVisible(false);
		tcb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				try
				{
				String value = tcb.getSelectedItem().toString();
				Connection con = connect();
				Statement smt = con.createStatement();
				String query = "SELECT RNo as RoomNumber FROM Room WHERE type = '" + value + "'";
				ResultSet rs = smt.executeQuery(query);
				table_1.setModel(DbUtils.resultSetToTableModel(rs));
				
				scrollPane_1.setVisible(true);
	        	txtpnCosts.setVisible(true);
				tcb.setVisible(true);
				lblRoomType.setVisible(true);
				scrollPane_2.setVisible(true);
				table_1.setVisible(true);
				lblNoD.setVisible(false);
				textField.setVisible(false);
				btnSubmit.setVisible(false);
				btnCancel.setVisible(false);
			}
			catch(Exception e)
			{
				System.out.println(e);
				
			}
			
			}});
		
		lblRoomType = new JLabel("Room Type");
		lblRoomType.setHorizontalAlignment(SwingConstants.CENTER);
		lblRoomType.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblRoomType.setBounds(227, 431, 111, 30);
		contentPane.add(lblRoomType);
		lblRoomType.setVisible(false);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(608, 419, 188, 76);
		contentPane.add(scrollPane_2);
		scrollPane_2.setVisible(false);
		
		table_1 = new JTable(){
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        }
	    };
		scrollPane_2.setViewportView(table_1);
		
		table_1.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	scrollPane_1.setVisible(true);
	        	txtpnCosts.setVisible(true);
				tcb.setVisible(true);
				lblRoomType.setVisible(true);
				scrollPane_2.setVisible(true);
				table_1.setVisible(true);
	        	lblNoD.setVisible(true);
	        	textField.setText("");
				textField.setVisible(true);
				btnSubmit.setVisible(true);
				btnCancel.setVisible(true);
	        }
	    });
		
		lblNoD = new JLabel("Number of days");
		lblNoD.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNoD.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoD.setBounds(398, 519, 166, 42);
		contentPane.add(lblNoD);
		lblNoD.setVisible(false);
		
		textField = new JTextField();
		textField.setBounds(594, 532, 142, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setVisible(false);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setVisible(false);
		
		btnSubmit.setBounds(437, 603, 148, 23);
		contentPane.add(btnSubmit);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				setVisible(false);
				Staff sf = new Staff();
				sf.setVisible(true);
			}
		});
		btnCancel.setVisible(false);
		btnCancel.setBounds(658, 603, 148, 23);
		contentPane.add(btnCancel);
		
		button = new JButton("<<<Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
			
				Staff staff = new Staff();
				staff.setVisible(true);
				setVisible(false);
			}
		});
		button.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		button.setBounds(517, 240, 319, 42);
		contentPane.add(button);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon("D:\\PBL\\medical-background-103.jpg"));
		label.setBounds(-285, 0, 1712, 705);
		contentPane.add(label);

		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				try
				{
					Connection con = connect();
				Statement smt = con.createStatement();
				int pno = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
				int digno=0,cost=0,totAmt=0;
				int rno = Integer.parseInt(table_1.getValueAt(table.getSelectedRow(), 0).toString());
				String query = "SELECT digno FROM diagnosis where rmallot = 'Not done' and rmreq ='Yes' and pno = '" + pno + "'";
				ResultSet rs = smt.executeQuery(query);
				if(rs.next())
				{
					digno = rs.getInt("digno");
				}
				Date date = Date.valueOf(LocalDate.now());
				int dno = Integer.parseInt(textField.getText());
				query = "update diagnosis set rmallot ='Done' where pno='" + pno + "'";
				smt.execute(query);
				query = "Select cost from room where rno = '" + rno + "'";
				rs = smt.executeQuery(query);
				if(rs.next())
				{
					cost = rs.getInt("cost");
					totAmt = cost*dno;
				}
				query = "Insert into ralloc (pno,digno,rno,radate,nodays,paystat,totamt) values (?,?,?,?,?,'Not Paid',?)";
				PreparedStatement ps = con.prepareStatement(query);
				ps.setInt(1, pno);
				ps.setInt(2, digno);
				ps.setInt(3, rno);
				ps.setDate(4, date);
				ps.setInt(5, dno);
				ps.setInt(6, totAmt);
				ps.execute();
				ps.close();

				tabVal();
				txtpnCosts.setVisible(false);
				lblNoD.setVisible(false);
				textField.setVisible(false);
				btnSubmit.setVisible(false);
				btnCancel.setVisible(false);
				scrollPane_2.setVisible(false);
				table_1.setVisible(false);
				tcb.setVisible(false);
				lblRoomType.setVisible(false);
				scrollPane_1.setVisible(false);
				}
				catch(SQLException e)
				{
					e.printStackTrace();
					
				}
				
			}
		});
		
	}
	
	void tabVal()
	{
		try
		{
			Connection con = connect();
			Statement smt = con.createStatement();
			String query = "SELECT d.pno,p.name,p.sex,p.age FROM diagnosis d, patient p WHERE d.rmallot = 'Not done' and rmreq ='Yes' and p.pno=d.pno";
			ResultSet rs = smt.executeQuery(query);
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
}
