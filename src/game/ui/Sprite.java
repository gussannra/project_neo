package game.ui;

import audio.commands.AudioCommand;
import game.Animation;
import game.EaseFunction;
import graphics.AlignmentX;
import graphics.AlignmentY;
import graphics.Texture;
import graphics.commands.DrawCommand;
import graphics.commands.DrawImageCommand;
import platform.Input;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Sprite {
    private float x;
    private float y;
    private Animation xAnimation;
    private Animation yAnimation;
    private AlignmentX xAlignment;
    private AlignmentY yAlignment;
    private float w;
    private float h;
    private Animation wAnimation;
    private Animation hAnimation;

    private List<Texture> textureList = new ArrayList<>();
    private Animation textureIndexAnimation;

    public Sprite(float x, float y, float w, float h, Texture texture) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        textureList.add(texture);
        xAlignment = AlignmentX.LEFT;
        yAlignment = AlignmentY.TOP;
        xAnimation = new Animation(x, x, 0, EaseFunction.CONSTANT_START);
        yAnimation = new Animation(y, y, 0, EaseFunction.CONSTANT_START);
        wAnimation = new Animation(w, w, 0, EaseFunction.CONSTANT_START);
        hAnimation = new Animation(h, h, 0, EaseFunction.CONSTANT_START);
        textureIndexAnimation = new Animation(0, 0, 0, EaseFunction.CONSTANT_START);

    }

    private float align(float x, AlignmentX alignment) {
        switch (alignment) {
            case LEFT -> {
                return x;
            }
            case CENTER -> {
                return x - w / 2;
            }
            case RIGHT -> {
                return x - w;
            }
        }
        return x;
    }

    private float align(float y, AlignmentY alignment) {
        switch (alignment) {
            case TOP -> {
                return y;
            }
            case CENTER -> {
                return y - h / 2;
            }
            case BOTTOM -> {
                return y - h;
            }
        }
        return y;
    }

    public void update(Input input, Queue<DrawCommand> outDraw, Queue<AudioCommand> outAudio) {
        xAnimation.update(input.getSecondsPerFrame());
        yAnimation.update(input.getSecondsPerFrame());
        wAnimation.update(input.getSecondsPerFrame());
        hAnimation.update(input.getSecondsPerFrame());
        textureIndexAnimation.update(input.getSecondsPerFrame());

        float x = align(xAnimation.sample(), xAlignment);
        float y = align(yAnimation.sample(), yAlignment);
        float w = wAnimation.sample();
        float h = hAnimation.sample();

        int textureIndex = (int) textureIndexAnimation.sample();

        DrawCommand cmd = new DrawImageCommand(x, y, w, h, textureList.get(textureIndex));
        outDraw.add(cmd);
    }

    public void addTexture(Texture texture) {
        textureList.add(texture);
    }

    public void setXAnimation(Animation xAnimation) {
        this.xAnimation = xAnimation;
    }

    public void setYAnimation(Animation yAnimation) {
        this.yAnimation = yAnimation;
    }

    public void setXAlignment(AlignmentX xAlignment) {
        this.xAlignment = xAlignment;
    }

    public void setYAlignment(AlignmentY yAlignment) {
        this.yAlignment = yAlignment;
    }

    public void setWAnimation(Animation wAnimation) {
        this.wAnimation = wAnimation;
    }

    public void setHAnimation(Animation hAnimation) {
        this.hAnimation = hAnimation;
    }

    public void setTextureIndexAnimation(Animation textureIndexAnimation) {
        this.textureIndexAnimation = textureIndexAnimation;
    }

    public float getW() {
        return w;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getH() {
        return h;
    }
}
