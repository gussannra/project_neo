package game.ui;

import graphics.TextColor;
import graphics.TextFont;
import graphics.commands.DrawCommand;
import graphics.commands.DrawTextCommand;
import platform.Input;

import java.util.Queue;

public class TextButton {
    private float x;
    private float y;
    private float w;
    private float h;

    public enum ButtonState {
        HOVER, PRESSED, DEFAULT;
    }

    private ButtonState state;

    private String text;
    private TextFont font;

    public TextButton(float x, float y, TextFont font) {
        this.x = x;
        this.y = y;
        this.w = 0;
        this.h = 0;
        this.font = font;
        state = ButtonState.DEFAULT;
    }

    public void update(Input input, Queue<DrawCommand> outDraw) {
        if (input.isDidPressMouse() && isInside(input.getMousePressedX(), input.getMousePressedY())) {
            state = ButtonState.PRESSED;
        }

        if (state == ButtonState.PRESSED && !input.isLeftMouseButtonDown()) {
            state = ButtonState.DEFAULT;
        }

        if (state == ButtonState.DEFAULT && isInside(input.getMouseX(), input.getMouseY())) {
            state = ButtonState.HOVER;
        }

        if (state == ButtonState.HOVER && !isInside(input.getMouseX(), input.getMouseY())) {
            state = ButtonState.DEFAULT;
        }

        TextColor color = TextColor.WHITE;
        if (state == TextButton.ButtonState.HOVER) {
            color = TextColor.YELLOW;
        }

        if (state == TextButton.ButtonState.PRESSED) {
            color = TextColor.RED;
        }

        DrawTextCommand drawTextCommand = new DrawTextCommand(text, x, y + h, font, color);
        outDraw.add(drawTextCommand);
    }

    private boolean isInside(float x, float y) {
        return x > this.x && y > this.y && x < this.x + this.w && y < this.y + this.h;
    }

    public ButtonState getState() {
        return state;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        this.w = font.getDrawWidth(text);
        this.h = font.size;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getW() {
        return w;
    }

    public float getH() {
        return h;
    }
}