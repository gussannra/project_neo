package graphics;

import java.util.*;

public class Renderer {

    public Renderer() {
    }

    public void draw(int[] outPixels, DrawInfo drawInfo) {
        clear(outPixels);

        for (int i = 0; i < outPixels.length; i++) {
            Random rand = new Random();
            int choice = rand.nextInt(2);
            int red = 0x00FF0000;
            int blue = 0x000000FF;

            if (choice == 0) {
                outPixels[i] = red;
            } else {
                outPixels[i] = blue;
            }

        }
    }

    private void clear(int[] outPixels) {
        Arrays.fill(outPixels, 0);
    }
}
