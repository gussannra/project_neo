package game.view;

import audio.commands.AudioCommand;
import graphics.commands.DrawCommand;
import platform.Input;

import java.util.Queue;

public interface View {
    void update(Input input, Queue<DrawCommand> outDraw, Queue<AudioCommand> outAudio);
}
