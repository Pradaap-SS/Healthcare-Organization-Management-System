import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class LogIn
{
	JFrame frame;
	private JTextField t1;
	private JPasswordField t2;
	private JLabel lblInvalidIdOr;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIn window = new LogIn();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LogIn() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setSize(650, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblWelcome = new JLabel("Welcome To");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Sitka Heading", Font.BOLD | Font.ITALIC, 30));
		lblWelcome.setBounds(589, 136, 183, 68);
		frame.getContentPane().add(lblWelcome);
		
		JLabel lblBengaluruHealthCare = new JLabel("RSMJ Health Care");
		lblBengaluruHealthCare.setFont(new Font("Tempus Sans ITC", Font.BOLD, 35));
		lblBengaluruHealthCare.setBounds(518, 211, 323, 59);
		frame.getContentPane().add(lblBengaluruHealthCare);
		
		JLabel lblHospital = new JLabel("HOSPITAL");
		lblHospital.setHorizontalAlignment(SwingConstants.CENTER);
		lblHospital.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblHospital.setBounds(607, 268, 148, 59);
		frame.getContentPane().add(lblHospital);
		
		JLabel lblAuthentication = new JLabel("Log In");
		lblAuthentication.setHorizontalAlignment(SwingConstants.CENTER);
		lblAuthentication.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblAuthentication.setBounds(635, 338, 91, 31);
		frame.getContentPane().add(lblAuthentication);
		
		lblInvalidIdOr = new JLabel("Invalid Id or Password");
		lblInvalidIdOr.setHorizontalAlignment(SwingConstants.CENTER);
		lblInvalidIdOr.setBounds(581, 528, 200, 50);
		frame.getContentPane().add(lblInvalidIdOr);
		lblInvalidIdOr.setVisible(false);
		
		JLabel lblLogIn = new JLabel("Log In");
		lblLogIn.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLogIn.setBounds(534, 394, 89, 22);
		frame.getContentPane().add(lblLogIn);
		
		t1 = new JTextField();
		t1.setBounds(633, 394, 148, 22);
		frame.getContentPane().add(t1);
		t1.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(534, 427, 89, 20);
		frame.getContentPane().add(lblPassword);
		
		t2 = new JPasswordField();
		t2.setBounds(633, 427, 148, 20);
		frame.getContentPane().add(t2);
		t2.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				String enter = t1.getText();
				String pass = new String(t2.getPassword());
				
				String Login="admin";
				String Password="admin";
				
				if(enter.equals(Login) && pass.equals(Password))
				{
					YouAre youare= new YouAre();
					youare.setVisible(true);	
					frame.setVisible(false);
				}
				
				else
				{
					lblInvalidIdOr.setVisible(true);
				}
				
			}
		});
		btnSubmit.setBounds(636, 482, 89, 23);
		frame.getContentPane().add(btnSubmit);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("D:\\PBL\\medical-background-103.jpg"));
		lblNewLabel.setBounds(-359, -46, 1721, 751);
		frame.getContentPane().add(lblNewLabel);
		
		
	}
}
