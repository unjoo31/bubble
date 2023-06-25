package test.ex11;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bubble extends JLabel {
    
    // 의존성 콤포지션
    private Player player;

    // 위치상태
    private int x;
    private int y;

    // 음직임 상태
    private boolean left;
    private boolean right;
    private boolean up;

    // 적군을 맞춘 상태
    private int state; // 0(물방울), 1(적을 가둔 물방울))

    private ImageIcon bubble; // 물방울
    private ImageIcon bubbled; // 적을 가둔 물방울
    private ImageIcon bomb; // 물방울이 터진 상태


    public Bubble(Player player) {
        this.player = player;
        initObject();
        initSetting();
        initThread();
    }

    private void initObject(){
        bubble = new ImageIcon("image/bubble.png");
        bubbled = new ImageIcon("image/bubbled.png");
        bomb = new ImageIcon("image/bomb.png");
    }
    
    private void initSetting(){
        left = false;
        right = false;
        up = false;

        x = player.getX();
        y = player.getY();

        setIcon(bubble);
        setSize(50, 50);

        state = 0;
    }

    private void initThread(){
        // 버블은 스레드가 하나만 필요하다
        new Thread(()->{
            if(player.getPlayerWay() == PlayerWay.LEFT){
                left();
            }else if(player.getPlayerWay() == PlayerWay.RIGHT){
                right();
            }
        }).start();
    }

    public void left(){
        left = true;
        for(int i = 0; i < 400; i++){
            x--;
            setLocation(x, y);

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        up();
    }

    public void right(){
        right = true;
        for(int i = 0; i < 400; i++){
            x++;
            setLocation(x, y);

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        up();
    }

    public void up(){
        up = true;
        for(int i = 0; i < 400; i++){
            y--;
            setLocation(x, y);

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

