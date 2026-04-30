import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.ScrollPane;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JFrameDesign extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSid;
	private JTextField txtSName;
	private JTextField txtSurName;
	private JTextField txtTel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
    
	DefaultTableModel model;
	int rowselect =-1;
	private JTable table;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameDesign frame = new JFrameDesign();
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
	public JFrameDesign() {
		setTitle("My Application");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 789, 641);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		

		
		JLabel lblNewLabel = new JLabel("Staff Information");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel.setBounds(279, 21, 210, 43);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Staff id");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 113, 59, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Staff Name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(10, 167, 91, 20);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Staff SurName");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3.setBounds(10, 225, 127, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Staff Tel");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_4.setBounds(10, 281, 101, 14);
		contentPane.add(lblNewLabel_4);
		
		txtSid = new JTextField();

		txtSid.setBounds(94, 112, 86, 20);
		contentPane.add(txtSid);
		txtSid.setColumns(10);
		
		txtSName = new JTextField();
		txtSName.setFont(new Font("TH SarabunPSK", Font.PLAIN, 18));
		txtSName.setBounds(113, 169, 120, 20);
		contentPane.add(txtSName);
		txtSName.setColumns(10);
		
		txtSurName = new JTextField();
		txtSurName.setFont(new Font("TH SarabunPSK", Font.PLAIN, 18));
		txtSurName.setBounds(135, 224, 145, 20);
		contentPane.add(txtSurName);
		txtSurName.setColumns(10);
		
		txtTel = new JTextField();
		txtTel.setBounds(94, 280, 145, 20);
		contentPane.add(txtTel);
		txtTel.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Sex");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5.setBounds(10, 328, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		JRadioButton rdMale = new JRadioButton("Male");
		rdMale.setSelected(true);
		buttonGroup.add(rdMale);
		rdMale.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdMale.setBounds(90, 326, 109, 23);
		contentPane.add(rdMale);
		
		JRadioButton rdFemale = new JRadioButton("Female");
		buttonGroup.add(rdFemale);
		rdFemale.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdFemale.setBounds(90, 352, 109, 23);
		contentPane.add(rdFemale);
		
		JComboBox cbDepartment = new JComboBox();
		cbDepartment.setModel(new DefaultComboBoxModel(new String[] {"Human Resources", "Marketing", "Sales", "Research and Development", "IT"}));
		cbDepartment.setBounds(125, 382, 130, 22);
		contentPane.add(cbDepartment);
		
		JComboBox cbUserId = new JComboBox();
		cbUserId.setBounds(125, 436, 130, 22);
		contentPane.add(cbUserId);
		MySQLConnect db= new MySQLConnect();
		String sql = "select* from user where user_id not in (select user_id from staff)";
		ResultSet rec =db.query(sql);
		int row=0;
		try {
			while((rec!=null)&&rec.next()) {
				cbUserId.addItem(rec.getString("user_id"));
				row++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JButton btnSave = new JButton("Save");
		btnSave.setEnabled(false);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			   
				String sid = txtSid.getText();
				String tel = txtTel.getText();
				try {
					int sidd = Integer.parseInt(sid);
					int tell = Integer.parseInt(tel);
					String fname =txtSName.getText();
					String surname =txtSurName.getText();
				
					String dept = cbDepartment.getSelectedItem().toString();
					String uid = cbUserId.getSelectedItem().toString();
					String gender;
					if(rdFemale.isSelected()) {
						gender="Female";}
						else
							{gender = "Male";}
						
					if(sid!=null) {
						if(sid.isEmpty()) {
							JOptionPane.showMessageDialog(null, "Invalid Staff ID","Alert",JOptionPane.ERROR_MESSAGE);
						}else{
							JOptionPane.showMessageDialog(null,"Staff Id: " +sid+" Staff Name: "+fname+" Staff SurName: "+surname+" Tel: "+tel+" Gender: "+gender+" Department: "+dept,"Staff Information",JOptionPane.INFORMATION_MESSAGE);
				        }
					 }
					
					//Connect DB
					MySQLConnect db = new MySQLConnect();
					
					String sql = "INSERT INTO staff (sid, sfname, slname, stel, user_id) "
						+"values ("+sid+",'"
						+fname+"','"
						+surname+"',"
						+tel+","
						+uid+") ";
						System.out.println(sql);
						int rows= db.update(sql);	
						if(rows==1) {
							String[] options = {"OK"};
						JPanel panel = new JPanel();
						JLabel lbl = new JLabel("record Inserted Successfully");
						panel.add(lbl);
						int selectedOption = JOptionPane.showOptionDialog(null, panel, "Add Successfully", JOptionPane.NO_OPTION,JOptionPane.INFORMATION_MESSAGE,null,options,options[0]);
						if(selectedOption ==0) {
							// showData();	
						     model.addRow(new Object[]{sid,fname,surname,tel,uid});
						}
						}
						db.close();
						cbUserId.removeItem(uid);
						clearform();		
						}
	
              catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(null, "Invalid number:Staff id and Tel must be numeric","Alert",JOptionPane.ERROR_MESSAGE);
				}
	 
					
			 }
			
			private void clearform() {
				// TODO Auto-generated method stub
				txtSid.setText("");
				txtSName.setText("");
				txtSurName.setText("");
				txtTel.setText("");
				rdMale.setSelected(true);
				cbDepartment.setSelectedIndex(0);	
				cbUserId.setSelectedIndex(0);
				rowselect = -1;
			}
			
			
		});
		btnSave.setIcon(new ImageIcon("icon\\icon1.png"));
		btnSave.setBounds(37, 507, 117, 68);
		contentPane.add(btnSave);
		
		txtSid.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(txtSid.getText().length()>0) {
					btnSave.setEnabled(true);
				}else {
					btnSave.setEnabled(false);
				}
			}
		});
		
		JButton btnExit = new JButton("Exit");
		btnExit.setIcon(new ImageIcon("icon\\exit.png"));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		btnExit.setBounds(566, 519, 145, 56);
		contentPane.add(btnExit);
		
		
		
		
		
		JLabel lblNewLabel_6 = new JLabel("Department");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_6.setBounds(10, 377, 109, 23);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("User ID");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_7.setBounds(10, 433, 91, 23);
		contentPane.add(lblNewLabel_7);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setEnabled(false);
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rowselect < 0) {
					JOptionPane.showMessageDialog(null,"Please Select row to delete");
				}else {
				String staff_id = table.getValueAt(rowselect, 0).toString();	
				String uid = table.getValueAt(rowselect, 4).toString();	
				System.out.println("Staff id: "+staff_id);
				Object[] options= {"Yes","no"};
				int n = JOptionPane.showOptionDialog(null, "Do you want to delete this row?", "Confirm Delete", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
				if(n==0) {//meaning confirm is yes
				
					DeleteData(staff_id);
					model.removeRow(rowselect);
					cbUserId.addItem(uid);				
				}
				
				}
				
				
			}
		});
		btnRemove.setIcon(new ImageIcon("icon\\icon2.png"));
		btnRemove.setBounds(184, 507, 137, 68);
		contentPane.add(btnRemove);
		
		//Update Button
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setEnabled(false);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sid = table.getValueAt(rowselect, 0).toString();
				String uid = table.getValueAt(rowselect, 4).toString();
				UpdateDialog update = new UpdateDialog(Integer.valueOf(sid),Integer.valueOf(uid));
				update.setModal(true);	
				update.setVisible(true);
			}
			
		});
		btnUpdate.setIcon(new ImageIcon("icon\\icon3.png"));
		btnUpdate.setBounds(344, 513, 164, 62);
		contentPane.add(btnUpdate);
		
		
		
		
		// create Data Table
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(table.getSelectedRow());
				rowselect = table.getSelectedRow();
                btnRemove.setEnabled(true);
                btnUpdate.setEnabled(true);
		        }
		});
		
		table.setBounds(290, 135, 423, 293);
		JScrollPane scrollPane = new JScrollPane(); // Wrap the table in a JScrollPane
		scrollPane.setBounds(290, 135, 423, 293);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(table);
		


		//table model
		
		model=(DefaultTableModel)table.getModel();
		 
	    model.addColumn("Staff ID");
	    model.addColumn("Staff Name");
	    model.addColumn("Staff SurName");
	    model.addColumn("Staff Tel");
	    model.addColumn("User Id");
		
	      
	      //get data from db
	      showData();
	     
	      }
	      
	 

private void showData(){


    MySQLConnect db1= new MySQLConnect();
    String sql1 = "select * from staff";
    ResultSet rec1 = db1.query(sql1);
    int row1=0;
    try {
		while ((rec1!=null) && rec1.next())
		  {                              
			model.addRow(new Object[0]);
			model.setValueAt(rec1.getString("sid"), row1, 0);
			model.setValueAt(rec1.getString("sfname"),row1,1);
			model.setValueAt(rec1.getString("slname"),row1,2);
			model.setValueAt(rec1.getString("stel"),row1,3);
			model.setValueAt(rec1.getString("user_id"),row1,4);
			//  System.out.println(rec1.getString("user_id"));
			row1++;
		  }
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
}

//Delete Data
private void DeleteData(String staff_id) {
	MySQLConnect db= new MySQLConnect();
	String sql = "Delete from staff where sid= "+staff_id;
	db.update(sql);
	db.close();
	JOptionPane.showMessageDialog(null, "Delete Staff Successfully");
}


}