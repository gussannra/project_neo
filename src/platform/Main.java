package platform;

public class Main {
    public static void main(String[] args) {

        WindowManager windowManager = new WindowManager("my first window", 512, 512);
        windowManager.createWindow();

        int x = 0;

        int[] pixels = new int[512 * 512];

        while (true) {
            windowManager.clearBuffer();

            // pixels[x] = 

            x += 0.5;

            if (x >= 512) {
                x = 0;
            }

            windowManager.flip(pixels);
        }

    }
}
