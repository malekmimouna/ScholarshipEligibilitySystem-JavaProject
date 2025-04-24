package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import model.Student;
import database.DatabaseHelper;

public class ApplyPage extends JFrame {

    public ApplyPage(Runnable onFinish) {
        setTitle("🎓 Scholarship Application");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main container with background
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(240, 245, 249));

        // Form panel (card)
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(40, 50, 40, 50)
        ));
        formPanel.setBackground(Color.WHITE);
        formPanel.setMaximumSize(new Dimension(600, Integer.MAX_VALUE));

        // Title
        JLabel title = new JLabel("Scholarship Application");
        title.setFont(new Font("SansSerif", Font.BOLD, 32));
        title.setForeground(new Color(63, 81, 181));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        formPanel.add(title);

        // Form icon
        JLabel icon = new JLabel(new ImageIcon("images/scholarship.png")); // Replace with your icon path
        icon.setAlignmentX(Component.CENTER_ALIGNMENT);
        icon.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        formPanel.add(icon);

        // Form fields
        JTextField nameField = createFormField(formPanel, "Full Name");
        JTextField gpaField = createFormField(formPanel, "GPA (0.0-4.0)");
        JTextField incomeField = createFormField(formPanel, "Annual Family Income ($)");
        JTextField ageField = createFormField(formPanel, "Age");

        // Submit button
        JButton submitBtn = new JButton("Submit Application");
        styleButton(submitBtn, new Color(76, 175, 80));
        submitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitBtn.setPreferredSize(new Dimension(250, 45));
        formPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        formPanel.add(submitBtn);

        // Add form panel to main panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.CENTER;
        mainPanel.add(formPanel, gbc);

        add(mainPanel);
        setVisible(true);

        // Action Listener
        submitBtn.addActionListener((ActionEvent e) -> {
            try {
                String name = nameField.getText().trim();
                double gpa = Double.parseDouble(gpaField.getText().trim());
                double income = Double.parseDouble(incomeField.getText().trim());
                int age = Integer.parseInt(ageField.getText().trim());

                if (name.isEmpty()) {
                    showErrorDialog("Please enter your full name.");
                    return;
                }
                if (gpa < 0 || gpa > 4.0) {
                    showErrorDialog("Please enter a valid GPA between 0.0 and 4.0.");
                    return;
                }
                if (income < 0) {
                    showErrorDialog("Income cannot be negative.");
                    return;
                }
                if (age < 16 || age > 30) {
                    showErrorDialog("Age must be between 16 and 30 years.");
                    return;
                }

                Student student = new Student(name, gpa, income, age);
                DatabaseHelper.insertStudent(student);

                showSuccessDialog("✅ Application submitted successfully for: " + name);

                dispose(); // Close this page
                onFinish.run(); // Call the callback

            } catch (NumberFormatException ex) {
                showErrorDialog("Please enter valid numeric values for all fields.");
            } catch (Exception ex) {
                showErrorDialog("An unexpected error occurred. Please try again.");
                ex.printStackTrace();
            }
        });
    }

    public ApplyPage() {
        this(() -> {});
    }

    private JTextField createFormField(JPanel parent, String labelText) {
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));
        fieldPanel.setBackground(Color.WHITE);
        fieldPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        fieldPanel.setMaximumSize(new Dimension(400, 60));

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("SansSerif", Font.PLAIN, 16));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));

        JTextField field = new JTextField();
        field.setFont(new Font("SansSerif", Font.PLAIN, 16));
        field.setMaximumSize(new Dimension(400, 35));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));

        fieldPanel.add(label);
        fieldPanel.add(field);
        parent.add(fieldPanel);
        parent.add(Box.createRigidArea(new Dimension(0, 15)));

        return field;
    }

    private void styleButton(JButton button, Color bgColor) {
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.BOLD, 18));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
                BorderFactory.createEmptyBorder(10, 25, 10, 25)
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(this,
                "<html><div style='text-align: center;'>" + message + "</div></html>",
                "Application Error",
                JOptionPane.ERROR_MESSAGE);
    }

    private void showSuccessDialog(String message) {
        JOptionPane.showMessageDialog(this,
                "<html><div style='text-align: center;'>" + message + "</div></html>",
                "Application Submitted",
                JOptionPane.INFORMATION_MESSAGE);
    }
}