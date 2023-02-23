import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;


public class YouAre extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					YouAre frame = new YouAre();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public YouAre() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 443, 330);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setSize(650, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblYouAre = new JLabel("You Are?");
		lblYouAre.setHorizontalAlignment(SwingConstants.CENTER);
		lblYouAre.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblYouAre.setBounds(516, 158, 330, 50);
		contentPane.add(lblYouAre);
		
		JButton btnMedicalSupport = new JButton("Medical Support");
		btnMedicalSupport.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnMedicalSupport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				Staff staff = new Staff();
				staff.setVisible(true);
				setVisible(false);
				
			}
		});
		btnMedicalSupport.setBounds(516, 252, 330, 50);
		contentPane.add(btnMedicalSupport);
		
		JButton btnDoctor = new JButton("Doctor");
		btnDoctor.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			Doctor doc = new Doctor();
			doc.setVisible(true);
			setVisible(false);
			}
		});
		btnDoctor.setBounds(516, 345, 330, 50);
		contentPane.add(btnDoctor);
		
		JButton btnBilling = new JButton("Billing");
		btnBilling.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnBilling.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Billing bill = new Billing();
				bill.setVisible(true);
				setVisible(false);
			}
		});
		btnBilling.setBounds(512, 442, 330, 50);
		contentPane.add(btnBilling);
		
		JButton btnNewButton = new JButton("Log Out");
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LogIn window = new LogIn();
				window.frame.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setBounds(512, 550, 330, 50);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("D:\\PBL\\medical-background-103.jpg"));
		lblNewLabel.setBounds(-325, -100, 1687, 805);
		contentPane.add(lblNewLabel);
		
	}
}
