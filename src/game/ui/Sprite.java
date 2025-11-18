package game.ui;

import audio.commands.AudioCommand;
import game.Animation;
import graphics.Alignment;
import graphics.Texture;
import graphics.commands.DrawCommand;
import graphics.commands.DrawImageCommand;
import platform.Input;

import java.util.Queue;

public class Sprite {
    private float x;
    private float y;
    private float w;
    private float h;
    private Texture texture;
    private Alignment alignment;
    private Animation animation;

    public Sprite(float x, float y, float w, float h, Texture texture, Alignment alignment, Animation animation) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.texture = texture;
        this.alignment = alignment;
        this.animation = animation;

        switch (alignment) {
            case LEFT -> {
            }
            case CENTER -> {
                this.x = x - w / 2;
            }
            case RIGHT -> {
                this.x = x - w;
            }
        }
    }

    public void update(Input input, Queue<DrawCommand> outDraw, Queue<AudioCommand> outAudio) {
        animation.update(input.getSecondsPerFrame());
        float y = animation.sample();
        DrawCommand cmd = new DrawImageCommand(x, y, w, h, texture);
        outDraw.add(cmd);
    }


}
