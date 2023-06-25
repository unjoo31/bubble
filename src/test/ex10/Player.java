package test.ex10;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

// class Player -> new 가능한 애들!! 게임에 존재할 수 있음. (추상메서드를 가질 수 없다.)

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

    // 플레이어의 스피드
    private final int SPEED = 10; // x 축
    private final int JUMPSPEED = 4; // y 축

    // 벽에 충돌한 상태
    private boolean leftWallCrash;
    private boolean rightWallCrash;

    private ImageIcon playerR, playerL;

    public Player() {
        initObject();
        initSetting();
        new Thread(new BackgroundPlayerService(this)).start();
    }

    private void initObject() {
        playerR = new ImageIcon("image/playerR.png");
        playerL = new ImageIcon("image/playerL.png");
    }

    private void initSetting() {
        x = 55;
        y = 535;

        left = false;
        right = false;
        up = false;
        left = false;

        setIcon(playerR);
        setSize(50, 50);
        setLocation(x, y);
    }

    // 이벤트 핸들러
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

    public void up() {
        System.out.println("up 메서드 실행됨");
        up = true;
        new Thread(() -> {
            for (int i = 0; i < 35; i++) {
                y = y - JUMPSPEED;
                setLocation(x, y);
                try {
                    Thread.sleep(10); // 0.01초s
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
            while (down) {
                y = y + JUMPSPEED;
                setLocation(x, y);
                try {
                    Thread.sleep(10); // 0.01초s
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            down = false;
        }).start();

    }

}