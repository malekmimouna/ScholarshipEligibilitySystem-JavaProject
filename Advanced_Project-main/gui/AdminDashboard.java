package gui;

import database.DatabaseHelper;
import model.Scholarship;
import model.Student;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AdminDashboard extends JFrame {

    public AdminDashboard() {
        setTitle("Admin Dashboard");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Main content panel with padding
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100));
        mainPanel.setBackground(new Color(240, 245, 249));

        // Header
        JLabel title = new JLabel("👨‍💼 Admin Dashboard", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 36));
        title.setForeground(new Color(63, 81, 181));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 50, 10));
        mainPanel.add(title, BorderLayout.NORTH);

        // Center panel with buttons
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(new Color(240, 245, 249));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Eligible Students Button
        JButton eligibleBtn = createButton("View Eligible Students", new Color(76, 175, 80), 24);
        eligibleBtn.setPreferredSize(new Dimension(350, 70));
        eligibleBtn.addActionListener(e -> showEligibleStudents());
        gbc.gridy = 0;
        centerPanel.add(eligibleBtn, gbc);

        // All Students Button
        JButton allBtn = createButton("View All Students", new Color(33, 150, 243), 24);
        allBtn.setPreferredSize(new Dimension(350, 70));
        allBtn.addActionListener(e -> showAllStudents());
        gbc.gridy = 1;
        centerPanel.add(allBtn, gbc);

        // Add Scholarship Button
        JButton addBtn = createButton("Add a Scholarship", new Color(255, 152, 0), 24);
        addBtn.setPreferredSize(new Dimension(350, 70));
        addBtn.addActionListener(e -> showAddScholarshipDialog());
        gbc.gridy = 2;
        centerPanel.add(addBtn, gbc);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Footer with logout button
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setBackground(new Color(240, 245, 249));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        JButton logoutBtn = new JButton("Logout");
        styleLogoutButton(logoutBtn);
        footerPanel.add(logoutBtn);

        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    private JButton createButton(String text, Color bgColor, int fontSize) {
        JButton btn = new JButton(text);
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("SansSerif", Font.BOLD, fontSize));
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(15, 25, 15, 25)
        ));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void styleLogoutButton(JButton button) {
        button.setBackground(new Color(120, 144, 156));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("SansSerif", Font.BOLD, 18));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(10, 30, 10, 30)
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(e -> {
            new LandingPage();
            dispose();
        });
    }

    private void showEligibleStudents() {
        List<Student> all = DatabaseHelper.getAllStudents();
        StringBuilder msg = new StringBuilder("<html><h2>🎓 Eligible Students</h2><ul>");
        for (Student s : all) {
            if (!DatabaseHelper.getEligibleScholarships(s).isEmpty()) {
                msg.append("<li><b>").append(s.getName()).append("</b> (GPA: ")
                        .append(s.getGpa()).append(")</li>");
            }
        }
        msg.append("</ul></html>");

        JTextPane textPane = new JTextPane();
        textPane.setContentType("text/html");
        textPane.setText(msg.toString());
        textPane.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setPreferredSize(new Dimension(500, 300));

        JOptionPane.showMessageDialog(this, scrollPane, "Eligible Students", JOptionPane.PLAIN_MESSAGE);
    }

    private void showAllStudents() {
        List<Student> all = DatabaseHelper.getAllStudents();
        StringBuilder msg = new StringBuilder("<html><h2>👥 All Students</h2><table border='1' cellpadding='5'>");
        msg.append("<tr><th>Name</th><th>GPA</th><th>Income</th><th>Age</th></tr>");

        for (Student s : all) {
            msg.append("<tr>")
                    .append("<td>").append(s.getName()).append("</td>")
                    .append("<td>").append(s.getGpa()).append("</td>")
                    .append("<td>").append(s.getFamilyIncome()).append("</td>")
                    .append("<td>").append(s.getAge()).append("</td>")
                    .append("</tr>");
        }
        msg.append("</table></html>");

        JTextPane textPane = new JTextPane();
        textPane.setContentType("text/html");
        textPane.setText(msg.toString());
        textPane.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        JOptionPane.showMessageDialog(this, scrollPane, "All Students", JOptionPane.PLAIN_MESSAGE);
    }

    private void showAddScholarshipDialog() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField nameField = new JTextField(20);
        JTextField gpaField = new JTextField(20);
        JTextField incomeField = new JTextField(20);
        JTextField minAgeField = new JTextField(20);
        JTextField maxAgeField = new JTextField(20);

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Scholarship Name:"), gbc);
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Minimum GPA:"), gbc);
        gbc.gridx = 1;
        panel.add(gpaField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Maximum Family Income:"), gbc);
        gbc.gridx = 1;
        panel.add(incomeField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Minimum Age:"), gbc);
        gbc.gridx = 1;
        panel.add(minAgeField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Maximum Age:"), gbc);
        gbc.gridx = 1;
        panel.add(maxAgeField, gbc);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Add New Scholarship",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            try {
                Scholarship s = new Scholarship(
                        nameField.getText().trim(),
                        Double.parseDouble(gpaField.getText().trim()),
                        Double.parseDouble(incomeField.getText().trim()),
                        Integer.parseInt(minAgeField.getText().trim()),
                        Integer.parseInt(maxAgeField.getText().trim())
                );
                DatabaseHelper.insertScholarship(s);
                JOptionPane.showMessageDialog(this, "✅ Scholarship added successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "❌ Invalid input. Please check all fields.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}