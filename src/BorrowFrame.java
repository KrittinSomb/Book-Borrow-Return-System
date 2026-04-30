import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.ScrollPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class BorrowFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtBorrowerId;
	private JTextField txtBorrowerName;
	private JTextField txtBookId;
	private JTextField txtBookName;
	private JTable table;

	DefaultTableModel model;
	int rowselect = -1;
	private JTextField txtboId;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BorrowFrame frame = new BorrowFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/

	/**
	 * Create the frame.
	 */
	public BorrowFrame() {
		setTitle("ยืมหนังสือ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 979, 664);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("รหัสผู้ยืม");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(20, 30, 75, 25);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("ชื่อผู้ยืม");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1.setBounds(20, 109, 75, 25);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("รหัสหนังสือ");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1_1.setBounds(20, 189, 93, 25);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("ชื่อหนังสือ");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1_1_1.setBounds(20, 262, 93, 25);
		contentPane.add(lblNewLabel_1_1_1);

		txtBorrowerId = new JTextField();
		txtBorrowerId.setBounds(126, 35, 117, 20);
		contentPane.add(txtBorrowerId);
		txtBorrowerId.setColumns(10);

		txtBorrowerName = new JTextField();
		txtBorrowerName.setEditable(false);
		txtBorrowerName.setBounds(130, 114, 181, 20);
		contentPane.add(txtBorrowerName);
		txtBorrowerName.setColumns(10);

		txtBookId = new JTextField();
		txtBookId.setBounds(130, 194, 86, 20);
		contentPane.add(txtBookId);
		txtBookId.setColumns(10);

		txtBookName = new JTextField();
		txtBookName.setEditable(false);
		txtBookName.setFont(new Font("TH SarabunPSK", Font.PLAIN, 20));
		txtBookName.setBounds(130, 267, 258, 35);
		contentPane.add(txtBookName);
		txtBookName.setColumns(10);

		JDateChooser BdateChooser = new JDateChooser();
		BdateChooser.setBounds(104, 338, 233, 20);
		contentPane.add(BdateChooser);

		JDateChooser RdateChooser = new JDateChooser();
		RdateChooser.setBounds(125, 409, 212, 20);
		contentPane.add(RdateChooser);

		JComboBox cmbStaff = new JComboBox();
		cmbStaff.setBounds(212, 471, 158, 22);
		contentPane.add(cmbStaff);
		MySQLConnect db = new MySQLConnect();
		String sql = "select * from staff";
		ResultSet rec = db.query(sql);
		int row = 0;
		try {
			while ((rec != null) && rec.next()) {
				cmbStaff.addItem(rec.getString("sid"));
				row++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JButton btnBorrowSearch = new JButton("ค้นหา");
		btnBorrowSearch.setEnabled(false);
		btnBorrowSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int br_id = Integer.valueOf(txtBorrowerId.getText());
				MySQLConnect db = new MySQLConnect();
				String sql = "select bfname, blname from borrower where bid=  " + br_id;
				ResultSet rec = db.query(sql);
				int row = 0;

				try {
					while ((rec != null) && rec.next()) {
						txtBorrowerName.setText(rec.getString("bfname") + ' ' + rec.getString("blname"));
						row++;
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (row == 0) {
					JOptionPane.showMessageDialog(null, "User Not Found", "Warning", JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		btnBorrowSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBorrowSearch.setBounds(281, 34, 89, 23);
		contentPane.add(btnBorrowSearch);

		txtBorrowerId.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				btnBorrowSearch.setEnabled(true);
				txtBorrowerName.setText("");
			}
		});

		JButton btnBookSearch = new JButton("ค้นหา");
		btnBookSearch.setEnabled(false);
		btnBookSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int book_id = Integer.valueOf(txtBookId.getText());
				MySQLConnect db = new MySQLConnect();
				String sql = "select * from book where book_id=  " + book_id;
				ResultSet rec = db.query(sql);
				int row = 0;
				String bStatus = "not available";
				String na = "not available";
				try {
					while ((rec != null) && rec.next()) {
						bStatus = rec.getString("status");
						System.out.println(bStatus);
						if (bStatus.equals(na)) {
							JOptionPane.showMessageDialog(null, "Book Not available", "Warning",
									JOptionPane.INFORMATION_MESSAGE);
						} else
							txtBookName.setText(rec.getString("book_name"));

						row++;
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if (row == 0) {
					JOptionPane.showMessageDialog(null, "Book Not Found", "Warning", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnBookSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBookSearch.setBounds(281, 193, 89, 23);
		contentPane.add(btnBookSearch);

		JButton btnAdd = new JButton("เพิ่มรายการ");
		btnAdd.setEnabled(false);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtBorrowerId.setEditable(false);
				btnBorrowSearch.setEnabled(false);
				BdateChooser.setEnabled(false);
				RdateChooser.setEnabled(false);
				cmbStaff.setEnabled(false);
				int book_id = Integer.valueOf(txtBookId.getText());
				String book_name = txtBookName.getText();

				Date selectedBDate = BdateChooser.getDate();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String formattedBDate = sdf.format(selectedBDate);

				Date selectedRDate = RdateChooser.getDate();
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
				String formattedRDate = sdf1.format(selectedRDate);

				LocalDate currentDate = LocalDate.now();
				LocalDate bDate = selectedBDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				if (selectedRDate.after(selectedBDate) && (currentDate.isAfter(bDate) || currentDate.equals(bDate))) {
					model.addRow(new Object[] { book_id, book_name, formattedBDate, formattedRDate });

				} else {
					JOptionPane.showMessageDialog(null,
							"Return Date must be after Borrow Date or Borrow Date must be before current date", "Alert",
							JOptionPane.ERROR_MESSAGE);
					BdateChooser.setEnabled(true);
					RdateChooser.setEnabled(true);

				}

			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAdd.setBounds(51, 543, 103, 39);
		contentPane.add(btnAdd);

		txtBookId.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				btnAdd.setEnabled(true);
				btnBookSearch.setEnabled(true);
				txtBookName.setText("");

			}
		});

		JButton btnRemove = new JButton("ลบรายการ");
		btnRemove.setEnabled(false);
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (rowselect < 0) {
					JOptionPane.showMessageDialog(null, "Please Select row to delete");
				} else {

					Object[] options = { "Yes", "No" };
					int n = JOptionPane.showOptionDialog(null, "Do you want to delete this row?", "Confirm Delete",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
					if (n == 0) {// meaning confirm is yes
						model.removeRow(rowselect);
						if (model.getRowCount() == 0) {
							btnRemove.setEnabled(false);
						}
					}
				}

			}
		});
		btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRemove.setBounds(212, 543, 117, 39);
		contentPane.add(btnRemove);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(410, 116, 457, 290);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rowselect = table.getSelectedRow();
				btnRemove.setEnabled(true);
				System.out.println(rowselect);

			}
		});
		table.setFont(new Font("TH SarabunPSK", Font.BOLD, 16));
		table.getTableHeader().setFont(new Font("TH SarabunPSK", Font.BOLD, 16));
		scrollPane.setViewportView(table);

		// table model

		model = (DefaultTableModel) table.getModel();
		model.addColumn("รหัสหนังสือ");
		model.addColumn("ชื่อหนังสือ");
		model.addColumn("วันที่ยืม");
		model.addColumn("วันกำหนดคืน");

		JButton btnCancel = new JButton("ออกจากหน้ายืม");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}

		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancel.setBounds(697, 443, 128, 35);
		contentPane.add(btnCancel);

		JButton btnBorrow = new JButton("ยืมหนังสือ");
		btnBorrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MySQLConnect db = new MySQLConnect();
				int bo_id = Integer.valueOf(txtboId.getText());

				Date selectedBDate = BdateChooser.getDate();
				SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
				String borrow_date = sdf.format(selectedBDate);

				Date selectedRDate = RdateChooser.getDate();
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
				String return_date = sdf1.format(selectedRDate);

				String rtn_status = "not return";
				int sid = Integer.valueOf(cmbStaff.getSelectedItem().toString());
				int bid = Integer.valueOf(txtBorrowerId.getText());
				// System.out.println(bo_id+" "+borrow_date+" "+return_date+" "+fine+"
				// "+rtn_status+" "+sid+" "+bid);
				String sql = "INSERT INTO borrowing (bo_id, borrow_date,due_date,sid,bid) VALUES (" + bo_id + ",'"
						+ borrow_date + "','" + return_date + "'," + sid + "," + bid + ")";
				// System.out.println(sql);
				int rows = db.update(sql);
				if (rows == 1) {
					String[] options = { "OK" };
					JPanel panel = new JPanel();
					JLabel lbl = new JLabel("record Inserted to borrow Successfully");
					panel.add(lbl);
					int selectedOption = JOptionPane.showOptionDialog(null, panel, "Add Successfully",
							JOptionPane.NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

					int i = 0;
					while (i >= 0 && i < table.getRowCount()) {
						int book_id = Integer.valueOf(model.getValueAt(i, 0).toString());
						String book_name = model.getValueAt(i, 1).toString();
						String brw_date = model.getValueAt(i, 2).toString();
						String rtn_date = model.getValueAt(i, 3).toString();
						// System.out.println(book_id+" "+book_name+" "+borrow_date+" "+return_date);
						String sql1 = "INSERT INTO borrowing_detail(bo_id, detail_id, book_id, item_return_status,fine) VALUES ("
								+ bo_id + "," + (i + 1) + "," + book_id + ",'not return'," + 0 + ")";
						// System.out.println(sql1);
						int rowdb = db.update(sql1);

						// To implement Update book status

						String sql2 = "UPDATE book " + "SET status = 'not available' " + "WHERE book_id = " + book_id;
						// System.out.println(sql2);
						int rowdb2 = db.update(sql2);

						// if(rowdb==1) {
						// JOptionPane.showMessageDialog(null, "insert detail
						// complete","Alert",JOptionPane.INFORMATION_MESSAGE);
						// }

						i++;
					}

				}

				btnBorrow.setEnabled(false);

			}
		});
		btnBorrow.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBorrow.setBounds(510, 441, 117, 39);
		contentPane.add(btnBorrow);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("วันที่ยืม");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1_1_1_1.setBounds(20, 333, 93, 25);
		contentPane.add(lblNewLabel_1_1_1_1);

		JLabel lblNewLabel_1_1_1_2 = new JLabel("วันกำหนดคืน");
		lblNewLabel_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1_1_1_2.setBounds(10, 404, 108, 25);
		contentPane.add(lblNewLabel_1_1_1_2);

		JLabel lblNewLabel_1_1_2 = new JLabel("รหัสพนักงานให้บริการ");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1_1_2.setBounds(10, 467, 181, 25);
		contentPane.add(lblNewLabel_1_1_2);

		JLabel lblNewLabel_2 = new JLabel("รหัสการยืม");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(424, 38, 75, 14);
		contentPane.add(lblNewLabel_2);

		txtboId = new JTextField();
		txtboId.setEditable(false);
		txtboId.setBounds(510, 32, 86, 20);
		contentPane.add(txtboId);
		txtboId.setColumns(10);

		MySQLConnect db1 = new MySQLConnect();
		String sql1 = "select max(bo_id)+1 as newboId from borrowing";
		ResultSet rec1 = db.query(sql1);
		int row1 = 0;
		try {
			while ((rec1 != null) && rec1.next()) {
				txtboId.setText(rec1.getString("newboId"));
				row1++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JLabel label = new JLabel("");

		JLabel label_1 = new JLabel("");

		JLabel label_2 = new JLabel("");

		JLabel label_3 = new JLabel("");

		JLabel label_4 = new JLabel("");

		JLabel label_5 = new JLabel("");

		JLabel label_6 = new JLabel("");

	}
}
