package test.ex07;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// main 스레드를 GUI 프로그램에서는 ui 스레드라고 부른다.
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
        setLayout(null); // absoulte 레이아웃 (자유롭게 그림을 그릴 수 있다)
        setLocationRelativeTo(null); // JFrame 가운데 배치하기
        setDefaultCloseOperation(EXIT_ON_CLOSE); // x버튼으로 창을 끌 때 JVM 같이 종료하기
    }

    private void initListener() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // System.out.println(e.getKeyCode());

                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    if (!player.isRight() && !player.isLeftWallCrash()) {
                        player.right();
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    System.out.println("왼쪽키 누르는 중");
                    if (!player.isLeft() && !player.isLeftWallCrash()) {
                        player.left();
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    // 상태가 up이랑 down이 아닌 상태일때만 점프가 가능하다
                    if (!player.isUp() && !player.isDown()) {
                        player.up();
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (!player.isDown()) {
                        player.down();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    player.setRight(false);
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    player.setLeft(false);
                }
            }
        });
    }

    public static void main(String[] args) {
        new BubbleFrame();
    }
}
