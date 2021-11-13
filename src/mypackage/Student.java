package mypackage;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import java.util.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.util.Vector;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.SystemColor;
public class Student extends JFrame {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ResultSetMetaData rss = null;
		DefaultTableModel Df=null;
		Statement st=null;
	private JPanel contentPane;
	private JTextField textName;
	private JTextField textPre;
	private JTextField textPer;
	private JTextField textID;
	private JTextField textFname;
	private JTextField textDOB;
	private JTextField textFoc;
	private JTextField textPhone;
	private JTextField textGender;
	private JTextField textYear;
	private JTextField textCourse;
	private JTextField textDegree;
	
	private JTable table_1;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public Student() {
		initComponents();
		conn= DBConnection.ConnectDB();
		table_update();
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Student frame = new Student();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	private void table_update() {
		int c;
		
		try {
			ps = conn.prepareStatement("select * from STUDENT2");
			rs=ps.executeQuery();
			rss=rs.getMetaData();
			c=rss.getColumnCount();
			
			Df=(DefaultTableModel)table_1.getModel();
			Df.setRowCount(0);
			
			while(rs.next()) {
				Vector v2=new Vector();
				
				for(int i=1; i<=c ; i++) {
					v2.add(rs.getInt("ID"));
					v2.add(rs.getString("NAME"));
					v2.add(rs.getString("PRE_ADDRESS"));
					v2.add(rs.getString("PER_ADDRESS"));
					v2.add(rs.getString("FATHERNAME"));
					v2.add(rs.getString("DATEOFBIRTH"));
					v2.add(rs.getString("FATHEROCCUPATION"));
					v2.add(rs.getInt("PHONE"));
					v2.add(rs.getString("GENDER"));
					v2.add(rs.getInt("YEAR"));
					v2.add(rs.getString("COURSE"));
					v2.add(rs.getDouble("DEGREE"));
				}
				Df.addRow(v2);
			}
			
			
		}catch (Exception ex){
			JOptionPane.showMessageDialog(null, ex);
	}
		
	}
	
	/**
	 * Create the frame.
	 */
	public void initComponents() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1211, 511);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 204, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Student informations");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 25));
		lblNewLabel.setBounds(478, 11, 482, 27);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(153, 0, 51)));
		panel.setBounds(0, 48, 302, 413);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(10, 49, 46, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Pre-adresse");
		lblNewLabel_2.setBounds(10, 88, 75, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Per-adresse");
		lblNewLabel_3.setBounds(10, 113, 65, 14);
		panel.add(lblNewLabel_3);
		
		textName = new JTextField();
		textName.setBounds(105, 41, 189, 30);
		panel.add(textName);
		textName.setColumns(10);
		
		textPre = new JTextField();
		textPre.setBounds(105, 79, 189, 26);
		panel.add(textPre);
		textPre.setColumns(10);
		
		textPer = new JTextField();
		textPer.setBounds(105, 107, 189, 26);
		panel.add(textPer);
		textPer.setColumns(10);
		
		
			
		
		
		
		
		
		
		
		
		
		
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				//int ID = Integer.parseInt(textID.getText());
				String Name = textName.getText();
				String PreAd = textPre.getText();
				String PerAd = textPer.getText();
				String Fname = textFname.getText();
				String DOB = textDOB.getText();
				String Foc = textFoc.getText();
				int Phone = Integer.parseInt(textPhone.getText());
				String Gender = textGender.getText();
				int Year = Integer.parseInt(textYear.getText());
				String Course = textCourse.getText();
				double Degree = Double.parseDouble(textDegree.getText());
				try {
					ps = conn.prepareStatement("insert into STUDENT2(NAME,PRE_ADDRESS,PER_ADDRESS,FATHERNAME,DATEOFBIRTH,FATHEROCCUPATION,PHONE,GENDER,YEAR,COURSE,DEGREE)values(?,?,?,?,?,?,?,?,?,?,?)");
					//ps.setInt(1, ID);
					ps.setString(1, Name);
					ps.setString(2, PreAd);
					ps.setString(3, PerAd);
					ps.setString(4, Fname);
					ps.setString(5, DOB);
					ps.setString(6, Foc);
					ps.setInt(7, Phone);
					ps.setString(8, Gender);
					ps.setInt(9, Year);
					ps.setString(10, Course);
					ps.setDouble(11, Degree);
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Student has been added");
					
					//textID.setText("");
					textName.setText("");
					textPre.setText("");
					textPer.setText("");
					textFname.setText("");
					textDOB.setText("");
					textFoc.setText("");
					textPhone.setText("");
					textGender.setText("");
					textYear.setText("");
					textCourse.setText("");
					textDegree.setText("");
					//textID.requestFocus();
					
				}catch (Exception ex){
					JOptionPane.showMessageDialog(null, ex);
				}
				table_update();
			}
		});
		
		btnNewButton.setBounds(10, 379, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Edit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Df=(DefaultTableModel)table_1.getModel();
				int selectedIndex= table_1.getSelectedRow();
				
				int ID = Integer.parseInt(Df.getValueAt(selectedIndex, 0).toString());
				String Name = textName.getText();
				String PreAd = textPre.getText();
				String PerAd = textPer.getText();
				String Fname = textFname.getText();
				String DOB = textDOB.getText();
				String Foc = textFoc.getText();
				int Phone = Integer.parseInt(textPhone.getText());
				String Gender = textGender.getText();
				int Year = Integer.parseInt(textYear.getText());
				String Course = textCourse.getText();
				double Degree = Double.parseDouble(textDegree.getText());
				try {
					ps = conn.prepareStatement("update STUDENT2 set NAME=?,PRE_ADDRESS=?,PER_ADDRESS=?,FATHERNAME=?,DATEOFBIRTH=?,FATHEROCCUPATION=?,PHONE=?,GENDER=?,YEAR=?,COURSE=?,DEGREE=? where ID=?");
					ps.setInt(12, ID);
					ps.setString(1, Name);
					ps.setString(2, PreAd);
					ps.setString(3, PerAd);
					ps.setString(4, Fname);
					ps.setString(5, DOB);
					ps.setString(6, Foc);
					ps.setInt(7, Phone);
					ps.setString(8, Gender);
					ps.setInt(9, Year);
					ps.setString(10, Course);
					ps.setDouble(11, Degree);
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Student Update");
					table_update();
					
					//textID.setText("");
					textName.setText("");
					textPre.setText("");
					textPer.setText("");
					textFname.setText("");
					textDOB.setText("");
					textFoc.setText("");
					textPhone.setText("");
					textGender.setText("");
					textYear.setText("");
					textCourse.setText("");
					textDegree.setText("");
					//textID.requestFocus();
					
				}catch (Exception ex){
					JOptionPane.showMessageDialog(null, ex);
				}
				
				
				
			}
		});
		btnNewButton_1.setBounds(105, 379, 89, 23);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Df=(DefaultTableModel)table_1.getModel();
				int selectedIndex= table_1.getSelectedRow();
				
				int ID = Integer.parseInt(Df.getValueAt(selectedIndex, 0).toString());
				
				int dialogresult = JOptionPane.showConfirmDialog(null, "do you want to delete this student","Warning",JOptionPane.YES_NO_OPTION);
				
				if(dialogresult==JOptionPane.YES_OPTION) {
				
				try {
					ps = conn.prepareStatement("delete from STUDENT2 where ID=?");
					ps.setInt(1, ID);
					
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Student has been deleted successfully");
					table_update();
					
					//textID.setText("");
					textName.setText("");
					textPre.setText("");
					textPer.setText("");
					textFname.setText("");
					textDOB.setText("");
					textFoc.setText("");
					textPhone.setText("");
					textGender.setText("");
					textYear.setText("");
					textCourse.setText("");
					textDegree.setText("");
					//textID.requestFocus();
					
				}catch (Exception ex){
					JOptionPane.showMessageDialog(null, ex);
				}
				}
				
			}
		});
		btnNewButton_2.setBounds(204, 379, 89, 23);
		panel.add(btnNewButton_2);
		
		/*textID = new JTextField();
		textID.setBounds(105, 11, 189, 23);
		panel.add(textID);
		textID.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("ID");
		lblNewLabel_4.setBounds(10, 17, 65, 14);
		panel.add(lblNewLabel_4);*/
		
		textFname = new JTextField();
		textFname.setBounds(105, 139, 189, 31);
		panel.add(textFname);
		textFname.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Father Name");
		lblNewLabel_5.setBounds(10, 147, 65, 14);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Date of birth");
		lblNewLabel_6.setBounds(10, 183, 75, 14);
		panel.add(lblNewLabel_6);
		
		textDOB = new JTextField();
		textDOB.setBounds(105, 175, 189, 31);
		panel.add(textDOB);
		textDOB.setColumns(10);
		
		textFoc = new JTextField();
		textFoc.setBounds(105, 209, 189, 31);
		panel.add(textFoc);
		textFoc.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Father occupation");
		lblNewLabel_7.setBounds(10, 217, 89, 14);
		panel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Phone");
		lblNewLabel_8.setBounds(10, 244, 46, 14);
		panel.add(lblNewLabel_8);
		
		textPhone = new JTextField();
		textPhone.setBounds(105, 240, 189, 22);
		panel.add(textPhone);
		textPhone.setColumns(10);
		
		textGender = new JTextField();
		textGender.setBounds(105, 266, 189, 26);
		panel.add(textGender);
		textGender.setColumns(10);
		
		textYear = new JTextField();
		textYear.setBounds(105, 293, 189, 24);
		panel.add(textYear);
		textYear.setColumns(10);
		
		textCourse = new JTextField();
		textCourse.setBounds(105, 320, 189, 25);
		panel.add(textCourse);
		textCourse.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Gender");
		lblNewLabel_9.setBounds(10, 272, 46, 14);
		panel.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Year");
		lblNewLabel_10.setBounds(10, 298, 46, 14);
		panel.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("Course");
		lblNewLabel_11.setBounds(10, 325, 46, 14);
		panel.add(lblNewLabel_11);
		
		textDegree = new JTextField();
		textDegree.setBounds(105, 345, 189, 23);
		panel.add(textDegree);
		textDegree.setColumns(10);
		
		JLabel lblNewLabel_12 = new JLabel("Degree");
		lblNewLabel_12.setBounds(10, 349, 46, 14);
		panel.add(lblNewLabel_12);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			//@Override
			/*public void mouseClicked(MouseEvent e) {
				Df=(DefaultTableModel)table_1.getModel();
				int selectedIndex= table_1.getSelectedRow();
				
				textID.setText(Df.getValueAt(selectedIndex, 0).toString());
				textName.setText(Df.getValueAt(selectedIndex, 1).toString());
				textPre.setText(Df.getValueAt(selectedIndex, 2).toString());
				textPer.setText(Df.getValueAt(selectedIndex, 3).toString());
				textFname.setText(Df.getValueAt(selectedIndex, 4).toString());
				textDOB.setText(Df.getValueAt(selectedIndex, 5).toString());
				textFoc.setText(Df.getValueAt(selectedIndex, 6).toString());
				textPhone.setText(Df.getValueAt(selectedIndex, 7).toString());
				textGender.setText(Df.getValueAt(selectedIndex, 8).toString());
				textYear.setText(Df.getValueAt(selectedIndex, 9).toString());
				textCourse.setText(Df.getValueAt(selectedIndex, 10).toString());
				textDegree.setText(Df.getValueAt(selectedIndex, 11).toString());
				
				
				
			}*/
			
			
			
			
			
			
			@Override
			public void mousePressed(MouseEvent e) {
				Df=(DefaultTableModel)table_1.getModel();
				int selectedIndex= table_1.getSelectedRow();
				
				//textID.setText(Df.getValueAt(selectedIndex, 0).toString());
				textName.setText(Df.getValueAt(selectedIndex, 1).toString());
				textPre.setText(Df.getValueAt(selectedIndex, 2).toString());
				textPer.setText(Df.getValueAt(selectedIndex, 3).toString());
				textFname.setText(Df.getValueAt(selectedIndex, 4).toString());
				textDOB.setText(Df.getValueAt(selectedIndex, 5).toString());
				textFoc.setText(Df.getValueAt(selectedIndex, 6).toString());
				textPhone.setText(Df.getValueAt(selectedIndex, 7).toString());
				textGender.setText(Df.getValueAt(selectedIndex, 8).toString());
				textYear.setText(Df.getValueAt(selectedIndex, 9).toString());
				textCourse.setText(Df.getValueAt(selectedIndex, 10).toString());
				textDegree.setText(Df.getValueAt(selectedIndex, 11).toString());
			}
		});
		scrollPane.setBounds(308, 73, 883, 361);
		contentPane.add(scrollPane);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Pre_Address", "Per_Address", "Father Name", "Dat of birth", "Father Occupation", "Phone", "Gender", "Year", "Course", "Degree"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table_1);
		
		JButton btnNewButton_3 = new JButton("Exit");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_3.setBounds(1096, 438, 89, 23);
		contentPane.add(btnNewButton_3);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				try {
					
					ps=conn.prepareStatement("select * from STUDENT2 where NAME=?");
					ps.setString(1, textField.getText());
					rs=ps.executeQuery();
					
					table_1.setModel(DbUtils.resultSetToTableModel(rs));
					
					
					
					
				}catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
				
				
				
				
				
			}
		});
		textField.setBounds(1011, 35, 143, 27);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_4 = new JButton("update");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_update();
				//textID.setText("");
				textName.setText("");
				textPre.setText("");
				textPer.setText("");
				textFname.setText("");
				textDOB.setText("");
				textFoc.setText("");
				textPhone.setText("");
				textGender.setText("");
				textYear.setText("");
				textCourse.setText("");
				textDegree.setText("");
				//textID.requestFocus();
			}
		});
		btnNewButton_4.setBounds(995, 438, 89, 23);
		contentPane.add(btnNewButton_4);
		
		JLabel lblNewLabel_13 = new JLabel("Search:");
		lblNewLabel_13.setForeground(SystemColor.textInactiveText);
		lblNewLabel_13.setToolTipText("");
		lblNewLabel_13.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_13.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_13.setBackground(new Color(255, 175, 175));
		lblNewLabel_13.setBounds(957, 44, 46, 14);
		contentPane.add(lblNewLabel_13);
		
	
	}
}
