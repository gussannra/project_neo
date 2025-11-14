package platform;

public class Input {
    private float mousePressedX;
    private float mousePressedY;
    private boolean didPressMouse;
    private float mouseX;
    private float mouseY;

    public Input() {

    }

    public void clear() {
         didPressMouse = false;
    }

    public float getMousePressedX() {
        return mousePressedX;
    }

    public boolean isDidPressMouse() {
        return didPressMouse;
    }

    public void setDidPressMouse(boolean didPressMouse) {
        this.didPressMouse = didPressMouse;
    }

    public float getMousePressedY() {
        return mousePressedY;
    }

    public void setMousePressedX(float mousePressedX) {
        this.mousePressedX = mousePressedX;
    }

    public void setMousePressedY(float mousePressedY) {
        this.mousePressedY = mousePressedY;
    }

    public float getMouseX() {
        return mouseX;
    }

    public void setMouseX(float mouseX) {
        this.mouseX = mouseX;
    }

    public float getMouseY() {
        return mouseY;
    }

    public void setMouseY(float mouseY) {
        this.mouseY = mouseY;
    }
}
