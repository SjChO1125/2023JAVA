package 과제sjChO1125;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class GameResultsDisplay extends JFrame {

    public GameResultsDisplay(Map<Integer, String> results) {
        super("게임 결과");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        displayResults(textArea, results);

        ImageIcon imageIcon = new ImageIcon("복단이집도착.png");
        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(getWidth(), getHeight() / 2, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImage);

        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(imageLabel, BorderLayout.SOUTH);

        JLabel additionalLabel = new JLabel("0초=도착X");
        additionalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(additionalLabel, BorderLayout.NORTH);

        Font font = new Font("맑은 고딕", Font.BOLD, 18);
        textArea.setFont(font);
        textArea.setBackground(new Color(255, 255, 204));
        textArea.setForeground(Color.DARK_GRAY);

        additionalLabel.setFont(font);
        additionalLabel.setForeground(Color.RED);

        imageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        additionalLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private void displayResults(JTextArea textArea, Map<Integer, String> results) {
        StringBuilder resultMessage = new StringBuilder();

        // Calculate the user with the shortest elapsed time (excluding 0 seconds and not arrived)
        int fastestUser = calculateFastestUser(results);

        // Display information for the fastest user at the top
        if (fastestUser != -1) {
            long fastestTime = GameResults.getElapsedTime(fastestUser);
            resultMessage.append("가장 빨리 도착한 사람은?!-> 사용자 ").append(fastestUser)
                          .append(": ").append(fastestTime).append("초\n\n");
        }

        // Display the rest of the results
        resultMessage.append("최종 결과\n");
        for (Map.Entry<Integer, String> entry : results.entrySet()) {
            int gameNumber = entry.getKey();
            String elapsedTimeString = entry.getValue();

            if (!elapsedTimeString.equals("0초") && !elapsedTimeString.equals("도착하지 못함")) {
                resultMessage.append("사용자 ").append(gameNumber).append(": ");

                long elapsedTime = GameResults.getElapsedTime(gameNumber);
                resultMessage.append(elapsedTime).append("초");

                resultMessage.append("\n");
            }
        }

        textArea.setText(resultMessage.toString());
    }

    private int calculateFastestUser(Map<Integer, String> results) {
        int fastestUser = -1;
        long fastestTime = Long.MAX_VALUE;

        for (Map.Entry<Integer, String> entry : results.entrySet()) {
            int gameNumber = entry.getKey();
            String elapsedTimeString = entry.getValue();

            // "도착하지 못함"인 경우는 계산에서 제외
            if (!elapsedTimeString.equals("도착하지 못함")) {
                long elapsedTime = GameResults.getElapsedTime(gameNumber);

                // 0 이상인 경우에만 계산에 포함
                if (elapsedTime > 0 && elapsedTime < fastestTime) {
                    fastestTime = elapsedTime;
                    fastestUser = gameNumber;
                }
            }
        }

        return fastestUser;
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            int totalGames = 5;
            Map<Integer, String> results = GameResults.getResultMap(totalGames);
            
            // Filter out entries with 0 seconds
            results.entrySet().removeIf(entry -> entry.getValue().equals("0초"));

            new GameResultsDisplay(results).setVisible(true);
        });
    }
}