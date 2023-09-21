package Week03_SjChO1125;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Car extends JFrame {
    int img_x = 150, img_y = 150;
    JLabel carLabel;

    public Car() {
        setSize(800,500);

        // 자동차 이미지를 표시할 JLabel 생성
        ImageIcon carIcon = new ImageIcon("C:/Users/DS/git/2023JAVA/Week03_SjChO1125/src/Week03_SjChO1125/CarImg.PNG");

        carLabel = new JLabel(carIcon);
        
        carLabel.setBounds(img_x, img_y, carIcon.getIconWidth(), carIcon.getIconHeight());

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JButton leftButton = new JButton("LEFT");
        JButton rightButton = new JButton("RIGHT");

        leftButton.setBounds(250, 400, 100, 50);
        rightButton.setBounds(400, 400, 100, 50);

        leftButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                moveLeft();
            }
        });

        rightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                moveRight();
            }
        });

        panel.add(leftButton);
        panel.add(rightButton);
        panel.add(carLabel);
        add(panel);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void moveLeft() {
        img_x -= 10;
        carLabel.setBounds(img_x, img_y, carLabel.getWidth(), carLabel.getHeight());
    }

    private void moveRight() {
        img_x += 10;
        carLabel.setBounds(img_x, img_y, carLabel.getWidth(), carLabel.getHeight());
    }

    public static void main(String[] args) {
        Car car = new Car();
    }
}

