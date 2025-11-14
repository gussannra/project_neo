package game;

import audio.Sound;
import audio.commands.AudioCommand;
import audio.commands.PlayAudioCommand;
import game.ui.Button;
import graphics.TextColor;
import graphics.TextFont;
import graphics.Texture;
import graphics.commands.DrawCommand;
import graphics.commands.DrawImageCommand;
import graphics.commands.DrawTextCommand;
import platform.Input;

import java.util.Queue;

public class Game {
    private float x = 0;
    private float y = 0;
    private Button button;

    public Game() {
        button = new Button(0.0f, 0.0f, 0.2f, 0.2f);
    }

    public void update(final Input input, Queue<DrawCommand> outDraw, Queue<AudioCommand> outAudio) {
        button.update(input);
        TextColor color = TextColor.WHITE;
        if (button.getState() == Button.ButtonState.HOVER) {
            color = TextColor.YELLOW;
        }

        DrawTextCommand drawTextCommand = new DrawTextCommand("Start", 0.4f, 0.4f, TextFont.MENU_FONT, color);
        outDraw.add(drawTextCommand);

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
