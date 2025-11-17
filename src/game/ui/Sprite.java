package game.ui;

import audio.commands.AudioCommand;
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
    Texture texture;

    public Sprite(float x, float y, float w, float h, Texture texture) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.texture = texture;
    }

    public void update(Input input, Queue<DrawCommand> outDraw, Queue<AudioCommand> outAudio) {
        DrawCommand cmd = new DrawImageCommand(x, y, texture);
        outDraw.add(cmd);
    }
}
