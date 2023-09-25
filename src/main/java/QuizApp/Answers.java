package QuizApp;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Answers extends JFrame {
	private JPanel contentPane;
	private JButton Again;
	private JButton Exit;
	int j = 0;
	String[][] NumberStorage;
	String[][] ans;
	public static String results;

	static final String DB_URL = "jdbc:sqlite:sys.db";

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Answers frame = new Answers();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public Answers() {
		setTitle("Answers");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Question qusobj = new Question();
		String[][] qusarray = qusobj.qarray();
		Question ansobj = new Question();
		String[][] ansarray = ansobj.ansarray();
		System.out.println(qusarray[1][0]);
		for (int i = 0, j = 0; i <= 9; i++) {

			JLabel quest = new JLabel((i + 1) + ") " + qusarray[i][0]);
			quest.setBounds(185, 10 + j, 200, 200);
			contentPane.add(quest);

			JLabel ans = new JLabel("Ans) " + ansarray[i][1]);
			ans.setBounds(190, 30 + j, 200, 200);
			contentPane.add(ans);
			j = j + 40;

		}
		Exit = new JButton("Exit");
		Exit.setBounds(270, 530, 89, 23);
		contentPane.add(Exit);
		Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		Again = new JButton("Play Again");
		Again.setBounds(180, 530, 89, 23);
		contentPane.add(Again);

		Again.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new login().setVisible(true);
				dispose();
			}
		});

		JLabel label2 = new JLabel("sri");
		label2.setText("Coded by Hazel Radingoana");
		label2.setBounds(300, 600, 400, 100);
		contentPane.add(label2);

		// Initialize the database connection here
		initializeDatabase();
	}

	private void initializeDatabase() {
		try {
			Connection conn = DriverManager.getConnection(DB_URL);
			// You can perform database operations here if needed
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
