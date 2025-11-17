package game.view;

import audio.commands.AudioCommand;
import game.ui.Sprite;
import graphics.Texture;
import graphics.commands.DrawCommand;
import platform.Input;
import platform.UiInput;

import java.util.Queue;

public class MainMenu implements View {
    Sprite logoSprite;

    public MainMenu() {
        logoSprite = new Sprite(0, 0, 0, 0, Texture.MAIN_LOGO);
    }

    @Override
    public void update(final Input input, Queue<DrawCommand> outDraw, Queue<AudioCommand> outAudio) {
        UiInput uiInput = input.getUiInput();
        uiInput.getStartButton().update(input, outDraw, outAudio);
        uiInput.getOptionsButton().update(input, outDraw, outAudio);
        uiInput.getExitButton().update(input, outDraw, outAudio);

        logoSprite.update(input, outDraw, outAudio);
    }
}
