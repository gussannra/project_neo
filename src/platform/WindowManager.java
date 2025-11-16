package platform;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class WindowManager extends WindowAdapter implements ComponentListener {
    private String title;
    private int width;
    private int height;
    private Frame frame;
    private BufferStrategy bufferStrategy;
    private Graphics2D bufferGraphics;
    private Insets insets;
    private InputListener inputListener;
    private boolean isWindowAlive;

    public WindowManager(String title, InputListener inputListener) {
        this.title = title;
        this.width = inputListener.getScreenWidth();
        this.height = inputListener.getScreenHeight();
        this.inputListener = inputListener;
        frame = null;
        bufferStrategy = null;
        isWindowAlive = false;
    }

    public void createWindow() {
        frame = new Frame(title);
        frame.setVisible(true);
        insets = frame.getInsets();
        inputListener.setxOffset(insets.left);
        inputListener.setyOffset(insets.top);
        frame.setSize(width + insets.left + insets.right, height + insets.top + insets.bottom);
        frame.setLocationRelativeTo(null);

        frame.addWindowListener(this);
        frame.addComponentListener(this);
        frame.addMouseListener(inputListener);
        frame.addMouseMotionListener(inputListener);

        frame.createBufferStrategy(2);
        bufferStrategy = frame.getBufferStrategy();
        isWindowAlive = true;
    }

    public void destroyWindow() {
        frame.dispose();
    }

    public void flip(BufferedImage buffer) {
        do {
            do {
                Graphics g = bufferStrategy.getDrawGraphics();
                g.drawImage(buffer, insets.left, insets.top, width, height, null);
                g.dispose();
            } while (bufferStrategy.contentsRestored());
            bufferStrategy.show();
        } while (bufferStrategy.contentsLost());
    }

    public void updateInput(Input outInput) {
        outInput.set(inputListener.getInputBuffer());
    }

    @Override
    public void windowClosing(WindowEvent e) {
        isWindowAlive = false;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        width = frame.getWidth() - insets.left - insets.right;
        height = frame.getHeight() - insets.top - insets.bottom;

        inputListener.setScreenWidth(width);
        inputListener.setScreenHeight(height);
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    public boolean getIsWindowAlive() {
        return isWindowAlive;
    }
}
