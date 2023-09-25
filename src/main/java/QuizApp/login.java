package QuizApp;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Wrapper;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class login extends JFrame {
    private JPanel contentPane;
    private JTextField txtUser;
    private JTextField txtPassword1;
    private JButton btnSignup;
    private JButton btnLogin;
    public static String usernameq;
    private boolean login = false;

    // SQLite database URL
    static final String DB_URL = "jdbc:sqlite:database.db";

    public static void main(String[] args) {
        Database.main(null);
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    login frame = new login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public login() {
        setTitle("Quiz: Login " + Database.success);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 550, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        JLabel label1 = new JLabel("LOGIN");
        label1.setText("LOGIN");
        label1.setBounds(250, 100, 50, 60);
        label1.setSize(50, 20);
        contentPane.add(label1);
        JLabel label2 = new JLabel("CB");
        label2.setText("Coded by Hazel Radingoana");
        label2.setBounds(300, 300, 400, 100);
        contentPane.add(label2);

        txtUser = new JTextField();
        txtUser.setBounds(250, 153, 99, 20);
        contentPane.add(txtUser);
        txtUser.setColumns(10);
        txtPassword1 = new JTextField();
        txtPassword1.setBounds(250, 204, 99, 20);
        contentPane.add(txtPassword1);
        txtPassword1.setColumns(10);

        JLabel lblUserName = new JLabel("User Name");
        lblUserName.setBounds(170, 156, 86, 14);
        contentPane.add(lblUserName);
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(170, 207, 86, 14);
        contentPane.add(lblPassword);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(250, 251, 89, 23);
        contentPane.add(btnLogin);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Connection conn = null;
                try {
                    String username = "";
                    String password = "";
                    username = txtUser.getText().trim();
                    password = txtPassword1.getText().trim();
                    if (username.equals("") || password.equals("")) {
                        JOptionPane.showMessageDialog(null, "Please Enter username and password", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        if (Database.success.equals("database_cnt_error.Proceeding in Offline Mode"))
                            login = true;
                        else {
                            try {
                                Class.forName("org.sqlite.JDBC");
                                Connection con = DriverManager.getConnection(DB_URL);
                                Statement stmt = con.createStatement();
                                String query = "SELECT user_name, passwd FROM sys.quiz;";
                                ResultSet rs = stmt.executeQuery(query);

                                while (rs.next()) {
                                    String db_username = rs.getString("user_name");
                                    String db_password = rs.getString("passwd");

                                    if (username.equals(db_username) && password.equals(db_password)) {
                                        System.out.println("OK");
                                        login = true;
                                    }
                                    System.out.println("COMPARING USERNAME:" + username + "  PASSWORD:" + password
                                            + "  WITH DB_USERNAME:" + db_username + "  DB_PASSWORD:" + db_password);
                                }
                            } catch (ClassNotFoundException e1) {
                                e1.printStackTrace();
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            }

                            conn = DriverManager.getConnection(DB_URL);
                            System.out.println("Connected to database successfully...");
                        }
                        if (login == true) {
                            String SMessage = "Logged in As " + username;
                            usernameq = username;
                            JOptionPane.showMessageDialog(null, SMessage, "Message", JOptionPane.PLAIN_MESSAGE);
                            new Question().setVisible(true);
                            dispose();
                        } else {
                            String SMessage = "Incorrect Password Or UserName";
                            JOptionPane.showMessageDialog(null, SMessage, "Message", JOptionPane.ERROR_MESSAGE);
                        }

                        assert conn != null;
                        conn.close();
                    }
                } catch (SQLException se) {

                    se.printStackTrace();
                } catch (Exception a) {
                    a.printStackTrace();
                }
            }
        });

        btnSignup = new JButton("SignUp");
        btnSignup.setBounds(160, 251, 89, 23);
        contentPane.add(btnSignup);

        btnSignup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Register().setVisible(true);
                dispose();
            }
        });
    }
}
