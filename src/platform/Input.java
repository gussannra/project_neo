package platform;

public class Input {
    private float mousePressedX;
    private float mousePressedY;
    private boolean didPressMouse;

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
}
