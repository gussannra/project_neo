package graphics;

import java.util.*;

public class Renderer {

    public Renderer() {
    }

    public void draw(int[] outPixels, DrawInfo drawInfo) {
        clear(outPixels);

        int w = 50;
        int h = 50;
        int mx = drawInfo.mousePoint.x;
        int my = drawInfo.mousePoint.y;

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int offsetY = y - h / 2;
                int offsetX = x - w / 2;
                int drawX = mx + offsetX;
                int drawY = my + offsetY;
                int index = drawX + drawY * 512;

                if (index < 0 || index >= outPixels.length) {
                    continue;
                }

                if (drawX >= drawInfo.width || drawX < 0) {
                    continue;
                }

                outPixels[index] = 0x00FF00;
            }

        }
    }

    private void clear(int[] outPixels) {
        Arrays.fill(outPixels, 0);
    }
}
