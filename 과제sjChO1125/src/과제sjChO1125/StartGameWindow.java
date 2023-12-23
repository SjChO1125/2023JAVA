package 과제sjChO1125;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class StartGameWindow extends JFrame {

    public StartGameWindow() {
        super("복단의 비오는 날 집가기");

        JButton startButton = new JButton("게임하기");
        startButton.addActionListener(e -> {
            dispose();  // Close the current window
            new RainGame().setVisible(true);  // Start the RainGame
        });

        // Set the preferred size of the button based on the font size
        Font defaultFont = UIManager.getDefaults().getFont("Label.font");
        int fontSize = defaultFont.getSize();
        startButton.setPreferredSize(new java.awt.Dimension(fontSize * 6, fontSize * 2));

        // Set font to the system default font
        startButton.setFont(defaultFont);

        // Set button background color to navy, foreground (text) color to white
        startButton.setBackground(Color.BLUE);
        startButton.setForeground(Color.WHITE);

        // Make the button border rounded
        Border roundedBorder = new EtchedBorder(EtchedBorder.RAISED, Color.WHITE, Color.BLUE);
        startButton.setBorder(roundedBorder);

        JPanel panel = createContentPane("배경2.png");
        panel.setLayout(new BorderLayout());
        panel.add(startButton, BorderLayout.SOUTH);

        add(panel);
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private JPanel createContentPane(String imagePath) {
        try {
            Image backgroundImage = ImageIO.read(new File(imagePath));
            JPanel backgroundPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            };
            return backgroundPanel;
        } catch (IOException e) {
            e.printStackTrace();
            return new JPanel(); // Default panel if image loading fails
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StartGameWindow().setVisible(true);
        });
    }
}
