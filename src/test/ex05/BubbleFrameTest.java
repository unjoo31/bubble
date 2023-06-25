package test.ex05;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BubbleFrameTest extends JFrame {

    private JLabel backgroundMap;
    private Player player;

    public BubbleFrameTest() {
        initObject();
        initSetting();
        setVisible(true);
        initListener();
    }

    private void initObject() {
        backgroundMap = new JLabel(new ImageIcon("image/backgroundMap.png"));
        player = new Player();

        setContentPane(backgroundMap);
        add(player);
    }

    private void initSetting() {
        setSize(1000, 640);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initListener() {
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                // System.out.println(e.getKeyCode());

                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    if (!player.isRight()) {
                        player.right();
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    if (!player.isLeft())
                        player.left();
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    if (!player.isUp() && !player.isDown()) {
                        player.up();
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (!player.isUp()) {
                        player.up();
                    }
                }
            }

            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    player.setRight(false);
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    player.setLeft(false);
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    player.setUp(false);
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    player.setDown(false);
                }
            }
        });
    }

    public static void main(String[] args) {
        new BubbleFrameTest();
    }
}
