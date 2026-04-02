package game.ui;

import audio.Sound;
import audio.commands.AudioCommand;
import audio.commands.PlayAudioCommand;
import graphics.AlignmentX;
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
    private AlignmentX alignment;

    public TextButton(float x, float y, TextFont font, AlignmentX alignment) {
        this.x = x;
        this.y = y;
        this.w = 0;
        this.h = 0;
        this.font = font;
        this.alignment = alignment;
        state = ButtonState.DEFAULT;
    }

    public void update(Input input, Queue<DrawCommand> outDraw, Queue<AudioCommand> outAudio) {
        if (state == ButtonState.DEFAULT && isInside(input.getMouseX(), input.getMouseY())) {
            state = ButtonState.HOVER;
            PlayAudioCommand pac = new PlayAudioCommand(Sound.HOVER);
            outAudio.add(pac);
        }

        if (state == ButtonState.HOVER && input.isDidPressMouse() && isInside(input.getMousePressedX(), input.getMousePressedY())) {
            state = ButtonState.PRESSED;
            PlayAudioCommand pac = new PlayAudioCommand(Sound.CLICK);
            outAudio.add(pac);
        }

        if (state == ButtonState.PRESSED && !input.isLeftMouseButtonDown()) {
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
        switch (alignment) {
            case LEFT -> {
            }
            case CENTER -> {
                x = x - font.getDrawWidth(text) / 2;
            }
            case RIGHT -> {
                x = x - font.getDrawWidth(text);
            }
        }
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