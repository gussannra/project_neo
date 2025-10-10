package platform;

import graphics.*;

public class Main {
    public static void main(String[] args) {
        final int drawWidth= 512;
        final int drawHeight = 512;

        WindowManager windowManager = new WindowManager("my first window", drawWidth, drawHeight);
        windowManager.createWindow();

        Renderer renderer = new Renderer();
        DrawInfo drawInfo = new DrawInfo();
        drawInfo.width = drawWidth;
        drawInfo.height = drawHeight;

        int[] pixels = new int[drawWidth * drawHeight];

        while (true) {

            drawInfo.mousePoint = windowManager.getPoint();
            renderer.draw(pixels, drawInfo);

            windowManager.flip(pixels);
        }

    }
}
