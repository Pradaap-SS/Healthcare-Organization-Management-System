import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;

public class Billing extends JFrame {

	private JPanel contentPane;
	private JTextField tf;
	private JLabel lab1,lab2,lab3;
	private JButton btnSubmit, btnSub, btnCancel;
	private JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9,l10, mode;
	private JScrollPane border1, border2;
	private JComboBox comboBox;
	int bno, dfee, rfee,tax, total, pno;
	private JButton button;
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Billing frame = new Billing();
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
	public Billing() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 650, 600);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setSize(650, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lab1 = new JLabel("Billing");
		lab1.setHorizontalAlignment(SwingConstants.CENTER);
		lab1.setFont(new Font("Times New Roman", Font.PLAIN, 28));
		lab1.setBounds(608, 36, 141, 44);
		contentPane.add(lab1);
		
		lab2 = new JLabel("Patient ID");
		lab2.setBounds(573, 97, 89, 14);
		contentPane.add(lab2);
		
		tf = new JTextField();
		tf.setBounds(717, 94, 86, 20);
		contentPane.add(tf);
		tf.setColumns(10);
		
		lab3= new JLabel();
		lab3.setHorizontalAlignment(SwingConstants.CENTER);
		lab3.setBounds(523, 209, 315, 35);
		contentPane.add(lab3);
		
		btnSubmit = new JButton("Get Details");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				pno = Integer.parseInt(tf.getText());
				try
				{
					lab3.setText("");
					Connection con = connect();
					CallableStatement cs = con.prepareCall("{call bill_proc(?)}");
					cs.setInt(1,pno);
					cs.execute();
					
					String query = "Select bno,dfee,rfee,tax,total from bill where pno = " + pno + " and pmode = 'N/A'";
					Statement smt = con.createStatement();
					ResultSet rs = smt.executeQuery(query);
					if(rs.next())
					{
						button.setVisible(false);
						lab1.setVisible(false);
						lab2.setVisible(false);
						tf.setVisible(false);
						btnSubmit.setVisible(false);
						bno = rs.getInt("bno");
						dfee = rs.getInt("dfee");
						rfee = rs.getInt("rfee");
						tax = rs.getInt("tax");
						total = rs.getInt("total");
						l1.setVisible(true);
						l2.setVisible(true);
						l3.setVisible(true);
						l4.setVisible(true);
						l5.setVisible(true);
						l6.setText("" + bno);
						l7.setText(""+ dfee);
						l8.setText("" + rfee);
						l9.setText("" + tax);
						l10.setText("" + total);
						border1.setVisible(true);
						border2.setVisible(true);
						mode.setVisible(true);
						comboBox.setVisible(true);
						btnSub.setVisible(true);
						btnCancel.setVisible(true);
					}
					else
					{
						lab3.setText("Not Pending Payments For Patient Number " + pno);
					}
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
		});
		btnSubmit.setBounds(531, 145, 131, 23);
		contentPane.add(btnSubmit);
		
		l1 = new JLabel("Bill Number:");
		l1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		l1.setHorizontalAlignment(SwingConstants.RIGHT);
		l1.setBounds(523, 283, 157, 35);
		contentPane.add(l1);
		l1.setVisible(false);
		
		l2 = new JLabel("Doctor Fee:");
		l2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		l2.setHorizontalAlignment(SwingConstants.RIGHT);
		l2.setBounds(523, 323, 157, 35);
		contentPane.add(l2);
		l2.setVisible(false);
		
		l3 = new JLabel("Room Fee:");
		l3.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		l3.setHorizontalAlignment(SwingConstants.RIGHT);
		l3.setBounds(523, 365, 157, 35);
		contentPane.add(l3);
		l3.setVisible(false);
		
		l4 = new JLabel("Tax:");
		l4.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		l4.setHorizontalAlignment(SwingConstants.RIGHT);
		l4.setBounds(523, 401, 157, 35);
		contentPane.add(l4);
		l4.setVisible(false);
		
		l5 = new JLabel("Total:");
		l5.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		l5.setHorizontalAlignment(SwingConstants.RIGHT);
		l5.setBounds(523, 441, 157, 35);
		contentPane.add(l5);
		l5.setVisible(false);
		
		l6 = new JLabel();
		l6.setHorizontalAlignment(SwingConstants.LEFT);
		l6.setFont(new Font("Times New Roman", Font.BOLD, 16));
		l6.setBounds(705, 283, 157, 35);
		contentPane.add(l6);
		
		l7 = new JLabel();
		l7.setHorizontalAlignment(SwingConstants.LEFT);
		l7.setFont(new Font("Times New Roman", Font.BOLD, 16));
		l7.setBounds(705, 323, 157, 35);
		contentPane.add(l7);
		
		l8 = new JLabel();
		l8.setHorizontalAlignment(SwingConstants.LEFT);
		l8.setFont(new Font("Times New Roman", Font.BOLD, 16));
		l8.setBounds(705, 365, 157, 35);
		contentPane.add(l8);
		
		l9 = new JLabel();
		l9.setHorizontalAlignment(SwingConstants.LEFT);
		l9.setFont(new Font("Times New Roman", Font.BOLD, 16));
		l9.setBounds(705, 401, 157, 35);
		contentPane.add(l9);
		
		l10 = new JLabel();
		l10.setHorizontalAlignment(SwingConstants.LEFT);
		l10.setFont(new Font("Times New Roman", Font.BOLD, 16));
		l10.setBounds(705, 441, 157, 35);
		contentPane.add(l10);
		
		border1 = new JScrollPane();
		border1.setBounds(523, 282, 175, 194);
		contentPane.add(border1);
		border1.setVisible(false);
		
		border2 = new JScrollPane();
		border2.setBounds(523, 282, 339, 194);
		contentPane.add(border2);
		border2.setVisible(false);
		
		mode = new JLabel("Choose Payment Mode:");
		mode.setHorizontalAlignment(SwingConstants.LEFT);
		mode.setFont(new Font("Times New Roman", Font.ITALIC, 16));
		mode.setBounds(490, 523, 175, 35);
		contentPane.add(mode);
		mode.setVisible(false);
		
		String modeList[] = {"Cash", "Card", "Cheque", "PayTM", "Wallet", "DD", "NEFT"};
		comboBox = new JComboBox(modeList);
		comboBox.setBounds(701, 525, 131, 35);
		contentPane.add(comboBox);
		comboBox.setVisible(false);
		
		btnSub = new JButton("Submit");
		btnSub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{ 	
				String mVal = comboBox.getSelectedItem().toString();
				try
				{
				Connection con=connect();
				String query="update diagnosis set paystat ='Paid' where pno="+pno;
				Statement smt=con.createStatement();
				smt.execute(query);
				
				query="update ralloc set paystat ='Paid' where pno="+pno;
				smt.execute(query);
				
				query="update bill set pmode ='"+mVal+"' where bno=" +bno;
				smt.execute(query);
				
				button.setVisible(true);
				lab1.setVisible(true);
				lab2.setVisible(true);
				tf.setVisible(true);
				btnSubmit.setVisible(true);
				l1.setVisible(false);
				l2.setVisible(false);
				l3.setVisible(false);
				l4.setVisible(false);
				l5.setVisible(false);
				tf.setText("");
				l6.setText("");
				l7.setText("");
				l8.setText("");
				l9.setText("");
				l10.setText("");
				border1.setVisible(false);
				border2.setVisible(false);
				mode.setVisible(false);
				comboBox.setVisible(false);
				btnSub.setVisible(false);
				btnCancel.setVisible(false);
				}
				
				catch(SQLException e)
				{
					e.printStackTrace();
				}
		
			}
		});
		btnSub.setBounds(502, 634, 131, 23);
		contentPane.add(btnSub);
		btnSub.setVisible(false);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				try
				{
				Connection con=connect();
				String query="delete from bill where bno="+bno;
				Statement smt=con.createStatement();
				smt.execute(query);
				YouAre youare=new YouAre();
				youare.setVisible(true);
				setVisible(false);
				}
				catch(SQLException e)
				{
					System.out.println(e);
				}
		
				
			}
		});
		btnCancel.setBounds(716, 634, 131, 23);
		contentPane.add(btnCancel);
	
		button = new JButton("<<<Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				setVisible(false);
				YouAre ya = new YouAre();
				ya.setVisible(true);
				
				
			}
		});
		button.setBounds(714, 145, 131, 23);
		contentPane.add(button);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon("D:\\PBL\\medical-background-103.jpg"));
		label.setBounds(-318, 0, 1680, 705);
		contentPane.add(label);
		btnCancel.setVisible(false);
	}
}
