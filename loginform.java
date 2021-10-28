package loginproject;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class loginform extends JFrame {

	private JPanel contentPane;
	private JTextField txt;
	private JPasswordField pwd;
	protected JLabel lbl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginform frame = new loginform();
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
	public loginform() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login Form");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(137, 11, 117, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("User Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(21, 68, 93, 29);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(20, 126, 100, 36);
		contentPane.add(lblNewLabel_2);
		
		txt = new JTextField();
		txt.setBounds(168, 73, 167, 20);
		contentPane.add(txt);
		txt.setColumns(10);
		
		pwd = new JPasswordField();
		pwd.setBounds(168, 135, 167, 20);
		contentPane.add(pwd);
		
		JLabel lbl = new JLabel("");
		lbl.setForeground(Color.RED);
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl.setBounds(153, 184, 82, 20);
		contentPane.add(lbl);
		
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=txt.getText();
				String password=String.valueOf(pwd.getPassword());
				try {
					Connection con=DatabaseConnection.initializeDatabase();
String sql="select * from user where user_name=? and password=?";
PreparedStatement stmt=con.prepareStatement(sql);
stmt.setString(1, name);
stmt.setString(2, password);
ResultSet rs=stmt.executeQuery();
boolean flag=false;

while(rs.next()) {
	flag=true;
}
if(flag) {
	Userlist ul=new Userlist();//call Userlist constructor
	ul.setVisible(true);
	txt.setText("");
	pwd.setText("");
}else {
	lbl.setText("Invalid User");
	txt.setText("");
	pwd.setText("");
}
					}catch(Exception exc) {
				
				}
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLogin.setBounds(153, 229, 89, 23);
		contentPane.add(btnLogin);
		
	}
}
