package platform;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class WindowManager extends WindowAdapter {
    private String title;
    private int width;
    private int height;
    private Frame frame;
    private BufferStrategy bufferStrategy;
    private BufferedImage buffer;
    private Graphics2D bufferGraphics;

    public WindowManager(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        frame = null;
        bufferStrategy = null;
        buffer = new BufferedImage(512, 512, BufferedImage.TYPE_INT_RGB);
        bufferGraphics = buffer.createGraphics();
        bufferGraphics.setBackground(Color.BLACK);
    }

    public void createWindow() {
        frame = new Frame(title);
        frame.setVisible(true);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.addWindowListener(this);

        frame.createBufferStrategy(2);
        bufferStrategy = frame.getBufferStrategy();
    }

    public void flip(int[] pixels) {
        buffer.setRGB(0, 0, 512, 512, pixels, 0, 512);
        Graphics g = bufferStrategy.getDrawGraphics();
        g.drawImage(buffer, 0, 0, frame.getWidth(), frame.getHeight(), null);
        g.dispose();

        bufferStrategy.show();
    }

    public void clearBuffer() {
        bufferGraphics.clearRect(0, 0, frame.getWidth(), frame.getHeight());
    }

    @Override
    public void windowClosing(WindowEvent e) {
        frame.dispose();
    }

}
