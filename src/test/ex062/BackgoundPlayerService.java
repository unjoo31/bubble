package test.ex062;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

// 메인쓰레드는 바쁨 - 키보드 이벤트 처리하기 바쁨
// 백그라운드에서 관찰
// Runnable 타입의 쓰레드를 상속 받고 클래스 자체를 쓰레드로 만듬
// 클래스 자체를 쓰레드로 만들기(익명으로 만들지 않는 이유가 player를 받기 위해서)
public class BackgoundPlayerService implements Runnable {

    private Player player;
    private BufferedImage image;

    public BackgoundPlayerService(Player player) {
        this.player = player;

        File file = new File("image/backgroundMapService.png");
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Runnable이 가지고 있는 추상메서드 run()
    @Override
    public void run() {
        while (true) {
            Color color = new Color(image.getRGB(player.getX(), player.getY()));

            System.out.println("보글이의 위치의 빨간색상 : " + color.getRed());
            System.out.println("보글이의 위치의 초록색상 : " + color.getGreen());
            System.out.println("보글이의 위치의 블루색상 : " + color.getBlue());
            System.out.println("보글이의 x값 : " + player.getX());

            if (color.getRed() == 255 && player.getX() <= 85) {
                System.out.println("왼쪽벽에 부딫부딫");
            } else if (color.getRed() == 255 && player.getX() >= 885) {
                System.out.println("오른쪽벽에 부딫부딫");
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
