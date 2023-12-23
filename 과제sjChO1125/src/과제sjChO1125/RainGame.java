package 과제sjChO1125;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class RainGame extends JFrame implements KeyListener, Runnable {

    private Image manImage;
    private Image houseImage;
    private Image backgroundImage;

    private int manX = 50;  // 변경: 초기 복단이 위치 조정
    private int houseX = 700;  // 변경: 초기 집 위치 조정
    private boolean arrived = false;
    private long startTime;
    private CopyOnWriteArrayList<Raindrop> raindrops = new CopyOnWriteArrayList<>();
    private boolean gameInProgress = false;

    private int totalGames;
    private int currentGame;

    private Thread gameThread;

    private float backgroundAlpha = 1.0f; // Initial alpha value

    public RainGame() {
        super("복단이의 비오는날 집가기");
        setSize(800, 600);  // 변경: 초기 게임 창 크기 조정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        addKeyListener(this);

        loadImages(); // 이미지 로드 메서드 호출
        askNumberOfPlayers();

        setContentPane(createContentPane());
    }

    private void loadImages() {
        try {
            manImage = ImageIO.read(new File("복단이.png"));
            houseImage = ImageIO.read(new File("집.png"));
            backgroundImage = ImageIO.read(new File("배경.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JPanel createContentPane() {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawBackground(g);

                g.drawImage(manImage, manX, 430, 40, 40, this);  // 변경: 복단이 이미지 크기 조정

                for (Raindrop raindrop : raindrops) {
                    g.setColor(Color.CYAN);
                    int raindropSize = 10; // 변경: 비 이미지 크기 조정
                    g.fillOval(raindrop.getX(), raindrop.getY(), raindropSize, raindropSize);
                }

                g.drawImage(houseImage, houseX, 350, 60, 160, this);  // 변경: 집 이미지 크기 조정
            }
        };
    }

    private void drawBackground(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    private void askNumberOfPlayers() {
        Object[] options = {"혼자하기", "n명이서 하기"};

        try {
            Image iconImage = ImageIO.read(new File("복단이.png"));
            Image scaledIcon = iconImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(scaledIcon);

            int choice = JOptionPane.showOptionDialog(
                    this,
                    "게임을 몇 명이서 진행하시겠습니까?",
                    "플레이어 선택",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    icon,
                    options,
                    options[1]);

            if (choice == JOptionPane.YES_OPTION) {
                totalGames = 1;
                startGame();
            } else if (choice == JOptionPane.NO_OPTION) {
                askNumberOfPlayersMulti();
            } else {
                System.exit(0);
            }

            // 게임이 시작된 시점부터 시간을 측정할 수 있도록 startTime 초기화
            startTime = System.currentTimeMillis();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void askNumberOfPlayersMulti() {
        String input = JOptionPane.showInputDialog(this, "몇 명이서 진행하시겠습니까?");
        try {
            totalGames = Integer.parseInt(input);
            if (totalGames <= 0) {
                JOptionPane.showMessageDialog(this, "잘못된 입력입니다. 1 이상의 숫자를 입력해주세요.");
                askNumberOfPlayersMulti();
            } else {
                startGame();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "숫자를 입력해주세요.");
            askNumberOfPlayersMulti();
        }
    }

    public void startGame() {
        currentGame = 0;
        gameInProgress = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {
        while (currentGame < totalGames) {
            if (gameInProgress) {
                if (!arrived) {
                    update();
                    repaint();
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        showGameResults();
    }

    private void showGameResults() {
        Map<Integer, String> results = new HashMap<>();

        for (int i = 0; i < totalGames; i++) {
            long elapsedTime = calculateElapsedTime();
            if (manX + 40 >= houseX) {  // 변경: 이미지 크기 고려하여 위치 보정
                results.put(i + 1, elapsedTime + "초");
            } else {
                results.put(i + 1, "도착하지 못했습니다.");
            }
        }

        // 결과를 보여주는 창 생성 및 게임 결과 전달
        GameResultsDisplay resultsDisplay = new GameResultsDisplay(results);
        resultsDisplay.setVisible(true);
    }

    private void update() {
        createRaindrop();
        moveRaindrops();

        for (Raindrop raindrop : raindrops) {
            int manBottom = 430;  // 변경: 복단이 아래쪽 좌표
            int manSize = 40;  // 변경: 복단이 이미지 크기
            int raindropSize = 10;  // 변경: 비 이미지 크기

            // 비와 복단이의 충돌 판정
            if (
                    raindrop.getY() + raindropSize >= manBottom &&
                            raindrop.getY() <= manBottom + manSize &&
                            raindrop.getX() + raindropSize >= manX &&
                            raindrop.getX() <= manX + manSize
            ) {
                raindrops.remove(raindrop);
                handleGameEnd();
                return;
            }
        }

        if (manX + 40 >= houseX && !arrived) {  // 변경: 이미지 크기 고려하여 위치 보정
            handleGameEnd();
        }
    }

    private void createRaindrop() {
        if (Math.random() < 0.08) {
            int rainX = (int) (Math.random() * getWidth());
            int rainY = 0;
            Raindrop raindrop = new Raindrop(rainX, rainY);
            raindrops.add(raindrop);
        }
    }

    private void moveRaindrops() {
        for (Raindrop raindrop : raindrops) {
            raindrop.move();
            if (raindrop.getY() > getHeight()) {
                raindrops.remove(raindrop);
            }
        }
    }

    private void handleGameEnd() {
        String message;
        long elapsedTime;

        if (manX + 40 >= houseX && startTime == 0) {
            startTime = System.currentTimeMillis();
        }

        if (manX + 40 >= houseX) {
            elapsedTime = calculateElapsedTime();
            message = "도착했습니다! 소요 시간: " + elapsedTime + "초";
            arrived = true;
        } else {
            elapsedTime = 0;
            message = "도착하지 못했습니다.";
        }

        // GameResults.setResult 호출 위치 수정
        GameResults.setResult(currentGame + 1, elapsedTime, arrived);

        showMessage(message);

        if (totalGames == 1) {
            int option = JOptionPane.showOptionDialog(
                    this,
                    "다시 시도하시겠습니까?",
                    "게임 종료",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new Object[]{"Yes", "No"},
                    "No"
            );

            if (option == JOptionPane.YES_OPTION) {
                restartGame();
            } else {
                System.exit(0);
            }
        } else {
            currentGame++;

            if (currentGame < totalGames) {
                restartGame();
            } else {
                showGameResults();
            }
        }
    }


    private void restartGame() {
        manX = 50;
        houseX = 700;
        arrived = false;
        startTime = System.currentTimeMillis();
        raindrops.clear();
    }

    private long calculateElapsedTime() {
        long currentTime = System.currentTimeMillis();
        if (arrived) {
            long endTime = currentTime;
            long elapsedTime = (endTime - startTime) / 1000;
            GameResults.setResult(currentGame + 1, elapsedTime, arrived);
            return elapsedTime;
        } else {
            return (currentTime - startTime) / 1000;
        }
    }


    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void paint(Graphics g) {
        super.paint(g);
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT && manX > 0) {
            manX -= 10;
        } else if (key == KeyEvent.VK_RIGHT && manX < getWidth() - 30) {
            manX += 10;
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    private static class Raindrop {
        private int x;
        private int y;
        private int speed; // 추가된 변수: 비의 속도

        public Raindrop(int x, int y) {
            this.x = x;
            this.y = y;
            this.speed = 8; // 비의 기본 속도
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void move() {
            y += speed; // 비가 내리는 속도 적용
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RainGame().setVisible(true);
        });
    }
}