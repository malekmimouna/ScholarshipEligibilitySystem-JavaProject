package gui;

import javax.swing.*;
import java.awt.*;

public class LandingPage extends JFrame {

    public LandingPage() {
        setTitle("🎓 Scholarship Eligibility System");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Make window full screen
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Main content panel with padding
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
        mainPanel.setBackground(new Color(240, 245, 249));

        JLabel title = new JLabel("Welcome to Scholarship System", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 36));
        title.setForeground(new Color(33, 150, 243));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 50, 10));
        mainPanel.add(title, BorderLayout.NORTH);

        // Buttons panel with improved layout
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(new Color(240, 245, 249));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton applyButton = new JButton("Apply for Scholarship");
        styleButton(applyButton, new Color(76, 175, 80), 24);
        applyButton.setPreferredSize(new Dimension(300, 60));
        applyButton.addActionListener(e -> {
            new ScholarshipInfoPage();
            dispose();
        });

        JButton adminLoginButton = new JButton("Admin Login");
        styleButton(adminLoginButton, new Color(244, 67, 54), 24);
        adminLoginButton.setPreferredSize(new Dimension(300, 60));
        adminLoginButton.addActionListener(e -> {
            new LoginPage();
            dispose();
        });

        gbc.gridy = 0;
        buttonPanel.add(applyButton, gbc);
        gbc.gridy = 1;
        buttonPanel.add(adminLoginButton, gbc);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Footer
        JLabel footer = new JLabel("© 2023 Scholarship Eligibility System", SwingConstants.CENTER);
        footer.setFont(new Font("SansSerif", Font.PLAIN, 14));
        footer.setForeground(Color.GRAY);
        footer.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        mainPanel.add(footer, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    private void styleButton(JButton button, Color bgColor, int fontSize) {
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("SansSerif", Font.BOLD, fontSize));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(10, 25, 10, 25)
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}