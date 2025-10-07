package platform;

import java.awt.event.WindowAdapter;
import java.awt.Frame;

public class WindowManager extends WindowAdapter {
    private String title;
    private int width;
    private int height;
    private Frame frame;

    public WindowManager(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        frame = null;
    }

    public void createWindow() {
        frame = new Frame(title);
        frame.setVisible(true);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
    }
}
