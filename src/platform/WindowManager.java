package platform;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class WindowManager extends WindowAdapter implements ComponentListener, MouseListener {
    private String title;
    private int width;
    private int height;
    private Frame frame;
    private BufferStrategy bufferStrategy;
    private BufferedImage buffer;
    private Graphics2D bufferGraphics;
    private Insets insets;

    private Point mousePoint;
    private boolean isWindowAlive;

    public WindowManager(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        frame = null;
        bufferStrategy = null;
        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        bufferGraphics = buffer.createGraphics();
        bufferGraphics.setBackground(Color.BLACK);
        mousePoint = new Point(0, 0);
        isWindowAlive = false;
    }

    public void createWindow() {
        frame = new Frame(title);
        frame.setVisible(true);
        insets = frame.getInsets();
        frame.setSize(width + insets.left + insets.right, height + insets.top + insets.bottom);
        frame.setLocationRelativeTo(null);

        frame.addWindowListener(this);
        frame.addComponentListener(this);
        frame.addMouseListener(this);

        frame.createBufferStrategy(2);
        bufferStrategy = frame.getBufferStrategy();
        isWindowAlive = true;
    }

    public void destroyWindow() {
        frame.dispose();
    }

    public void flip(int[] pixels) {
        buffer.setRGB(0, 0, buffer.getWidth(), buffer.getHeight(), pixels, 0, buffer.getWidth());

        do {
            do {
                Graphics g = bufferStrategy.getDrawGraphics();
                g.drawImage(buffer, insets.left, insets.top, width, height, null);
                g.dispose();
            } while (bufferStrategy.contentsRestored());
            bufferStrategy.show();
        } while (bufferStrategy.contentsLost());
    }

    @Override
    public void windowClosing(WindowEvent e) {
        isWindowAlive = false;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        width = frame.getWidth() - insets.left - insets.right;
        height = frame.getHeight() - insets.top - insets.bottom;
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

    @Override
    public void mousePressed(MouseEvent e) {
        float xScalingFactor = (float) buffer.getWidth() / width;
        float yScalingFactor = (float) buffer.getHeight() / height;

        int mouseX = e.getX() - insets.left;
        int mouseY = e.getY() - insets.top;

        mousePoint.x = (int) (xScalingFactor * mouseX);
        mousePoint.y = (int) (yScalingFactor * mouseY);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    public Point getPoint() {
        return mousePoint;
    }

    public boolean getIsWindowAlive() {
        return isWindowAlive;
    }

}
