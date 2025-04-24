package gui;

import model.Scholarship;
import database.DatabaseHelper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class ScholarshipInfoPage extends JFrame {

    public ScholarshipInfoPage() {
        setTitle("Available Scholarships");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Main panel with padding
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(30, 50, 30, 50));
        mainPanel.setBackground(new Color(240, 245, 249));

        // Header
        JLabel title = new JLabel("🏆 Available Scholarships", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 36));
        title.setForeground(new Color(63, 81, 181));
        title.setBorder(new EmptyBorder(0, 0, 30, 0));
        mainPanel.add(title, BorderLayout.NORTH);

        // Scholarship cards container
        JPanel cardsPanel = new JPanel();
        cardsPanel.setLayout(new BoxLayout(cardsPanel, BoxLayout.Y_AXIS));
        cardsPanel.setBackground(new Color(240, 245, 249));

        List<Scholarship> scholarships = DatabaseHelper.getAllScholarships();
        if (scholarships.isEmpty()) {
            JLabel noScholarships = new JLabel("No scholarships available at this time", SwingConstants.CENTER);
            noScholarships.setFont(new Font("SansSerif", Font.ITALIC, 18));
            noScholarships.setForeground(Color.GRAY);
            cardsPanel.add(noScholarships);
        } else {
            for (Scholarship s : scholarships) {
                cardsPanel.add(createScholarshipCard(s));
                cardsPanel.add(Box.createRigidArea(new Dimension(0, 20)));
            }
        }

        // Scroll pane for scholarship cards
        JScrollPane scrollPane = new JScrollPane(cardsPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Footer with action button
        JPanel footer = new JPanel();
        footer.setBackground(new Color(240, 245, 249));
        footer.setBorder(new EmptyBorder(20, 0, 0, 0));

        JButton nextButton = new JButton("Proceed to Application");
        styleButton(nextButton, new Color(76, 175, 80));
        nextButton.setPreferredSize(new Dimension(300, 50));
        nextButton.addActionListener(e -> {
            new ApplyPage(() -> new LandingPage());
            dispose();
        });

        footer.add(nextButton);
        mainPanel.add(footer, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    private JPanel createScholarshipCard(Scholarship scholarship) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        card.setBackground(Color.WHITE);
        card.setMaximumSize(new Dimension(800, Integer.MAX_VALUE));

        // Scholarship name
        JLabel nameLabel = new JLabel("🎓 " + scholarship.getName());
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        nameLabel.setForeground(new Color(33, 150, 243));
        card.add(nameLabel, BorderLayout.NORTH);

        // Details panel
        JPanel detailsPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        detailsPanel.setBorder(new EmptyBorder(15, 10, 0, 0));
        detailsPanel.setBackground(Color.WHITE);

        addDetail(detailsPanel, "Minimum GPA:", String.valueOf(scholarship.getMinGPA()));
        addDetail(detailsPanel, "Maximum Family Income:", "$" + scholarship.getMaxIncome());
        addDetail(detailsPanel, "Age Range:", scholarship.getMinAge() + " - " + scholarship.getMaxAge());

        card.add(detailsPanel, BorderLayout.CENTER);

        return card;
    }

    private void addDetail(JPanel panel, String label, String value) {
        JPanel detailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        detailPanel.setBackground(Color.WHITE);

        JLabel detailLabel = new JLabel(label);
        detailLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        detailLabel.setForeground(Color.DARK_GRAY);

        JLabel detailValue = new JLabel(value);
        detailValue.setFont(new Font("SansSerif", Font.PLAIN, 14));

        detailPanel.add(detailLabel);
        detailPanel.add(detailValue);
        panel.add(detailPanel);
    }

    private void styleButton(JButton button, Color bgColor) {
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.BOLD, 18));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(10, 30, 10, 30)
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}