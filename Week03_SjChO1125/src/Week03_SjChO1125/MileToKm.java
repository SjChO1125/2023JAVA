package Week03_SjChO1125;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MileToKm extends JFrame {

    private JTextField mileTextField;
    private JTextField kmTextField;

    public MileToKm() {
        setTitle("Mile => Km");
        setSize(300, 150);

        JPanel panel = new JPanel();
        add(panel);

        panel.add(new JLabel("마일을 입력하시오"));
        mileTextField = new JTextField(20);
        panel.add(mileTextField);

        panel.add(new JLabel("=>"));
        kmTextField = new JTextField(20);
        panel.add(kmTextField);

        JButton ch = new JButton("변환");
        panel.add(ch);

        // Add an ActionListener to the button
        ch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the value from the mileTextField
                String mileText = mileTextField.getText();

                try {
                    // Convert the mile value to kilometers
                    double mile = Double.parseDouble(mileText);
                    double kilometer = mile * 1.60934;

                    // Display the result in the kmTextField
                    kmTextField.setText(String.format("%.2f", kilometer));
                } catch (NumberFormatException ex) {
                    kmTextField.setText("유효한 숫자를 입력하세요.");
                }
            }
        });

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        // Create an instance of the MileToKm class
        new MileToKm();
    }
}
