package platform;

public class Input {
    private float mousePressedX;
    private float mousePressedY;
    private boolean didPressMouse;
    private float mouseX;
    private float mouseY;
    private boolean isLeftMouseButtonDown;

    public Input() {
    }

    public void set(Input input) {
        mousePressedX = input.mousePressedX;
        mousePressedY = input.mousePressedY;
        didPressMouse = input.didPressMouse;
        mouseX = input.mouseX;
        mouseY = input.mouseY;
        isLeftMouseButtonDown = input.isLeftMouseButtonDown;
    }

    public void clear() {
        didPressMouse = false;
    }

    public boolean isLeftMouseButtonDown() {
        return isLeftMouseButtonDown;
    }

    public void setLeftMouseButtonDown(boolean leftMouseButtonDown) {
        isLeftMouseButtonDown = leftMouseButtonDown;
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
