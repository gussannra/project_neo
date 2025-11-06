package game;

import audio.AudioCommands;
import graphics.Texture;
import graphics.commands.DrawCommand;
import graphics.commands.DrawImageCommand;
import platform.Input;

import java.util.Queue;

public class Game {
    private float x = 0;
    private float y = 0;

    public Game() {

    }

    public void update(final Input input, Queue<DrawCommand> outDraw, AudioCommands outAudio) {
        if (input.isDidPressMouse()) {
            x = input.getMousePressedX();
            y = input.getMousePressedY();
        }
        DrawImageCommand command = new DrawImageCommand(x, y, Texture.PIG);
        outDraw.add(command);
    }


}
