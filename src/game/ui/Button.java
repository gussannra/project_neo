package game.ui;

import platform.Input;

import javax.swing.*;

public class Button {
    private float x;
    private float y;
    private float w;
    private float h;

    public enum ButtonState {
        HOVER, PRESSED, DEFAULT;
    }

    private ButtonState state;

    public Button(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        state = ButtonState.DEFAULT;
    }

    public void update(Input input) {
        state = ButtonState.DEFAULT;
        if (isInside(input.getMouseX(), input.getMouseY())) {
            state = ButtonState.HOVER;
        }
    }

    private boolean isInside(float x, float y) {
        return x > this.x && y > this.y && x < this.x + this.w && y < this.y + this.h;
    }

    public ButtonState getState() {
        return state;
    }
}
