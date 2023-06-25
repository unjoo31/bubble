package test.ex03;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BubbleFrame extends JFrame {

    private JLabel backgroundMap;
    private Player player;

    public BubbleFrame() {
        initObject();
        initSetting();
        initListener();
        setVisible(true);
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
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void initListener() {
        // public abstract class KeyAdapter implements KeyListener
        // keyListener인터페이스를 상속받은 KeyAdapter 추상 클래스 new
        addKeyListener(new KeyAdapter() {
            // KeyAdapter 추상클래스의 추상 메서드 재정의
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println(e.getKeyCode());

                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    player.right();
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    player.left();
                }
            }
        });
    }

    public static void main(String[] args) {
        new BubbleFrame();
    }
}
