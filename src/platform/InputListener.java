package platform;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class InputListener implements MouseListener, MouseMotionListener {
    private final int baseWidth;
    private final int baseHeight;
    private int screenWidth;
    private int screenHeight;
    private float xScreenToBaseScale;
    private float yScreenToBaseScale;
    private int xOffset;
    private int yOffset;
    private Input inputBuffer;

    public InputListener(int baseWidth, int baseHeight) {
        this.baseHeight = baseHeight;
        this.baseWidth = baseWidth;
        this.screenHeight = baseHeight;
        this.screenWidth = baseWidth;
        this.xOffset = 0;
        this.yOffset = 0;
        inputBuffer = new Input();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX() - xOffset;
        int mouseY = e.getY() - yOffset;

        int mousePressedXInt = (int) (xScreenToBaseScale * mouseX);
        int mousePressedYInt = (int) (yScreenToBaseScale * mouseY);
        inputBuffer.setMousePressedX((float) mousePressedXInt / baseWidth);
        inputBuffer.setMousePressedY((float) mousePressedYInt / baseHeight);

        inputBuffer.setLeftMouseButtonDown(true);

        inputBuffer.setDidPressMouse(true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        inputBuffer.setLeftMouseButtonDown(false);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int mouseX = e.getX() - xOffset;
        int mouseY = e.getY() - yOffset;

        int mouseXInt = (int) (xScreenToBaseScale * mouseX);
        int mouseYInt = (int) (yScreenToBaseScale * mouseY);
        inputBuffer.setMouseX((float) mouseXInt / baseWidth);
        inputBuffer.setMouseY((float) mouseYInt / baseHeight);
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
        xScreenToBaseScale = (float) baseWidth / screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
        yScreenToBaseScale = (float) baseHeight / screenHeight;
    }

    public Input getInputBuffer() {
        return inputBuffer;
    }

    public void setxOffset(int xOffset) {
        this.xOffset = xOffset;
    }

    public void setyOffset(int yOffset) {
        this.yOffset = yOffset;
    }
}
