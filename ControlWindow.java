import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControlWindow extends JPanel implements ActionListener, KeyListener {
    private Ball ball = new Ball(Constant.WIDTH / 2, Constant.HEIGHT / 2, 30);
    private Timer timer = new Timer(30, this);
    private Player leftPlayer = new Player(0, Constant.HEIGHT / 2);
    private Player rightPlayer = new Player(Constant.WIDTH - 40, Constant.HEIGHT / 2);
    private Font gameFont = new Font("Consolas", Font.BOLD, 30);

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.blue);
        g.fillRect(leftPlayer.x, leftPlayer.y, leftPlayer.width, leftPlayer.height);

        g.setColor(Color.red);
        g.fillRect(rightPlayer.x, rightPlayer.y, rightPlayer.width, rightPlayer.height);

        g.setColor(Color.white);
        g.fillOval(ball.x, ball.y, ball.diameter, ball.diameter);

        g.setColor(Color.blue);
        g.setFont(gameFont);
        g.drawString("LEFT: " + leftPlayer.score, 0, 40);
        g.drawString("RIGHT: " + leftPlayer.score, Constant.WIDTH - 200, 40);
    }

    // Method to make the ball bounce back
    public void actionPerformed(ActionEvent e) {
        ball.x += ball.speedX;
        ball.y += ball.speedY;

        Rectangle rectBall = new Rectangle(ball.x, ball.y, ball.diameter, ball.diameter);
        Rectangle rectLp = new Rectangle(leftPlayer.x, leftPlayer.y, leftPlayer.width, leftPlayer.height);
        Rectangle rectRp = new Rectangle(rightPlayer.x, rightPlayer.y, rightPlayer.width, rightPlayer.height);

        if (rectBall.intersects(rectRp)) {
            ball.speedX = -Math.abs(ball.speedX);
        }

        if (rectBall.intersects(rectLp)) {
            ball.speedX = Math.abs(ball.speedX);
        }

        // Make the ball bouncing back when reach the margin
        if (ball.y >= Constant.HEIGHT - ball.diameter * 2) {
            ball.speedY *= -1;
        }

        if (ball.y <= 0) {
            ball.speedY *= -1;
        }

        if (ball.x <= 0) {
            rightPlayer.score++;
            ball.x = Constant.WIDTH / 2;
            ball.y = Constant.HEIGHT / 2;
        }

        if (ball.x >= Constant.WIDTH - ball.diameter * 2) {
            leftPlayer.score++;
            ball.x = Constant.WIDTH / 2;
            ball.y = Constant.HEIGHT / 2;
        }
        repaint();
    }

    public void keyPressed(KeyEvent e) {
        // WS
        // UP DOWN
        if (e.getKeyCode() == KeyEvent.VK_W) {
            leftPlayer.y -= leftPlayer.speedY;
        }

        if (e.getKeyCode() == KeyEvent.VK_S) {
            leftPlayer.y += leftPlayer.speedY;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            rightPlayer.y -= rightPlayer.speedY;
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            rightPlayer.y += rightPlayer.speedY;
        }
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }

    public ControlWindow() {
        timer.start();
        this.setBackground(Color.black);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow(true);
        this.addKeyListener(this);
    }
}