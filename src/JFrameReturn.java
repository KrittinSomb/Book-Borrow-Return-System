import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Component;

public class JFrameReturn extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ignored) {}

        EventQueue.invokeLater(() -> {
            try {
                JFrameReturn frame = new JFrameReturn();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public JFrameReturn() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Return Book");
        setBounds(100, 100, 980, 700);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(8, 8, 8, 8));
        contentPane.setLayout(null);               // Absolute Layout
        setContentPane(contentPane);

        // ===== Top: Member search =====
        JLabel lbMemberId = new JLabel("รหัสผู้ยืม");
        lbMemberId.setFont(new Font("TH Sarabun New", Font.PLAIN, 18));
        lbMemberId.setBounds(20, 20, 80, 24);
        contentPane.add(lbMemberId);

        JTextField tfMemberId = new JTextField();
        tfMemberId.setBounds(100, 20, 140, 24);
        contentPane.add(tfMemberId);

        JButton btnSearchMember = new JButton("ค้นหาผู้ยืม");
        btnSearchMember.setFont(new Font("TH Sarabun New", Font.PLAIN, 18));
        btnSearchMember.setBounds(250, 20, 110, 24);
        contentPane.add(btnSearchMember);

        JLabel lbMemberName = new JLabel("ชื่อผู้ยืม");
        lbMemberName.setFont(new Font("TH Sarabun New", Font.PLAIN, 18));
        lbMemberName.setBounds(380, 20, 70, 24);
        contentPane.add(lbMemberName);

        JTextField tfMemberName = new JTextField();
        tfMemberName.setEditable(false);
        tfMemberName.setBounds(440, 20, 500, 24);
        contentPane.add(tfMemberName);

        // ===== Borrowed books table (empty) =====
        JLabel lbBorrowed = new JLabel("รายการหนังสือที่ยืม");
        lbBorrowed.setFont(new Font("TH Sarabun New", Font.BOLD, 24));
        lbBorrowed.setBounds(20, 44, 200, 41);
        contentPane.add(lbBorrowed);

        JTable tblBorrowed = new JTable(new DefaultTableModel(
            new Object[][]{},
            new String[]{"ลำดับ","รหัสยืม","รหัสหนังสือ","ชื่อหนังสือ","วันที่ยืม","วันกำหนดคืน","สถานะการคืน"}
        ));
        tblBorrowed.setFont(new Font("TH Sarabun New", Font.PLAIN, 20));
        JScrollPane spBorrowed = new JScrollPane(tblBorrowed);
        spBorrowed.setBounds(20, 85, 920, 180);
        contentPane.add(spBorrowed);

        // ===== Detail (left) =====
        JLabel lbDetailTitle = new JLabel("รายละเอียดหนังสือ");
        lbDetailTitle.setFont(new Font("TH Sarabun New", Font.BOLD, 24));
        lbDetailTitle.setBounds(20, 276, 200, 24);
        contentPane.add(lbDetailTitle);

        JLabel lbBookId = new JLabel("รหัสหนังสือ");
        lbBookId.setFont(new Font("TH Sarabun New", Font.PLAIN, 18));
        lbBookId.setBounds(20, 305, 100, 24);
        contentPane.add(lbBookId);

        JTextField tfBookId = new JTextField();
        tfBookId.setBounds(120, 305, 180, 24);
        contentPane.add(tfBookId);

        JLabel lbBookName = new JLabel("ชื่อหนังสือ");
        lbBookName.setFont(new Font("TH Sarabun New", Font.PLAIN, 18));
        lbBookName.setBounds(20, 335, 100, 24);
        contentPane.add(lbBookName);

        JTextField tfBookTitle = new JTextField();
        tfBookTitle.setBounds(120, 335, 260, 24);
        contentPane.add(tfBookTitle);

        JLabel lbBorrowDate = new JLabel("วันที่ยืม");
        lbBorrowDate.setFont(new Font("TH Sarabun New", Font.PLAIN, 18));
        lbBorrowDate.setBounds(20, 365, 100, 24);
        contentPane.add(lbBorrowDate);

        JTextField tfBorrowDate = new JTextField();
        tfBorrowDate.setBounds(120, 365, 140, 24);
        contentPane.add(tfBorrowDate);

        JLabel lbDueDate = new JLabel("วันกำหนดคืน");
        lbDueDate.setFont(new Font("TH Sarabun New", Font.PLAIN, 18));
        lbDueDate.setBounds(20, 395, 100, 24);
        contentPane.add(lbDueDate);

        JTextField tfDueDate = new JTextField();
        tfDueDate.setBounds(120, 395, 140, 24);
        contentPane.add(tfDueDate);

        JLabel lbReturnDate = new JLabel("วันคืน");
        lbReturnDate.setFont(new Font("TH Sarabun New", Font.PLAIN, 18));
        lbReturnDate.setBounds(20, 425, 100, 24);
        contentPane.add(lbReturnDate);

        JTextField tfReturnDate = new JTextField();
        tfReturnDate.setBounds(120, 425, 140, 24);
        contentPane.add(tfReturnDate);

        JLabel lbOver = new JLabel("เกินกำหนด");
        lbOver.setFont(new Font("TH Sarabun New", Font.PLAIN, 18));
        lbOver.setBounds(20, 455, 100, 24);
        contentPane.add(lbOver);

        JTextField tfOverDays = new JTextField();
        tfOverDays.setBounds(120, 455, 60, 24);
        contentPane.add(tfOverDays);

        JLabel lbOverUnit = new JLabel("วัน");
        lbOverUnit.setFont(new Font("TH Sarabun New", Font.PLAIN, 18));
        lbOverUnit.setBounds(185, 455, 30, 24);
        contentPane.add(lbOverUnit);

        JLabel lbFine = new JLabel("ค่าปรับ");
        lbFine.setFont(new Font("TH Sarabun New", Font.PLAIN, 18));
        lbFine.setBounds(20, 485, 100, 24);
        contentPane.add(lbFine);

        JTextField tfFine = new JTextField();
        tfFine.setBounds(120, 485, 100, 24);
        contentPane.add(tfFine);

        JLabel lbFineUnit = new JLabel("บาท");
        lbFineUnit.setFont(new Font("TH Sarabun New", Font.PLAIN, 18));
        lbFineUnit.setBounds(225, 485, 40, 24);
        contentPane.add(lbFineUnit);

        // ===== Summary (right) =====
        JLabel lbTotalFine = new JLabel("รวมค่าปรับทั้งสิ้น");
        lbTotalFine.setFont(new Font("TH Sarabun New", Font.PLAIN, 18));
        lbTotalFine.setBounds(574, 485, 140, 24);
        contentPane.add(lbTotalFine);

        JTextField tfTotalFine = new JTextField();
        tfTotalFine.setEditable(false);
        tfTotalFine.setBounds(704, 485, 120, 24);
        contentPane.add(tfTotalFine);

        JLabel lbTotalUnit = new JLabel("บาท");
        lbTotalUnit.setFont(new Font("TH Sarabun New", Font.PLAIN, 18));
        lbTotalUnit.setBounds(834, 485, 40, 24);
        contentPane.add(lbTotalUnit);

        JLabel lbOutstanding = new JLabel("ยอดคงค้าง");
        lbOutstanding.setFont(new Font("TH Sarabun New", Font.PLAIN, 18));
        lbOutstanding.setBounds(574, 525, 140, 24);
        contentPane.add(lbOutstanding);

        JTextField tfOutstanding = new JTextField();
        tfOutstanding.setEditable(false);
        tfOutstanding.setBounds(704, 525, 120, 24);
        contentPane.add(tfOutstanding);

        JLabel lbOutUnit = new JLabel("บาท");
        lbOutUnit.setFont(new Font("TH Sarabun New", Font.PLAIN, 18));
        lbOutUnit.setBounds(834, 525, 40, 24);
        contentPane.add(lbOutUnit);

        // ===== Return list (empty) =====
        JLabel lbReturnList = new JLabel("รายการหนังสือที่ทำการคืน");
        lbReturnList.setFont(new Font("TH Sarabun New", Font.BOLD, 24));
        lbReturnList.setBounds(423, 276, 250, 42);
        contentPane.add(lbReturnList);

        JTable tblReturn = new JTable(new DefaultTableModel(
            new Object[][]{},
            new String[]{"รหัสหนังสือ","ชื่อหนังสือ","ค่าปรับ (บาท)","เงินรับ (บาท)","สถานะการคืน"}
        ));
        JScrollPane spReturn = new JScrollPane(tblReturn);
        spReturn.setBounds(423, 329, 531, 120);
        contentPane.add(spReturn);

        // ===== Buttons =====
        JButton btnFind = new JButton("ค้นหนังสือ");
        btnFind.setFont(new Font("TH Sarabun New", Font.BOLD, 36));
        btnFind.setBounds(76, 571, 200, 53);
        contentPane.add(btnFind);

        JButton btnPay = new JButton("ชำระค่าปรับ");
        btnPay.setFont(new Font("TH Sarabun New", Font.BOLD, 36));
        btnPay.setBounds(380, 571, 195, 53);
        contentPane.add(btnPay);

        JButton btnExit = new JButton("ออกจากหน้าคืน");
        btnExit.setFont(new Font("TH Sarabun New", Font.BOLD, 36));
        btnExit.setBounds(656, 571, 195, 53);
        btnExit.addActionListener(e -> dispose());
        contentPane.add(btnExit);
    }
}
