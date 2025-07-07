import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {
    private JTextField inputField;
    private JButton checkButton;
    private JLabel resultLabel;

    private SpamFilter spamFilter;

    public MainFrame() {
        setTitle("Dynamic Spam Filter");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        inputField = new JTextField();
        checkButton = new JButton("Check Message");
        resultLabel = new JLabel("Result: ", SwingConstants.CENTER);

        // Load training data
        spamFilter = new SpamFilter();
        spamFilter.train("spam.txt", "ham.txt");  // Ensure these files exist in project root

        add(new JLabel("Enter message:", SwingConstants.CENTER));
        add(inputField);
        add(checkButton);
        add(resultLabel);

        // Action listener
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userMessage = inputField.getText().trim();
                if (!userMessage.isEmpty()) {
                    String result = spamFilter.classify(userMessage);
                    resultLabel.setText("Result: " + result);
                } else {
                    resultLabel.setText("Please enter a message.");
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
