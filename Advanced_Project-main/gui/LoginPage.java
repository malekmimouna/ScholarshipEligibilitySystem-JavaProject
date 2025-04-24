package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginPage extends JFrame {

    public LoginPage() {
        setTitle("Admin Login");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main container with background
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(240, 245, 249));

        // Login card panel
        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(40, 40, 40, 40)
        ));
        loginPanel.setBackground(Color.WHITE);
        loginPanel.setPreferredSize(new Dimension(500, 400));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        // Title
        JLabel title = new JLabel("🔒 Admin Login");
        title.setFont(new Font("SansSerif", Font.BOLD, 28));
        title.setForeground(new Color(63, 81, 181));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        loginPanel.add(title, gbc);

        // Username
        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        loginPanel.add(userLabel, gbc);

        JTextField usernameField = new JTextField(20);
        styleTextField(usernameField);
        gbc.gridx = 1;
        loginPanel.add(usernameField, gbc);

        // Password
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        loginPanel.add(passLabel, gbc);

        JPasswordField passwordField = new JPasswordField(20);
        styleTextField(passwordField);
        gbc.gridx = 1;
        loginPanel.add(passwordField, gbc);

        // Login button
        JButton loginBtn = new JButton("Login");
        styleButton(loginBtn, new Color(63, 81, 181));
        loginBtn.setPreferredSize(new Dimension(200, 40));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        loginPanel.add(loginBtn, gbc);

        // Add login panel to main panel
        mainPanel.add(loginPanel);
        add(mainPanel);

        // Login action
        loginBtn.addActionListener((ActionEvent e) -> {
            String user = usernameField.getText();
            String pass = new String(passwordField.getPassword());

            if (user.equals("admin") && pass.equals("admin")) {
                new AdminDashboard();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                        "❌ Invalid credentials. Please try again.",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }

    private void styleButton(JButton button, Color bgColor) {
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
                BorderFactory.createEmptyBorder(10, 25, 10, 25)
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void styleTextField(JTextField field) {
        field.setFont(new Font("SansSerif", Font.PLAIN, 16));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        field.setPreferredSize(new Dimension(250, 35));
    }
}