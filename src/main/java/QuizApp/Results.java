package QuizApp;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Results extends JFrame {
	private JPanel contentPane;
	private JButton Again;
	private JButton Exit;
	private JButton Answers;
	private login login;
	private int results;

	static final String DB_URL = "jdbc:sqlite:database.db";

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Results frame = new Results();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public Results() {
		setTitle("Results");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		results = Question.score;
		String result = String.valueOf(results);

		JLabel username = new JLabel("<html>Hey....<html>" + login.usernameq + "!");
		username.setBounds(220, 40, 200, 200);
		contentPane.add(username);

		results = 10 - results;
		String wrong = String.valueOf(results);

		JLabel finalresult = new JLabel("<html>Your Final Score is:<html>" + result
				+ "<html><br>Total Right Answers: <html>" + result + "<html><br>Total Wrong Answers: <html>" + wrong);
		finalresult.setBounds(200, 90, 200, 200);
		contentPane.add(finalresult);

		Exit = new JButton("Exit");
		Exit.setBounds(270, 251, 89, 23);
		contentPane.add(Exit);
		Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		Again = new JButton("Play Again");
		Again.setBounds(180, 251, 89, 23);
		contentPane.add(Again);
		Again.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new login().setVisible(true);
				dispose();
			}
		});

		Answers = new JButton("Answers");
		Answers.setBounds(225, 291, 89, 23);
		contentPane.add(Answers);
		Answers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Answers().setVisible(true);
				dispose();
			}
		});

		JLabel label2 = new JLabel("SRI");
		label2.setText("CODED BY Hazel Radingoana");
		label2.setBounds(300, 300, 400, 100);
		contentPane.add(label2);

		// Initialize the database connection here
		initializeDatabase();
	}

	private void initializeDatabase() {
		try {
			Connection conn = DriverManager.getConnection(DB_URL);
			Statement stmt = conn.createStatement();
			// You can perform database operations here if needed
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
