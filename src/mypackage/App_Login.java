package mypackage;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
public class App_Login extends JFrame {
Connection conn = null;
PreparedStatement ps = null;
ResultSet rs = null;
Statement st=null;
private JPanel contentPane;
private JTextField txtUserName;
private JPasswordField txtPassword;
/**
* Launch the application.
*/
public App_Login(){
initComponents();
conn= DBConnection.ConnectDB();
}
public static void main(String[] args) {
EventQueue.invokeLater(new Runnable() {
public void run() {
try {
App_Login frame = new App_Login();
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
public void initComponents() {
for(javax.swing.UIManager.LookAndFeelInfo info:javax.swing.UIManager.getInstalledLookAndFeels())
if("Nimbus".equals(info.getName())){
try {
javax.swing.UIManager.setLookAndFeel(info.getClassName());
} catch (ClassNotFoundException e) {
// TODO Auto-generated catch block
e.printStackTrace();
} catch (InstantiationException e) {
// TODO Auto-generated catch block
e.printStackTrace();
} catch (IllegalAccessException e) {
// TODO Auto-generated catch block
e.printStackTrace();
} catch (UnsupportedLookAndFeelException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
break;
}
setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
setBounds(500, 100, 499, 212);
contentPane = new JPanel();
contentPane.setBackground(new Color(0, 153, 255));
contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
setContentPane(contentPane);
contentPane.setLayout(null);JLabel lblUserName = new JLabel("User Name");
lblUserName.setBounds(131, 36, 89, 23);
contentPane.add(lblUserName);
JLabel lblPassword = new JLabel("Password");
lblPassword.setBounds(141, 67, 89, 23);
contentPane.add(lblPassword);
txtUserName = new JTextField();
txtUserName.setFont(new Font("Tahoma", Font.PLAIN, 12));
txtUserName.setBackground(new Color(255, 165, 0));
txtUserName.setBounds(221, 33, 115, 23);
contentPane.add(txtUserName);
txtUserName.setColumns(10);
txtPassword = new JPasswordField();
txtPassword.setBackground(new Color(255, 165, 0));
txtPassword.setBounds(221, 67, 115, 23);
contentPane.add(txtPassword);
JButton btnLogin = new JButton("Login");
btnLogin.addFocusListener(new FocusAdapter() {
@Override
public void focusLost(FocusEvent e) {
}
});
btnLogin.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
String sql ="select * from Users where UserName=? and Password=?"; //UserName=Admin, Password=admin
try{
ps=conn.prepareStatement(sql);
ps.setString(1, txtUserName.getText());
ps.setString(2, txtPassword.getText());
rs=ps.executeQuery();
if(rs.next()){
JOptionPane.showMessageDialog(null,"connected Successfully");
system s = new system();
s.setVisible(true);
close();
}
else{
JOptionPane.showMessageDialog(null, "Connection Problem");
}
}catch (Exception ex){
JOptionPane.showMessageDialog(null, ex);
}
}
});
btnLogin.setBounds(221, 103, 89, 23);
contentPane.add(btnLogin);
}
public void close(){
WindowEvent wincloseEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wincloseEvent);
}
}