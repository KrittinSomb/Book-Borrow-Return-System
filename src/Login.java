import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 496, 307);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("User Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(40, 72, 123, 31);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Library System");
		lblNewLabel.setBounds(153, 11, 152, 25);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setBounds(40, 149, 108, 22);
		contentPane.add(lblNewLabel_2);
		
		txtUser = new JTextField();
		txtUser.setBounds(173, 80, 152, 31);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(173, 149, 152, 31);
		contentPane.add(passwordField_1);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = txtUser.getText();
				char[] passwordChars = passwordField_1.getPassword();
				String password = new String(passwordChars);
				MySQLConnect db = new MySQLConnect();
				String sql = "select* from user where user_id = "+userName+" and password = '"+password+"'";
				 System.out.println(sql);
				ResultSet rec =db.query(sql);
				int row=0;
			 
					try {
						while((rec!=null)&&rec.next()) { 
							row++;
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				 System.out.println(row);
				
			 if (row > 0) {
				    dispose();
					MainForm mf = new MainForm(Integer.valueOf(userName));
					mf.setTitle("Welcome");
					mf.setVisible(true);
			 }else {
				 
				 JOptionPane.showMessageDialog(null, "Invalid UserName and Password","Warning",JOptionPane.ERROR_MESSAGE);
				 
			 }
				
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(181, 216, 144, 41);
		contentPane.add(btnNewButton);
		
		JCheckBox chkPasswd = new JCheckBox("show password");
		chkPasswd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chkPasswd.isSelected()) 
				{
				 	passwordField_1.setEchoChar((char)0);
				}else {
					passwordField_1.setEchoChar('*');
				}
				
				
			}
		});
		chkPasswd.setBounds(23, 191, 125, 23);
		contentPane.add(chkPasswd);
	
	}
}
