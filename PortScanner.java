import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.Socket;

public class PortScanner extends JFrame {

    private JTextField ipField;
    private JTextArea outputArea;

    public PortScanner() {
        setTitle("Cyber Security Tool - Network & Port Scanner");
        setSize(600, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // centriranje prozora
        getContentPane().setBackground(Color.decode("#222222"));

        initUI();
    }

    private void initUI() {
        // Glavni panel sa BoxLayout za vertikalno slaganje i centriranje
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.decode("#222222"));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Naslov
        JLabel titleLabel = new JLabel("Cyber Security - Network & Port Scanner");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(titleLabel);

        // Unos IP adrese
        JLabel ipLabel = new JLabel("Enter Target IP:");
        ipLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        ipLabel.setForeground(Color.WHITE);
        ipLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(ipLabel);

        ipField = new JTextField(20);
        ipField.setMaximumSize(new Dimension(250, 30));
        ipField.setFont(new Font("Arial", Font.PLAIN, 14));
        ipField.setHorizontalAlignment(JTextField.CENTER);
        mainPanel.add(ipField);

        // Dugmad u horizontalnoj liniji, centrirana
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBackground(Color.decode("#222222"));

        JButton scanButton = new JButton("Scan Open Ports");
        scanButton.setFont(new Font("Arial", Font.BOLD, 12));
        scanButton.setBackground(Color.RED);
        scanButton.setForeground(Color.WHITE);

        JButton getIpButton = new JButton("Get My IP");
        getIpButton.setFont(new Font("Arial", Font.BOLD, 12));
        getIpButton.setBackground(Color.BLUE);
        getIpButton.setForeground(Color.WHITE);

        scanButton.addActionListener(e -> scanPorts());
        getIpButton.addActionListener(e -> getMyIp());

        buttonPanel.add(scanButton);
        buttonPanel.add(getIpButton);

        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(buttonPanel);

        // Tekstualni izlaz sa skrolom
        outputArea = new JTextArea();
        outputArea.setFont(new Font("Arial", Font.PLAIN, 13));
        outputArea.setBackground(Color.BLACK);
        outputArea.setForeground(Color.WHITE);
        outputArea.setEditable(false);
        outputArea.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setPreferredSize(new Dimension(550, 150));
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(scrollPane);

        add(mainPanel);
    }

    private void scanPorts() {
        String targetIp = ipField.getText().trim();
        if (targetIp.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a valid IP Address!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        outputArea.setText("Scanning ports on " + targetIp + "...\n");

        new Thread(() -> {
            for (int port = 20; port <= 1024; port++) {
                try (Socket socket = new Socket()) {
                    socket.connect(new java.net.InetSocketAddress(targetIp, port), 500);
                    int finalPort = port;
                    SwingUtilities.invokeLater(() ->
                            outputArea.append("Port " + finalPort + " is OPEN ✅\n"));
                } catch (Exception e) {
                    // Ignoriši zatvorene portove
                }
            }
        }).start();
    }

    private void getMyIp() {
        try {
            InetAddress ip = InetAddress.getLocalHost();
            JOptionPane.showMessageDialog(this, "Your IP: " + ip.getHostAddress(), "IP Address", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unable to get IP address", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PortScanner().setVisible(true));
    }
}
