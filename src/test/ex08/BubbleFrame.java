package test.ex08;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

// main 스레드를 GUI 프로그램에서는 ui 스레드라고 부른다.
public class BubbleFrame extends JFrame {

    private JLabel backgroundMap;
    private Player player;

    public BubbleFrame() {
        initObject();
        initSetting();
        initListener();
        setVisible(true); // while
    }

    private void initObject() {
        backgroundMap = new JLabel(new ImageIcon("image/backgroundMapService.png"));
        setContentPane(backgroundMap);
        player = new Player();
        add(player);
    }

    private void initSetting() {
        setSize(1000, 640);
        setLayout(null); // absoulte 레이아웃 (자유롭게 그림을 그릴 수 있다)
        setLocationRelativeTo(null); // JFrame 가운데 배치하기
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x버튼으로 창을 끌 때 JVM 같이 종료하기
    }

    private void initListener() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // System.out.println(e.getKeyCode());

                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    System.out.println("왼쪽키 누르는 중");
                    // 너 지금 왼쪽방향 보고 있으면 안되고
                    // 너 지금 왼쪽에 벽에 부딛치면 안되고
                    // 위에것이 둘다 아니면 가게 해줄께
                    if (!player.isLeft() && !player.isLeftWallCrash()) {
                        player.left();
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    if (!player.isRight() && !player.isRightWallCrash()) {
                        player.right();
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    // up = false
                    if (!player.isUp() && !player.isDown()) {
                        player.up();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    player.setLeft(false);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    player.setRight(false);
                }
            }
        });
    }

    public static void main(String[] args) {
        new BubbleFrame();
    }
}