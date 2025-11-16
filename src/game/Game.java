package game;

import audio.commands.AudioCommand;
import game.ui.TextButton;
import graphics.TextFont;
import graphics.commands.DrawCommand;
import platform.Input;

import java.util.Queue;

public class Game {
    private float x = 0;
    private float y = 0;
    private TextButton textButton;

    public Game() {
        textButton = new TextButton(0.2f, 0.2f, TextFont.MENU_FONT);
        textButton.setText("Flashy begin");
    }

    public void update(final Input input, Queue<DrawCommand> outDraw, Queue<AudioCommand> outAudio) {
        textButton.update(input, outDraw);


//        if (input.isDidPressMouse()) {
//            x = input.getMousePressedX();
//            y = input.getMousePressedY();
//            PlayAudioCommand pac = new PlayAudioCommand(Sound.MAIN_THEME);
//            outAudio.add(pac);
//        }
//        DrawImageCommand command = new DrawImageCommand(x, y, Texture.PIG);
//        outDraw.add(command);
    }
}
