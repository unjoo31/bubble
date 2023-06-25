package test.ex07;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player extends JLabel {

    // 위치 상태
    private int x;
    private int y;

    // 플레이어의 방향 상태
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;

    // 플레이어의 스피드(스피드는 고정되어 있어야 하기 때문에 상수화한다)
    private final int SPEED = 10; // x축
    private final int JUMPSPEED = 4; // y축

    // 벽에 충돌한 상태
    private boolean leftWallCrash;
    private boolean rightWallCrash;

    private ImageIcon playerR, playerL;

    public Player() {
        initObject(); // new 하는거 먼저
        initSetting();
        new Thread(new BackgroundPlayerService(this)).start();
    }

    private void initObject() {
        playerR = new ImageIcon("image/playerR.png");
        playerL = new ImageIcon("image/playerL.png");
    }

    private void initSetting() {
        x = 70;
        y = 535;

        left = false;
        right = false;
        up = false;
        down = false;

        setIcon(playerR);
        setSize(50, 50);
        setLocation(x, y);
    }

    // right, left 움직임을 부드럽게 하기 위해서 Thread 만들기
    public void right() {
        System.out.println("right 메서드 실행됨");
        right = true;
        new Thread(() -> {
            while (right) {
                setIcon(playerR);
                x = x + SPEED;
                setLocation(x, y);

                try {
                    Thread.sleep(10); // 0.01초
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void left() {
        System.out.println("left 메서드 실행됨");
        left = true;
        new Thread(() -> {
            while (left) {
                setIcon(playerL);
                x = x - SPEED;
                setLocation(x, y);

                try {
                    Thread.sleep(10); // 0.01초
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    // up, down
    public void up() {
        System.out.println("up 메서드 실행됨");
        up = true;
        new Thread(() -> {
            // for문이 한번 돌때 4씩 이동한다 (4*30=120)
            for (int i = 0; i < 30; i++) {
                // 스피드가 4
                y = y - JUMPSPEED;
                setLocation(x, y);

                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            up = false;
            down();
        }).start();
    }

    public void down() {
        System.out.println("down 메서드 실행됨");
        down = true;
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                // 스피드가 4
                y = y + JUMPSPEED;
                setLocation(x, y);

                try {
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            down = false;
        }).start();
    }
}
