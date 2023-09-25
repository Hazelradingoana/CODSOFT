package QuizApp;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Register extends JFrame {
	private JPanel contentPane;
	private JTextField txtUser;
	private JTextField txtName;
	private JTextField txtMail;
	private JTextField txtPassword1;
	private JButton btnSignup;
	private JButton btnLogin;
	protected java.lang.String Spassword;

	static final String DB_URL = "jdbc:sqlite:database.db";

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Register frame = new Register();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public Register() {
		setTitle("Quiz: Sign up");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label1 = new JLabel("SIGN UP");
		label1.setBounds(250, 10, 50, 60);
		label1.setSize(50, 20);
		contentPane.add(label1);

		JLabel label2 = new JLabel("Coded by Hazel Radingoana");
		label2.setBounds(300, 300, 400, 100);
		contentPane.add(label2);

		txtName = new JTextField();
		txtName.setBounds(250, 51, 99, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);

		txtMail = new JTextField();
		txtMail.setBounds(250, 102, 99, 20);
		contentPane.add(txtMail);
		txtMail.setColumns(10);

		txtUser = new JTextField();
		txtUser.setBounds(250, 153, 99, 20);
		contentPane.add(txtUser);
		txtUser.setColumns(10);

		txtPassword1 = new JTextField();
		txtPassword1.setBounds(250, 204, 99, 20);
		contentPane.add(txtPassword1);
		txtPassword1.setColumns(10);

		JLabel lblName = new JLabel("Full Name");
		lblName.setBounds(170, 54, 86, 14);
		contentPane.add(lblName);

		JLabel lblMail = new JLabel("Email");
		lblMail.setBounds(170, 105, 86, 14);
		contentPane.add(lblMail);

		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setBounds(170, 156, 86, 14);
		contentPane.add(lblUserName);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(170, 207, 86, 14);
		contentPane.add(lblPassword);

		btnSignup = new JButton("Sign Up");
		btnSignup.setBounds(250, 251, 89, 23);
		contentPane.add(btnSignup);

		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String full_name = txtName.getText().trim();
				String g_mail = txtMail.getText().trim();
				String user_name = txtUser.getText().trim();
				String passwd = txtPassword1.getText().trim();

				if (user_name.isEmpty() || passwd.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter valid username and password", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						Connection conn = DriverManager.getConnection(DB_URL);
						String insertQuery = "INSERT INTO quiz (full_name, email, user_name, passwd) VALUES (?, ?, ?, ?)";
						PreparedStatement pstmt = conn.prepareStatement(insertQuery);
						pstmt.setString(1, full_name);
						pstmt.setString(2, g_mail);
						pstmt.setString(3, user_name);
						pstmt.setString(4, passwd);

						int rowsInserted = pstmt.executeUpdate();
						pstmt.close();
						conn.close();

						if (rowsInserted > 0) {
							String SMessage = "Record added for " + user_name;
							JOptionPane.showMessageDialog(null, SMessage, "Message", JOptionPane.PLAIN_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "Failed to insert record", "Error", JOptionPane.ERROR_MESSAGE);
						}
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}
			}
		});

		btnLogin = new JButton("Login");
		btnLogin.setBounds(160, 251, 89, 23);
		contentPane.add(btnLogin);

		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new login().setVisible(true);
				dispose();
			}
		});
	}
}
