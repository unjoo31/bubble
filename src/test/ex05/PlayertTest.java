package test.ex05;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PlayertTest extends JLabel {

    private int x;
    private int y;

    private boolean right;
    private boolean left;
    private boolean up;
    private boolean down;

    private final int SPEED = 10;
    private final int JUMPSPEED = 4;

    private ImageIcon playerL, playerR;

    public PlayertTest() {
        initObject();
        initSetting();
    }

    private void initObject() {
        playerL = new ImageIcon("image/playerL.png");
        playerR = new ImageIcon("image/playerR.png");
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

    public void right() {
        System.out.println("right 메서드 실행됨");
        right = true;
        new Thread(() -> {
            while (right) {
                setIcon(playerR);
                x = x + SPEED;
                setLocation(x, y);

                try {
                    Thread.sleep(10);
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
                    Thread.sleep(10);
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
            for (int i = 0; i < 30; i++) {
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public ImageIcon getPlayerL() {
        return playerL;
    }

    public void setPlayerL(ImageIcon playerL) {
        this.playerL = playerL;
    }

    public ImageIcon getPlayerR() {
        return playerR;
    }

    public void setPlayerR(ImageIcon playerR) {
        this.playerR = playerR;
    }
}
