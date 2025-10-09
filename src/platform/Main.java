package platform;

import graphics.*;

public class Main {
    public static void main(String[] args) {

        WindowManager windowManager = new WindowManager("my first window", 512, 512);
        windowManager.createWindow();

        Renderer renderer = new Renderer();
        DrawInfo drawInfo = new DrawInfo();

        int[] pixels = new int[512 * 512];

        while (true) {

            drawInfo.mousePoint = windowManager.getPoint();
            renderer.draw(pixels, drawInfo);

            windowManager.flip(pixels);
        }

    }
}
