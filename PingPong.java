import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PingPong extends JFrame {

    private ControlWindow controlWindow = new ControlWindow();

    public PingPong() {

        this.add(controlWindow);
        this.pack();
        this.setTitle("Ping Pong ");
        this.setSize(Constant.WIDTH, Constant.HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new PingPong();
    }
}

