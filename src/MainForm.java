import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;

public class MainForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private int userName;

	/**
	 * Create the frame.
	 */
	public MainForm(int userName) {
		this.userName = userName;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 738, 554);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMainTitle = new JLabel("New label");
		lblMainTitle.setForeground(new Color(255, 255, 51));
		lblMainTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblMainTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblMainTitle.setBounds(185, 122, 359, 32);
		contentPane.add(lblMainTitle);
		
		lblMainTitle.setText("ยินดีต้อนรับ User: "+ userName);
		
		JLabel lblName = new JLabel("New label");
		lblName.setForeground(new Color(255, 255, 0));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblName.setBounds(240, 187, 199, 32);
		contentPane.add(lblName);
		MySQLConnect db = new MySQLConnect();
		String sql = "select sfname, slname from staff where user_id = "+userName;
		ResultSet rec =db.query(sql);
		int row=0;
		String sfname=null;
		String slname=null;
		
			try {
				while((rec!=null)&&rec.next()) { 
				sfname	 = rec.getString("sfname");
			    slname = rec.getString("slname");
					row++;
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		 lblName.setText("คุณ "+sfname+" "+slname);
		 
		 JMenuBar menuBar = new JMenuBar();
		 menuBar.setBounds(0, 0, 722, 22);
		 contentPane.add(menuBar);
		 
		 JMenu mnNewMenu = new JMenu("จัดการข้อมูล");
		 mnNewMenu.setFont(new Font("TH SarabunPSK", Font.BOLD, 24));
		 menuBar.add(mnNewMenu);
		 
		 JMenuItem mnuStaff = new JMenuItem("จัดการเจ้าหน้าที่");
		 mnuStaff.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		JFrameDesign jframe = new JFrameDesign();
				 jframe.setVisible(true);
		 		
		 		
		 	}
		 });
		 mnuStaff.setFont(new Font("TH SarabunPSK", Font.BOLD, 22));
		 mnNewMenu.add(mnuStaff);
		 
		 JMenuItem mnuBorrower = new JMenuItem("จัดการผู้ยืม");
		 mnuBorrower.setFont(new Font("TH SarabunPSK", Font.BOLD, 22));
		 mnNewMenu.add(mnuBorrower);
		 
		 JMenuItem mnuUser = new JMenuItem("จัดการ User");
		 mnuUser.setFont(new Font("TH SarabunPSK", Font.BOLD, 22));
		 mnNewMenu.add(mnuUser);
		 
		 JMenuItem mnuBook = new JMenuItem("จัดการหนังสือ");
		 mnuBook.setFont(new Font("TH SarabunPSK", Font.BOLD, 22));
		 mnNewMenu.add(mnuBook);
		 
		 JMenu mnNewMenu_2 = new JMenu("ยืม-คืน");
		 mnNewMenu_2.setFont(new Font("TH SarabunPSK", Font.BOLD, 24));
		 menuBar.add(mnNewMenu_2);
		 
		 JMenuItem mnuBorrow = new JMenuItem("ยืมหนังสือ");
		 mnuBorrow.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
				BorrowFrame brFrame = new BorrowFrame();
				 brFrame.setVisible(true);
		 		
		 	}
		 });
		 mnuBorrow.setFont(new Font("TH SarabunPSK", Font.BOLD, 24));
		 mnNewMenu_2.add(mnuBorrow);
		 
		 JMenuItem mnuReturn = new JMenuItem("คืนหนังสือ");
		 mnuReturn.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		//ReturnFrame rf = new ReturnFrame();
		 		//rf.setVisible(true);
		 	}
		 });
		 mnuReturn.setFont(new Font("TH SarabunPSK", Font.BOLD, 24));
		 mnNewMenu_2.add(mnuReturn);
		 
		 JMenu mnuExit = new JMenu("ออกจากระบบ");
		 menuBar.add(mnuExit);
		 mnuExit.setFont(new Font("TH SarabunPSK", Font.BOLD, 24));
		 
		 JMenuItem mntmNewMenuItem_4 = new JMenuItem("ออกจากระบบ");
		 mntmNewMenuItem_4.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
		 		int confirm = JOptionPane.showConfirmDialog(null, "Do you want to Exit","Warning",JOptionPane.YES_NO_OPTION);
		 		if(confirm == JOptionPane.YES_NO_OPTION) {
		 			
				 	System.exit(0);
		 		}
		 		
		 		
		 	}
		 });
		 mntmNewMenuItem_4.setFont(new Font("TH SarabunPSK", Font.BOLD, 24));
		 mnuExit.add(mntmNewMenuItem_4);
		 
		 JLabel lblNewLabel = new JLabel("");
		 lblNewLabel.setIcon(new ImageIcon("icon\\bookbg.jpg"));
		 lblNewLabel.setBounds(0, 21, 722, 494);
		 contentPane.add(lblNewLabel);
	}
}
