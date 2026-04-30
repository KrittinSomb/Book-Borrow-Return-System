import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateDialog extends JDialog {

	private static final long serialVersionUID = 1L;
    private int sid;
    private int uid;
    private JTextField txtSid;
    private JTextField txtSFName;
    private JTextField txtSurName;
    private JTextField txtTel;

	/**
	 * Create the dialog.
	 */
	public UpdateDialog(int sid, int uid) {
		setTitle("Staff Information");
		this.sid = sid;
		this.uid = uid;
	    System.out.println("Sid: "+sid +"  Uid: "+uid); 	
		setBounds(100, 100, 685, 556);
		getContentPane().setLayout(null);
		

		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setIcon(new ImageIcon("icon\\cancel.png"));
		btnCancel.setBounds(254, 425, 149, 71);
		getContentPane().add(btnCancel);
		
		JLabel lblNewLabel = new JLabel("Staff Id");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(37, 61, 81, 43);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Staff Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(37, 137, 106, 33);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Staff SurName");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setBounds(37, 218, 138, 27);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Staff Tel");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3.setBounds(37, 276, 127, 27);
		getContentPane().add(lblNewLabel_3);
		
		JComboBox cmbUserId = new JComboBox();
		cmbUserId.setBounds(198, 340, 171, 33);
		getContentPane().add(cmbUserId);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println("SID in save button "+sid);
		 	    int user_id = Integer.valueOf((String)cmbUserId.getSelectedItem());
		 	    //System.out.println("user id in save button "+user_id);
		 	    String fname = txtSFName.getText();
		 	    String surname = txtSurName.getText();
		 	    String tel =txtTel.getText();
		 	    
		 	   try {
		 	    	int tell = Integer.parseInt(tel);
		 	    	MySQLConnect db = new MySQLConnect();
		 	    	String sql = "UPDATE staff "+
		 	    	"SET sfname = '"+fname+"',"+
		 	    	" slname = '"+surname+"',"+
		 	    	" stel = "+tel+","+
		 	    	" user_id = "+user_id+
		 	    	" WHERE sid= "+sid;
		 	    	//System.out.println(sql);
		 	    	db.update(sql);
		 	    	db.close();
		 	    	String[]options = {"OK"};
		 	    	JPanel panel = new JPanel();
		 	    	JLabel lbl = new JLabel("Record Updated Successfully");
		 	    	panel.add(lbl);
		 	    	int selectedOption= JOptionPane.showOptionDialog(null,panel, "Update Successfully", JOptionPane.NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		 	    	if (selectedOption ==0) {
		 	    		dispose();
		 	    		JFrameDesign frame= new JFrameDesign();
		 	    		frame.setVisible(true);
		 	    	}
		 	    	
		 	    	
		 	    }
		 	    catch(NumberFormatException ex){
		 	    JOptionPane.showMessageDialog(null,"Invalid telephone number, it must be numeric","Warning",JOptionPane.ERROR_MESSAGE);
		 	    	
		 	    }
					   
		 	    
			}
		});
		btnSave.setIcon(new ImageIcon("icon\\icon1.png"));
		btnSave.setBounds(87, 425, 117, 71);
		getContentPane().add(btnSave);
		
		
		JLabel lblNewLabel_4 = new JLabel("User Id");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_4.setBounds(37, 340, 127, 27);
		getContentPane().add(lblNewLabel_4);
		
		txtSid = new JTextField();
		txtSid.setEnabled(false);
		txtSid.setBounds(198, 76, 149, 28);
		getContentPane().add(txtSid);
		txtSid.setColumns(10);
		
		txtSFName = new JTextField();
		txtSFName.setBounds(198, 147, 149, 33);
		getContentPane().add(txtSFName);
		txtSFName.setColumns(10);
		
		txtSurName = new JTextField();
		txtSurName.setBounds(200, 218, 147, 27);
		getContentPane().add(txtSurName);
		txtSurName.setColumns(10);
		
		txtTel = new JTextField();
		txtTel.setBounds(198, 283, 158, 27);
		getContentPane().add(txtTel);
		txtTel.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Staff Information");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_5.setBounds(225, 10, 220, 33);
		getContentPane().add(lblNewLabel_5);
		
		//Select data to show before updating
		MySQLConnect db= new MySQLConnect();
		
		//add combobox
		String sql1 = "select * from user where user_id not in (select user_id from staff) "
				+ "union select * from user where user_id= "+uid;
		
		ResultSet rec = db.query(sql1);
		int row=0;
		try {
			while((rec!=null)&&rec.next()) {
			cmbUserId.addItem(rec.getString("user_id"));	
			row++;	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//add textbox
		
		String sql = "select * from staff where sid= "+sid;
		ResultSet rec1 = db.query(sql);
		if (rec1!=null) {
			try {
				rec1.next();
				txtSid.setText(rec1.getString("sid"));
				txtSFName.setText(rec1.getString("sfname"));
				txtSurName.setText(rec1.getString("slname"));
				txtTel.setText(rec1.getString("stel"));
				cmbUserId.setSelectedItem(rec1.getString("user_id"));
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} //rec1!=null
		 
	}
}
