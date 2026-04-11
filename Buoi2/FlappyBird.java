import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    int boardWidth = 360;
    int boardHeight = 640;

    // Hinh anh
    Image backgroundImg;
    Image birdImg;
    Image topPipeImg;
    Image bottomPipeImg;

    // Khoi tao cac doi tuong tu class Bird va Pipe
    Bird bird;
    ArrayList<Pipe> pipes;
    Random random = new Random();

    Timer gameLoop;
    Timer pipeTimer;

    int score = 0;
    boolean gameOver = false;
    int pipeGap = 150;
    int pipeSpeed = 3;

    public FlappyBird() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setFocusable(true);
        addKeyListener(this);

        // Tai hinh anh
        backgroundImg = new ImageIcon("flappybirdbg.png").getImage();
        birdImg = new ImageIcon("flappybird.png").getImage();
        topPipeImg = new ImageIcon("toppipe.png").getImage();
        bottomPipeImg = new ImageIcon("bottompipe.png").getImage();

        bird = new Bird(boardHeight / 2);
        pipes = new ArrayList<>();

        // Hen gio tao ong moi
        pipeTimer = new Timer(1500, e -> placePipes());
        pipeTimer.start();

        // Vong lap game chinh
        gameLoop = new Timer(16, this);
        gameLoop.start();
    }

    public void placePipes() {
        if (gameOver) return;
        
        int topHeight = random.nextInt(boardHeight - pipeGap - 100) + 50;

        // Goi class Pipe tu file Pipe.java
        Pipe topPipe = new Pipe(boardWidth, 0, topHeight, topPipeImg, true);
        
        int bottomY = topHeight + pipeGap;
        int bottomHeight = boardHeight - bottomY;
        Pipe bottomPipe = new Pipe(boardWidth, bottomY, bottomHeight, bottomPipeImg, false);

        pipes.add(topPipe);
        pipes.add(bottomPipe);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(backgroundImg, 0, 0, boardWidth, boardHeight, null);

        for (Pipe pipe : pipes) {
            g.drawImage(pipe.getImg(), pipe.getX(), pipe.getY(), pipe.getWidth(), pipe.getHeight(), null);
        }

        g.drawImage(birdImg, bird.getX(), bird.getY(), bird.getWidth(), bird.getHeight(), null);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.drawString("Score: " + score, 10, 40);

        if (gameOver) {
            // Can giua dong GAME OVER
            String text1 = "GAME OVER";
            g.setColor(Color.RED);
            Font font1 = new Font("Arial", Font.BOLD, 36);
            g.setFont(font1);
            FontMetrics fm1 = g.getFontMetrics();
            int x1 = (boardWidth - fm1.stringWidth(text1)) / 2;
            g.drawString(text1, x1, boardHeight / 2 - 20);

            // Can giua dong Restart
            String text2 = "Press Space/ Enter to Restart";
            g.setColor(Color.WHITE);
            Font font2 = new Font("Arial", Font.BOLD, 20);
            g.setFont(font2);
            FontMetrics fm2 = g.getFontMetrics();
            int x2 = (boardWidth - fm2.stringWidth(text2)) / 2;
            g.drawString(text2, x2, boardHeight / 2 + 20);
        }
    }

    public void move() {
        // Su dung Setter va Getter de cap nhat van toc va toa do y
        bird.setVelocityY(bird.getVelocityY() + bird.getGravity());
        bird.setY(bird.getY() + (int)bird.getVelocityY());

        if (bird.getY() < 0) {
            bird.setY(0);
            bird.setVelocityY(0);
        }

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            // Su dung Setter de thay doi toa do x (ong di chuyen qua trai)
            pipe.setX(pipe.getX() - pipeSpeed);

            // Kiem tra cong diem
            if (!pipe.isPassed() && bird.getX() > pipe.getX() + pipe.getWidth()) {
                pipe.setPassed(true);
                if (pipe.isTopPipe()) score++;
            }

            // Kiem tra va cham
            if (bird.getRect().intersects(pipe.getRect())) {
                gameOver = true;
            }
        }

        if (bird.getY() >= boardHeight) {
            gameOver = true;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            move();
            repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (gameOver) {
                bird.setY(boardHeight / 2);
                bird.setVelocityY(0);
                pipes.clear();
                score = 0;
                gameOver = false;
            } else {
                bird.setVelocityY(bird.getJumpStrength());
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Flappy Bird");
        FlappyBird panel = new FlappyBird();
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}