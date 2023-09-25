package StudentGradeCalculator;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Student Grade Calculator");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 500);

            SpringLayout layout = new SpringLayout();
            JPanel panel = new JPanel(layout);
            frame.add(panel);

            String[] subjects = {
                "Physical Science", "Life Science", "Accounting",
                "Mathematics", "English", "Computer Application Technology"
            };

            JTextField[] subjectTextFields = new JTextField[subjects.length];
            JLabel[] errorLabels = new JLabel[subjects.length];

            for (int i = 0; i < subjects.length; i++) {
                JLabel label = new JLabel(subjects[i] + ":");
                JTextField textField = new JTextField(10);
                JLabel errorLabel = new JLabel("");
                panel.add(label);
                panel.add(textField);
                panel.add(errorLabel);

                subjectTextFields[i] = textField;
                errorLabels[i] = errorLabel;

                // Set constraints for the label
                layout.putConstraint(SpringLayout.WEST, label, 10, SpringLayout.WEST, panel);
                layout.putConstraint(SpringLayout.NORTH, label, 20 + (i * 30), SpringLayout.NORTH, panel);

                // Set constraints for the text field
                layout.putConstraint(SpringLayout.WEST, textField, 260, SpringLayout.WEST, panel);
                layout.putConstraint(SpringLayout.NORTH, textField, 20 + (i * 30), SpringLayout.NORTH, panel);

                // Set constraints for the error label
                layout.putConstraint(SpringLayout.WEST, errorLabel, 10, SpringLayout.EAST, textField);
                layout.putConstraint(SpringLayout.NORTH, errorLabel, 20 + (i * 30), SpringLayout.NORTH, panel);

                // textField.addActionListener(new ActionListener() {
                //     @Override
                //     public void actionPerformed(ActionEvent e) {
                //         JTextField source = (JTextField) e.getSource();
                //         try {
                //             int input = Integer.parseInt(source.getText());
                //             if (input < 0 || input > 100) {
                //                 errorLabel.setText("Input must be between 0 and 100");
                //                 source.setText("");
                //             } else {
                //                 errorLabel.setText("");
                //             }
                //         } catch (NumberFormatException ex) {
                //             errorLabel.setText("Invalid input. Please enter a number.");
                //             source.setText(""); 
                //         }
                //     }
                // }
                // );
            }

            JButton calculateButton = new JButton("Calculate");
            panel.add(calculateButton);

            // Set constraints for the Calculate button
            layout.putConstraint(SpringLayout.WEST, calculateButton, 10, SpringLayout.WEST, panel);
            layout.putConstraint(SpringLayout.NORTH, calculateButton, 30 + (subjects.length * 30), SpringLayout.NORTH, panel);

            calculateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int[] subjectMarks = new int[subjects.length];
                    boolean validInput = true;
                    for (int i = 0; i < subjects.length; i++) {
                        try {
                            int input = Integer.parseInt(subjectTextFields[i].getText());
                            if (input < 0 || input > 100) {
                                errorLabels[i].setText("Input must be between 0 and 100");
                                
                                validInput = false;
                                
                            } else {
                                errorLabels[i].setText("");
                                subjectMarks[i] = input;
                            }
                        } catch (NumberFormatException ex) {
                            errorLabels[i].setText("Invalid input. Please enter a number.");
                            validInput = false;
                        }
                    }

                    if (validInput) {
                        int averagePercentage = calculateAveragePercentage(subjectMarks);
                        StudentGrade(averagePercentage);
                    }
                }
            });

            frame.setVisible(true);
        });
    }

    public static int calculateAveragePercentage(int[] subjectMarks) {
        int totalMarks = 0;
        for (int marks : subjectMarks) {
            totalMarks += marks;
        }
        return (totalMarks * 100) / (subjectMarks.length * 100);
    }

    public static void StudentGrade(int averagePercentage) {
        String grade;
        if (averagePercentage >= 90) {
            grade = "You got A+";
        } else if (averagePercentage >= 80) {
            grade = "You got A";
        } else if (averagePercentage >= 70) {
            grade = "You got B";
        } else if (averagePercentage >= 60) {
            grade = "You got C";
        } else if (averagePercentage >= 50) {
            grade = "You got D";
        } else {
            grade = "You FAILED";
        }

        JOptionPane.showMessageDialog(null, grade, "Grade Result", JOptionPane.INFORMATION_MESSAGE);
    }
}
